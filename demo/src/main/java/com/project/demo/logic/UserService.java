package com.project.demo.logic;

import com.project.demo.entity.*;
import com.project.demo.entity.request.UserRequest;
import com.project.demo.logic.exceptions.UserServiceException;
import com.project.demo.repository.CountryRepository;
import com.project.demo.repository.CurrencyRepository;
import com.project.demo.repository.RoleRepository;
import com.project.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IService<UserRequest, Integer> {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRequest userRequest;

    @Override
    public UserRequest save(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setLast_name(userRequest.getLastname());
        user.setSecond_last_name(userRequest.getSecondLastname());
        user.setEmail(userRequest.getEmail());
        user.setBirthDate(userRequest.getBirthDate());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRequest.setPassword(null);
        user.setOperational(true);
        Country country = countryRepository.findById(Integer.valueOf(userRequest.getCountryId())).orElse(null);
        Currency currency = currencyRepository.findById(Integer.valueOf(userRequest.getCurrencyId())).orElse(null);
        Role role = roleRepository.findByName(RoleEnum.USER).orElseThrow(RuntimeException::new);

        user.setRole(role);
        user.setCountry(country);
        user.setCurrency(currency);
        userRepository.save(user);
        return userRequest;
    }

    @Override
    public List<UserRequest> findAll() {
        return null;
    }

    public List<UserRequest> findAllMinusCurrent(Integer id) {
        return userRepository.findUsersOperationalUsers(id).stream()
                .map(user -> {
                    createUserRequest(user);
                    return userRequest;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRequest> findById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        createUserRequest(user);
        return Optional.of(userRequest);
    }

    @Override
    public UserRequest update(UserRequest userRequest) {
        return userRepository.findById(userRequest.getId())
                .map(existingUser -> {
                    existingUser.setName(userRequest.getName());
                    existingUser.setLast_name(userRequest.getLastname());
                    existingUser.setSecond_last_name(userRequest.getSecondLastname());
                    existingUser.setCurrency(currencyRepository.findById(Integer.valueOf(userRequest.getCurrencyId())).orElse(null));
                    existingUser.setCountry(countryRepository.findById(Integer.valueOf(userRequest.getCountryId())).orElse(null));
                    userRepository.save(existingUser);
                    return userRequest;
                })
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUser_id(userRequest.getId());
                    newUser.setName(userRequest.getName());
                    newUser.setLast_name(userRequest.getLastname());
                    newUser.setEmail(userRequest.getEmail());
                    userRepository.save(newUser);
                    return userRequest;
                });
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setOperational(false);
                    userRepository.save(existingUser);
                    return true ;
                })
                .orElseGet(() -> {
                    return false;
                });
    }

    private void createUserRequest(User user) {
        userRequest = new UserRequest();
        userRequest.setId(user.getUser_id());
        userRequest.setName(user.getName());
        userRequest.setLastname(user.getLast_name());
        userRequest.setSecondLastname(user.getSecond_last_name());
        userRequest.setEmail(user.getEmail());
        userRequest.setBirthDate(user.getBirthDate());
        userRequest.setAuthorities(user.getAuthorities());
        if (Optional.ofNullable(user.getCountry()).isPresent()) {
            userRequest.setCountryId(user.getCountry().getCountryId().toString());
        }
        if (Optional.ofNullable(user.getCurrency()).isPresent()) {
            userRequest.setCurrencyId(user.getCurrency().getCurrencyId().toString());
        }
        userRequest.setOperational(user.isOperational());
        userRequest.setCreateAt(user.getCreation_datetime().toString());
        userRequest.setUpdateAt(user.getLast_update_datetime().toString());
        userRequest.setRoleId(user.getRole().getRole_id());
    }

    public User findByIdTrip(Integer id) {
        try {
            return userRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new UserServiceException(
                    "Failed to find user by ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while finding the user. Please try again later.",
                    e
            );
        }
    }
}
