
package com.supplyhut.controller;

import com.supplyhut.model.User;
import com.supplyhut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/")
public String home() {
    return "Welcome to SupplyHut backend!";
}  

@GetMapping("/findbyemail/{email}")
public User getUserByEmail(@PathVariable String email) {
    return userRepository.findByEmail(email).orElse(null);
}





    @Autowired
    private UserRepository userRepository;

    // Register user (POST /api/users/register)
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "User already exists with this email!";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }

    // Login (POST /api/users/login)
    @PostMapping("/login")
    public String loginUser(@RequestBody User loginData) {
        Optional<User> user = userRepository.findByEmail(loginData.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(loginData.getPassword())) {
            return "Login successful!";
        } else {
            return "Invalid email or password!";
        }
    }
}
