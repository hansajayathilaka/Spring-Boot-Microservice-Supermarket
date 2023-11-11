package com.springframework.userservice.service.user;

import com.springframework.userservice.exception.IdMismatchException;
import com.springframework.userservice.exception.UserDoesNotExistException;
import com.springframework.userservice.model.entity.User;
import com.springframework.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public User getUserById(String id) {
        User user = repository.findById(id).orElseThrow(UserDoesNotExistException::new);

        if (user.isDeleted()) {
            throw new UserDoesNotExistException();
        }

        return user;
    }

    public List<User> getAllUsers() {
        var users = repository.findAll();
        return users.stream()
                .filter(user -> !user.isDeleted()).toList();
    }

    public User addUser(User user) {
        user.setCreated(new Date());
        return repository.save(user);
    }

    public User updateUser(String userId, User user) {
        if (!userId.equals(user.getId())) {
            throw new IdMismatchException();
        }

        User oldUser = getUserById(userId);
        oldUser.updateFields(user);

        return repository.save(oldUser);
    }

    public User deleteUser(String userId) {
        User user = getUserById(userId);

        if (!user.isDeleted()) {
            user.setDeleted(true);
            repository.save(user);
        }

        return user;
    }
}
