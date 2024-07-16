package com.project.demo.entity.rol;

import com.project.demo.entity.user.User;
import com.project.demo.entity.user.UserRepository;
import com.project.demo.repository.CountryRepository;
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
//        this.createSuperAdministrator();
    }

//    private void createSuperAdministrator() {
//        User superAdmin = new User();
//        superAdmin.setName("Super");
//        superAdmin.setLast_name("Admin");
//        superAdmin.setEmail("super.admin@gmail.com");
//        superAdmin.setPassword("superadmin123");
//        superAdmin.setOperational(true);
//        superAdmin.setCreation_responsible(1L);
//        superAdmin.setUpdate_responsible(1L);
//        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
//        Optional<Role> optionalCountry = CountryRepository.(1);
//        Optional<User> optionalUser = userRepository.findByEmail(superAdmin.getEmail());
//
//        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
//            return;
//        }
//
//        var user = new User();
//        user.setName(superAdmin.getName());
//        user.setLast_name(superAdmin.getLast_name());
//        user.setEmail(superAdmin.getEmail());
//        user.setPassword(passwordEncoder.encode(superAdmin.getPassword()));
//        user.setRole(optionalRole.get());
//
//        userRepository.save(user);
//    }
}
