package com.project.demo.rest;

import com.project.demo.entity.User;
import com.project.demo.entity.request.UserRequest;
import com.project.demo.logic.UserService;
import com.project.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/req/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserRequest> retrieveAllMinusCurrent(@PathVariable Integer id) {
        return userService.findAllMinusCurrent(id);
    }

    @GetMapping("/userDetailed")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<User> getAllUsersDetailed() {
        return UserRepository.findUsersWithCountryAndRole();
    }

    @PostMapping
    public UserRequest create(@RequestBody UserRequest user) {
        return userService.save(user);
    }

    @Override
    public List<UserRequest> retrieveAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Optional<UserRequest> retrieveById(@PathVariable Integer id) {
        if (userService.findById(id).isPresent()) {
            return userService.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public UserRequest update(UserRequest entity) {
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
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public User authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}