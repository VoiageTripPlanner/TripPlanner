package com.project.demo.rest;

import com.project.demo.entity.request.UserRequest;
import com.project.demo.logic.AuthenticationService;
import com.project.demo.logic.EmailService;
import com.project.demo.logic.JwtService;
import com.project.demo.logic.UserService;
import com.project.demo.entity.LoginResponse;
import com.project.demo.entity.User;
import com.project.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthRestController implements IController<UserRequest, Integer> {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

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
        loginResponse.setUser_id(user.getUser_id());

        Optional<User> foundedUser = userRepository.findByEmail(user.getEmail());

        foundedUser.ifPresent(loginResponse::setAuthUser);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest user) {
        UserRequest savedUser = userService.save(user);

        return ResponseEntity.ok(savedUser);
    }

    //Me da error en el test si lo paso a AuthenticationService
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody User user) {

        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("No se ecuentra el usuario");
        }
        User userToUpdate = optionalUser.get();

        String otp = authenticationService.generateOTP();

        userToUpdate.setOtp(otp);
        userRepository.save(userToUpdate);

        emailService.sendSimpleEmail(user.getEmail(), "Reset Password", "Your OTP is: " + otp);

        return ResponseEntity.ok(userToUpdate);
    }


    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOTP(@RequestBody User user) {
        return authenticationService.validateOTP(user);
    }

    @Override
    public ResponseEntity<UserRequest> create(UserRequest entity) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserRequest>> retrieveAll() {
        return null;
    }

    @Override
    public ResponseEntity<UserRequest> retrieveById(Integer aLong) {
        return null;
    }

    @Override
    public ResponseEntity<UserRequest> update(UserRequest entity) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer aLong) {

        return null;
    }
}