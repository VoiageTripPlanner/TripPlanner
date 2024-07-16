package com.project.demo.rest.country;

import com.project.demo.logic.entity.country.Country;
import com.project.demo.logic.entity.country.CountryRepository;
import com.project.demo.logic.entity.user.User;
import com.project.demo.logic.entity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryRestController {
    @Autowired
    private CountryRepository CountryRepository;

    @GetMapping
    public List<Country> getAllUsers() {
        return CountryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Country getUserById(@PathVariable Integer id) {
        return CountryRepository.findCountryById(id).orElseThrow(RuntimeException::new);
    }

}