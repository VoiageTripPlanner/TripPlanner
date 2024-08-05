package com.project.demo.logic;
import com.project.demo.logic.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.*;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.List;
import java.util.Optional;


@Service
public class EmailService implements IService<String, String> {


    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        } catch (MailParseException e) {
            throw new EmailServiceException(
                    "Failed to parse email message.",
                    HttpStatus.BAD_REQUEST,
                    "MAIL_PARSE_ERROR",
                    "An error occurred while parsing the email message. Please check the email content and try again.",
                    e
            );
        } catch (MailAuthenticationException e) {
            throw new EmailServiceException(
                    "Failed to authenticate email service.",
                    HttpStatus.UNAUTHORIZED,
                    "MAIL_AUTHENTICATION_ERROR",
                    "An error occurred while authenticating the email service. Please check the authentication details and try again.",
                    e
            );
        } catch (MailSendException e) {
            throw new EmailServiceException(
                    "Failed to send email.",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "MAIL_SEND_ERROR",
                    "An error occurred while sending the email. Please try again later.",
                    e
            );
        } catch (MailException e) {
            throw new EmailServiceException(
                    "General email service error.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "MAIL_ERROR",
                    "An error occurred with the email service. Please try again later.",
                    e
            );
        } catch (Exception e) {
            throw new EmailServiceException(
                    "Unexpected error occurred.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "UNEXPECTED_ERROR",
                    "An unexpected error occurred. Please try again later.",
                    e
            );
        }
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
    public String findById(String s) {
        return null;
    }

    @Override
    public String update(String entity) {
        return "";
    }

    @Override
    public void deleteById(String s) {

    }

}
