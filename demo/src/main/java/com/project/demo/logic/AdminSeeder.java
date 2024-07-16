package com.project.demo.logic;

import com.project.demo.entity.Role;
import com.project.demo.entity.RoleEnum;
import com.project.demo.entity.User;
import com.project.demo.repository.RoleRepository;
import com.project.demo.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
            RoleRepository roleRepository,
            UserRepository  userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        User superAdmin = new User();
        superAdmin.setName("Super");
        superAdmin.setLastname("Admin");
        superAdmin.setEmail("super.admin@gmail.com");
        superAdmin.setPassword("superadmin123");

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> optionalUser = userRepository.findByEmail(superAdmin.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new User();
        user.setName(superAdmin.getName());
        user.setLastname(superAdmin.getLastname());
        user.setEmail(superAdmin.getEmail());
        user.setPassword(passwordEncoder.encode(superAdmin.getPassword()));
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }
}
