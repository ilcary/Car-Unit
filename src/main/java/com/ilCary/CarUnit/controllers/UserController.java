package com.ilCary.CarUnit.controllers;

import com.ilCary.CarUnit.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ilCary.CarUnit.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users/") //TODO impostare la rotta
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //---------------------------- Post ---------------------------------

    @PostMapping
    public User saveUser(
//          TODO gestire il post
//            @Valid
//            @RequestParam("name") String name,
//            @RequestParam(value="address",required=false) String address,
    ) {
        User user = User.builder().build();

        logger.info("Save User in UserController");
        return userService.save(user);
    }

    //---------------------------- Get ---------------------------------

    @GetMapping
    public List<User> getUserList() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    //---------------------------- Delete --------------------------------

    @DeleteMapping("{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "User deleted successfully";
    }

    //---------------------------- Put --------------------------------

    @PutMapping("{id}")
    public User updateUser(
            @PathVariable("id") Long id
//            @RequestParam("name") String name
    ) {

        User user = userService.getById(id);

        //TODO gestire il put

        userService.save(user);
        return user;
    }

}
