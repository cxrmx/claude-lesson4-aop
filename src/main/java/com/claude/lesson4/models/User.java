package com.claude.lesson4.models;

public class User {
    private Long id;
    private String username;
    private String email;
    private UserRole userRole;

    public User(Long id, String username, String email, UserRole userRole) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
