package com.project.demo.rest;

import com.project.demo.entity.Country;
import com.project.demo.entity.request.UserRequest;
import com.project.demo.logic.AuthenticationService;
import com.project.demo.logic.JwtService;
import com.project.demo.entity.Role;
import com.project.demo.entity.RoleEnum;
import com.project.demo.logic.UserService;
import com.project.demo.repository.RoleRepository;
import com.project.demo.entity.LoginResponse;
import com.project.demo.entity.User;
import com.project.demo.repository.UserRepository;
import com.project.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthRestController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CountryRepository countryRepository;

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    @Autowired
    private UserService userService;

    public AuthRestController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody User user) {
        User authenticatedUser = authenticationService.authenticate(user);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        Optional<User> foundedUser = userRepository.findByEmail(user.getEmail());

        foundedUser.ifPresent(loginResponse::setAuthUser);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest user) {
        UserRequest savedUser = userService.save(user);

        return ResponseEntity.ok(savedUser);
    }
}