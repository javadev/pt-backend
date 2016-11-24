package com.github.pt.tokenemail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class SendEmailService {
    private final MailSender mailSender;

    SendEmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    void send(InUserEmail inUserEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Welcome to the Personal Trainer application!");
        message.setFrom("PT <pt.backend@gmail.com>");
        message.setTo(inUserEmail.getLogin());
        message.setText(
            "Dear " + inUserEmail.getUser_name()
                    + ", thank you for register in PT application. Your user id number is "
                    + inUserEmail.getInUser().getId() + ".");
        try {
            this.mailSender.send(message);
        }
        catch (MailException ex) {
            log.warn(ex.getMessage(), ex);
        }
    }
}
