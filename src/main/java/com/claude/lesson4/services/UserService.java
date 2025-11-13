package com.claude.lesson4.services;

import com.claude.lesson4.annotations.Loggable;
import com.claude.lesson4.models.User;
import com.claude.lesson4.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        System.out.println("[IoC] Bean UserService created");
        this.userRepository = userRepository;
        System.out.println("[UserService] Dependency User Repository was injected.");
    }

    @Loggable
    public User getUserById (Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("Cannot find user by id: " + userId + ". User is null");
        }
        System.out.println("[UserService] Returning user by id: " + userId);
        return user;
    }

    public List<User> getAllUsers() {
        System.out.println("[UserService] Returning all users from repository");
        return userRepository.findAll();
    }
}
