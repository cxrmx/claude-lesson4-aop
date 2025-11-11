package com.claude.lesson4.repositories;

import com.claude.lesson4.models.User;
import com.claude.lesson4.models.UserRole;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    Map<Long, User> users = new HashMap<>();
    Long id = 0L;

    public UserRepository() {
        System.out.println("[IoC] Bean TaskRepository created");
        initializeUsers();
    }

    private void initializeUsers() {
        User user1 = new User(null,"Josh","josh-user@gmail.com", UserRole.USER);
        User user2 = new User(null, "Alex", "adminalex@gmail.com", UserRole.ADMIN);
        User user3 = new User(null, "Poatan", "poatan-butcher@mail.com", UserRole.USER);
        User user4 = new User(null, "Marry", "marry-the-ceo@gmail.com", UserRole.MANAGER);
        save(user1);
        save(user2);
        save(user3);
        save(user4);
    }

    public void save(User user) {
        if (user == null) {
            System.out.println("[UserRepository] Cannot save user. User is null");
            return;
        }
        if (user.getId() == null) {
            user = new User(id++, user.getUsername(), user.getEmail(), user.getUserRole());
        }
        users.put(user.getId(), user);
        System.out.println("[UserRepository] User was succesfully saved");
    }

    public User findById(Long id) {
        User user = users.get(id);
        if (user == null) {
            System.out.println("[UserRepository] Cannot find user by id " + id + ". User is null");
            return null;
        }
        System.out.println("[UserRepository] User was found by id. Returning user");
        return user;
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList(users.values());
        if (userList.isEmpty()) {
            System.out.println("[UserRepository] Returning empty list of users. Repository is empty");
            return userList;
        }
        System.out.println("[UserRepository] Returning list of users");
        return userList;
    }
}
