package com.project.demo.rest;

import com.project.demo.entity.Country;
import com.project.demo.logic.AuthenticationService;
import com.project.demo.logic.EmailService;
import com.project.demo.logic.JwtService;
import com.project.demo.entity.Role;
import com.project.demo.entity.RoleEnum;
import com.project.demo.repository.RoleRepository;
import com.project.demo.entity.LoginResponse;
import com.project.demo.entity.User;
import com.project.demo.repository.UserRepository;
import com.project.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;

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

    @Autowired
    private EmailService emailService;

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

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
        loginResponse.setUser_id(user.getUser_id());

        Optional<User> foundedUser = userRepository.findByEmail(user.getEmail());

        foundedUser.ifPresent(loginResponse::setAuthUser);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        if (optionalRole.isEmpty()) {
            return ResponseEntity.badRequest().body("Role not found");
        }
        user.setRole(optionalRole.get());

        if (user.getCountry() != null && user.getCountry().getCountryId() != null) {
            Optional<Country> optionalCountry = countryRepository.findById(user.getCountry().getCountryId());

                user.setCountry(optionalCountry.get());

        } else {
            return ResponseEntity.badRequest().body("Country information is missing or incomplete");
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        User userToUpdate = optionalUser.get();

        String otp = authenticationService.generateOTP();

        userToUpdate.setOtp(otp);
        userRepository.save(userToUpdate);

        emailService.sendSimpleEmail(user.getEmail(), "Reset Password", "Your OTP is: " + userToUpdate.getOtp());

        return ResponseEntity.ok(userToUpdate);
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOTP(@RequestBody User user) {
        Optional<User> optionalUser = userRepository.findByOTP(user.getOtp());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        User userToUpdate = optionalUser.get();
        if (userToUpdate.getOtp().equals(user.getOtp())) {
            userToUpdate.setOtp(null);
            userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(userToUpdate);
            return ResponseEntity.ok(userToUpdate);
        }
        return ResponseEntity.badRequest().body("OTP is incorrect");
    }
}