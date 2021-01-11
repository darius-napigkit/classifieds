package com.metheor.java.samples.classifieds.controller;

import com.metheor.java.samples.classifieds.model.User;
import com.metheor.java.samples.classifieds.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> createUser(@RequestBody User user) {
        LOGGER.info("Creating and saving a user... ");
        return userService.saveUser(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<User> getAllUsers() {
        LOGGER.info("Retrieving all users... ");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Mono<User> getUserDetails(@PathVariable String id) {
        LOGGER.info("Retrieving user details for id: " + id);
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> updateUser(@PathVariable String id, @RequestBody User user) {
        LOGGER.info("Updating details for user with id: " + id);
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> deleteUser(@PathVariable String id) {
        LOGGER.info("Deleting user with id: " + id);
        return userService.deleteUser(id);
    }

}
