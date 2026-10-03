package com.example.assessment.controller;

import com.example.assessment.model.User;
import com.example.assessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String email) {
        List<User> users;
        if (email != null && !email.isEmpty()) {
            users = userRepository.findByEmail(email);
        } else {
            users = userRepository.findAll();
        }
        for (User u : users) {
            u.setPassword("***");
        }
        return users;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(null); 
        user.setRole("student"); // Explicitly assign student role for public registration
        if (user.getPassword() != null) {
            user.setPassword(hashPassword(user.getPassword()));
        }
        User saved = userRepository.save(user);
        saved.setPassword("***");
        return saved;
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        List<User> users = userRepository.findByUsernameOrEmail(username, username);
        if (users.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        User user = users.get(0);
        if (!user.getPassword().equals(hashPassword(password))) {
            throw new RuntimeException("Invalid password");
        }
        
        user.setPassword("***");
        return user;
    }

    @PatchMapping("/{id}")
    public User updateUser(@RequestHeader(value = "X-User-Role", defaultValue = "") String role, @PathVariable Long id, @RequestBody Map<String, String> updates) {
        User user = userRepository.findById(id).orElseThrow();
        if (updates.containsKey("password")) {
            user.setPassword(hashPassword(updates.get("password")));
        }
        if (updates.containsKey("role")) {
            if (!"Admin".equalsIgnoreCase(role)) {
                throw new RuntimeException("Forbidden: Only Admins can change roles");
            }
            user.setRole(updates.get("role"));
        }
        User saved = userRepository.save(user);
        saved.setPassword("***");
        return saved;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    public static String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


