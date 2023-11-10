package com.springframework.userservice.service.user;

import com.springframework.userservice.model.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(String id);

    List<User> getAllUsers();

    User addUser(User user);

    User updateUser(String userId, User user);

    User deleteUser(String userId);
}
