package com.ead.userservice.controller;

import com.ead.userservice.model.entity.User;
import com.ead.userservice.service.user.UserService;
import com.ead.userservice.service.user.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public User deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }
}
