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
public class UserRestController implements IController<User, Integer> {

    private final UserRepository UserRepository;
    private final UserService userService;

    public UserRestController(com.project.demo.repository.UserRepository userRepository, UserService userService) {
        UserRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<User>> retrieveAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/userDetailed")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsersDetailed() {
        return ResponseEntity.ok(UserRepository.findUsersWithCountryAndRole());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> retrieveById(@PathVariable Integer id) {
       return ResponseEntity.ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<User> update(User entity) {
        return null;
    }

    @GetMapping("/filterByName/{name}")
    public ResponseEntity<List<User>> getUserById(@PathVariable String name) {
        return ResponseEntity.ok(UserRepository.findUsersWithCharacterInName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @PutMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok((User) authentication.getPrincipal());
    }
}