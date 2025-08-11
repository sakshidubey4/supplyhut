package com.supplyhut.controller;

import com.supplyhut.model.User;
import com.supplyhut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete user
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    // Promote to admin
    @PutMapping("/users/{id}/promote")
    public String promoteToAdmin(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setRole("admin");
            userRepository.save(user);
            return "User promoted to admin";
        }
        return "User not found";
    }
}

