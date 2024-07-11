package com.project.University.service;

import org.jobrunr.jobs.annotations.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Job(name = "Sending Notifications")
    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("ashaar409@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

}
