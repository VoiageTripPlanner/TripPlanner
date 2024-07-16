package com.project.demo.repository;

import com.project.demo.entity.Role;
import com.project.demo.entity.RoleEnum;
import com.project.demo.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserRepositoryTests(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Test
    public void UserRepository_findByUsername_ReturnsUser() {
        Role role = new Role();
        role.setDescription("ROLE_USER");
        role.setName(RoleEnum.USER);
        roleRepository.save(role);
        Role roleFound = roleRepository.findByName(RoleEnum.USER).get();

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setName("John");
        user.setLastname("Smith");
        user.setPassword("password");
        user.setRole(role);
        userRepository.save(user);

        User userFound = userRepository.findByName("John").get();

        Assertions.assertThat(userFound).isNotNull();
    }
}
