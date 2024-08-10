package com.project.demo.rest;

import com.project.demo.entity.User;
import com.project.demo.entity.request.UserRequest;
import com.project.demo.logic.UserService;
import com.project.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController implements IController<UserRequest, Integer> {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<UserRequest>> retrieveAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/userDetailed")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<User> getAllUsersDetailed() {
        return UserRepository.findUsersWithCountryAndRole();
    }

    @PostMapping
    public ResponseEntity<UserRequest> create(@RequestBody UserRequest user) {
        UserRequest userResponse = userService.save(user);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> retrieveById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<UserRequest> update(UserRequest entity) {
        return null;
    }

    @GetMapping("/filterByName/{name}")
    public List<User> getUserById(@PathVariable String name) {
        return UserRepository.findUsersWithCharacterInName(name);
    }

    @PutMapping("/{id}")
    public UserRequest update(@PathVariable Integer id, @RequestBody UserRequest user) {
        return userService.update(user);
    }

    @PutMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public User authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}