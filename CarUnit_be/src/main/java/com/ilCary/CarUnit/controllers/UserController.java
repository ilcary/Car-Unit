package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.DTO.converter.UserConverter;
import com.ilCary.CarUnit.DTOs.UserDTO;
import com.ilCary.CarUnit.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ilCary.CarUnit.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users") //TODO impostare la rotta
@CrossOrigin("http://localhost:4200")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    //---------------------------- Post ---------------------------------

    @PostMapping
    public User saveUser(@RequestBody User user) {
        System.err.println(user.getPassword());
        logger.info("Save User in UserController");
        return userService.save(user);
    }

    //---------------------------- Get ---------------------------------

    @GetMapping
    public List<User> getUserList() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("/makeItCeo/{id}")
    public User makeItCeo(@PathVariable("id") Long id){
        User u = userService.getById(id);
        u.setCeo(true);
        return userService.update(u);
    }

    //---------------------------- Delete --------------------------------

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "User deleted successfully";
    }

    //---------------------------- Put --------------------------------

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable("id") Long id,
            @RequestBody User updateUser
    ) {

        User user = userService.getById(id);

        System.out.println("QUESTO è LO USER UPDATE /////////// "+updateUser.toString());


        if (updateUser.getName() != null)
            user.setName(updateUser.getName());
        if (updateUser.getLastname() != null)
            user.setLastname(updateUser.getLastname());
        if (updateUser.getUsername() != null)
            user.setUsername(updateUser.getUsername());
        if (updateUser.getEmail() != null)
            user.setEmail(updateUser.getEmail());
        if (updateUser.getTasks() != null)
            user.setTasks(updateUser.getTasks());
        if (updateUser.getDealership() != null)
            user.setDealership(updateUser.getDealership());
        if (updateUser.getAddress() != null)
            user.setAddress(updateUser.getAddress());
        if (updateUser.getPassword() != null){
            user.setPassword(updateUser.getPassword());
            return userService.save(user);
        }

        return userService.update(user);
    }

}
