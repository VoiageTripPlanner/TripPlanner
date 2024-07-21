package com.project.demo.logic.seeder;

import com.project.demo.entity.Role;
import com.project.demo.entity.RoleEnum;
import com.project.demo.entity.User;
import com.project.demo.repository.UserRepository;
import com.project.demo.repository.CountryRepository;
import com.project.demo.repository.RoleRepository;
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
    private final CountryRepository countryRepository;
    
    public AdminSeeder(
            RoleRepository roleRepository,
            UserRepository  userRepository,
            PasswordEncoder passwordEncoder,
            CountryRepository countryRepository
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.countryRepository = countryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createAdmin();
    }

    private void createAdmin(){
        User admin = new User();
        admin.setName("Admin");
        admin.setLast_name("Seeder");
        admin.setEmail("admin.seed@gmail.com");
        admin.setPassword("superadmin123");
        admin.setOperational(true);

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
        Optional<User> optionalUser = userRepository.findByEmail(admin.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(optionalRole.get());
        userRepository.save(admin);
    }
}
