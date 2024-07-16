package com.project.demo.rest.auth;

import com.project.demo.entity.Country;
import com.project.demo.entity.Currency;
import com.project.demo.entity.auth.AuthenticationService;
import com.project.demo.entity.auth.JwtService;
import com.project.demo.entity.rol.Role;
import com.project.demo.entity.rol.RoleEnum;
import com.project.demo.entity.rol.RoleRepository;
import com.project.demo.entity.user.LoginResponse;
import com.project.demo.entity.user.User;
import com.project.demo.entity.user.UserRepository;
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
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        if (optionalRole.isEmpty()) {
            return ResponseEntity.badRequest().body("Role not found");
        }
        user.setRole(optionalRole.get());

        if (user.getCountry() != null && user.getCountry().getCountryId() != null) {
            Optional<Country> optionalCountry = countryRepository.findById(user.getCountry().getCountryId());
            if (optionalCountry.isPresent()) {
                user.setCountry(optionalCountry.get());
            } else {
                // Crear y guardar una nueva entidad Country si no existe
                Country country = new Country();
                country.setCountryId(user.getCountry().getCountryId());
                country.setCountryName("Default Country Name");
                country.setCountryCode("Default Country Code");
                country.setCurrency(new Currency());
                country.setOperational(true);
                country = countryRepository.save(country);
                user.setCountry(country);
            }
        } else {
            return ResponseEntity.badRequest().body("Country information is missing or incomplete");
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}