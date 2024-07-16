package com.project.demo.logic;

import com.project.demo.entity.User;
import com.project.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User, Integer>{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User save(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.ofNullable(userRepository.findById(Long.valueOf(integer)).orElseThrow(RuntimeException::new));
    }

    @Override
    public User update(/*Long id,*/User entity) {
        return userRepository.findById(entity.getUserId()).map(existingUser -> {
            existingUser.setName(existingUser.getName());
            existingUser.setLastName(existingUser.getLastName());
            existingUser.setEmail(existingUser.getEmail());
            return userRepository.save(existingUser);
        })
                .orElseGet(() -> {
                    // user.setUser_id(id);
                    return userRepository.save(entity);
                });
    }

    @Override
    public void deleteById(Integer integer) {
        userRepository.deleteById(Long.valueOf(integer));
    }

    public Optional<User> findByUserName(String name) {
        return userRepository.findByName(name);
    }
}
