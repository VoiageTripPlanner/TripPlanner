package com.project.demo.repository;

import com.project.demo.Config.TestConfig;
import com.project.demo.entity.Country;
import com.project.demo.logic.AuthenticationService;
import com.project.demo.entity.Currency;
import com.project.demo.entity.Role;
import com.project.demo.entity.RoleEnum;
import com.project.demo.entity.User;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Date;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import(TestConfig.class)

public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired

    private CurrencyRepository currencyRepository;


    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;
    @BeforeEach
    public void infoSetup() {
        // Info de usuario
        Role role = new Role();
        role.setAbbreviation("USE");
        role.setRole_name(RoleEnum.USER);
        role.setOperational(true);
        role.setCreation_datetime(new Date());
        role.setCreation_responsible(1);
        role.setLast_update_datetime(new Date());
        role.setUpdate_responsible(1);
        roleRepository.save(role);

        Optional<Role> roleFound = roleRepository.findByName(RoleEnum.USER);
        Assertions.assertThat(roleFound).isPresent();

        // Currency info

        Currency currency = new Currency();
        currency.setCurrencyId(1);
        currency.setCurrencyName("Colon");
        currency.setCurrencyCode("CR");
        currency.setCurrencySymbol("CRC");
        currency = currencyRepository.save(currency);

        // Info de Country
        Country country = new Country();
        country.setCountryName("Costa Rica");
        country.setCountryCode("CR");
        country.setOperational(true);
        country.setCurrency(currency);
        countryRepository.save(country);

        Optional<Country> countryFound = countryRepository.findByCountryName("Costa Rica");
        Assertions.assertThat(countryFound).isPresent();

        // Info User
        User user = new User();
        user.setName("John");
        user.setLast_name("Smith");
        user.setSecond_last_name("Johnson");
        user.setCountry(country);
        user.setEmail("test@gmail.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setOperational(true);
        user.setRole(role);
        user.setCreation_datetime(new Date());
        user.setCreation_responsible(1);
        user.setLast_update_datetime(new Date());
        user.setUpdate_responsible(1);
        userRepository.save(user);

        // Info User 2
        User user2 = new User();
        user2.setName("Alice");
        user2.setLast_name("Johnson");
        user2.setSecond_last_name("Smith");
        user2.setCountry(country);
        user2.setEmail("alice@gmail.com");
        user2.setPassword(passwordEncoder.encode("password"));
        user2.setOperational(false);
        user2.setRole(role);
        user2.setCreation_datetime(new Date());
        user2.setCreation_responsible(1);
        user2.setLast_update_datetime(new Date());
        user2.setUpdate_responsible(1);
        userRepository.save(user2);

        Optional<User> userFoundOptional = userRepository.findByName("John");
        Assertions.assertThat(userFoundOptional).isPresent();
    }

    @Test
    public void findUserToResetPassword() {
        Optional<User> userFoundOptional = userRepository.findByEmail("test@gmail.com");
        Assertions.assertThat(userFoundOptional).isPresent();
    }

    @Test
    public void generateOTP() {
        Optional<User> userFoundOptional = userRepository.findByEmail("test@gmail.com");

        if (userFoundOptional.isPresent()) {
            User user = userFoundOptional.get();
            String otp = authenticationService.generateOTP();
            user.setOtp(otp);
            Assertions.assertThat(user.getOtp()).isNotNull();
        }
    }
}

