package com.project.demo.rest;

import com.project.demo.entity.User;
import com.project.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<User> getAllUsers() {
        return UserRepository.findUsersOperationalUsers();
    }

    @GetMapping("/userDetailed")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<User> getAllUsersDetailed() {
        return UserRepository.findUsersWithCountryAndRole();
    }


    @PostMapping
    public User addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return UserRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/filterByName/{name}")
    public List<User> getUserById(@PathVariable String name) {
        return UserRepository.findUsersWithCharacterInName(name);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return UserRepository.findById((long)id)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setLast_name(user.getLast_name());
                    existingUser.setEmail(user.getEmail());
                    return UserRepository.save(existingUser);
                })
                .orElseGet(() -> {
                    user.setUser_id(id);
                    return UserRepository.save(user);
                });
    }

    @PutMapping("delete/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return UserRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setOperational(false);
                    UserRepository.save(existingUser);
                    return true ;
                })
                .orElseGet(() -> {
                    return false;
                });
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public User authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

}