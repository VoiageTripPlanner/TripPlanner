package com.project.demo.logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;
import java.util.Optional;


@Service
public class EmailService implements IService<String, String> {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public String save(String entity) {
        return "";
    }

    @Override
    public List<String> findAll() {
        return List.of();
    }

    @Override
    public Optional<String> findById(String s) {
        return Optional.empty();
    }

    @Override
    public String update(String entity) {
        return "";
    }

    @Override
    public void deleteById(String s) {

    }
}
