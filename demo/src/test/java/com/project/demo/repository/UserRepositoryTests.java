package com.project.demo.repository;

import com.project.demo.entity.rol.Role;
import com.project.demo.entity.rol.RoleEnum;
import com.project.demo.entity.rol.RoleRepository;
import com.project.demo.entity.user.User;
import com.project.demo.entity.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.management.remote.JMXAuthenticator;
import java.util.Date;
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
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;
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

        // Info de Country
        Country country = new Country();
        country.setCountry_name("Costa Rica");
        country.setCountry_code("CR");
        country.setOperational(true);
        countryRepository.save(country);

        Optional<Country> countryFound = countryRepository.findByName("Costa Rica");
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

        Optional<User> userFoundOptional = userRepository.findByName("John");
        Assertions.assertThat(userFoundOptional).isPresent();
    }

    @Test
    public void UserRepository_findByUsername_ReturnsUser() {
        Optional<User> userFoundOptional = userRepository.findByName("John");
        Assertions.assertThat(userFoundOptional).isPresent();
        User userFound = userFoundOptional.get();
        Assertions.assertThat(userFound).isNotNull();
        Assertions.assertThat(userFound.getName()).isEqualTo("John");
    }

    @Test
    public void testLoginSuccess() {
        User userInput = new User();
        userInput.setEmail("test@gmail.com");
        userInput.setPassword("password");

        User user = authenticationService.authenticate(userInput);
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getEmail()).isEqualTo("test@gmail.com");

    }

    @Test
    public void testLoginFailure() {
        User userInput = new User();
        userInput.setEmail("test@gmail.com");
        userInput.setPassword("wrongpassword");

        Assertions.assertThatThrownBy(() -> {
            authenticationService.authenticate(userInput);
        }).isInstanceOf(Exception.class);
    }


}
