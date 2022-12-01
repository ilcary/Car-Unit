package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import softLock.security.JwtUtils;
import softLock.security.login.LoginRequest;
import softLock.security.details.UserDetailsImpl;
import softLock.security.login.LoginResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(method = {RequestMethod.POST}, value = "/api")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService us;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            authentication.getAuthorities();

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                    roles, userDetails.getExpirationTime()));

        } catch (DisabledException | LockedException e) {
            return new ResponseEntity<>("User disabled", HttpStatus.FORBIDDEN);
        }
    }
}
