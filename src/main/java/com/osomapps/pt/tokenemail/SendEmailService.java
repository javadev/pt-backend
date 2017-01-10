package com.osomapps.pt.tokenemail;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.email.EmailMessageType;
import com.osomapps.pt.email.EmailMessageTypeRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendEmailService {
    private final MailSender mailSender;
    private final EmailMessageTypeRepository emailMessageTypeRepository;
    private final DictionaryService dictionaryService;
    private final String emailConfirmUrl;
    private final String emailResetUrl;

    private enum MessageType {
       WelcomeMessage, ForgetPasswordMessage
    }

    SendEmailService(MailSender mailSender,
            EmailMessageTypeRepository emailMessageTypeRepository,
            DictionaryService dictionaryService,
            @Value("${app.email.confirm.url}") String emailConfirmUrl,
            @Value("${app.email.reset.url}") String emailResetUrl) {
        this.mailSender = mailSender;
        this.emailMessageTypeRepository = emailMessageTypeRepository;
        this.dictionaryService = dictionaryService;
        this.emailConfirmUrl = emailConfirmUrl;
        this.emailResetUrl = emailResetUrl;
    }

    String getSubject(String defaultValue, MessageType messageType) {
        List<EmailMessageType> emailMessageTypes = emailMessageTypeRepository
                .findByNameOrderByIdDesc(messageType.name());
        if (emailMessageTypes.isEmpty()) {
            return defaultValue;
        }
        return dictionaryService.getEnValue(DictionaryName.email_subject,
                emailMessageTypes.get(emailMessageTypes.size() - 1).getEmailMessageTemplates().get(
                emailMessageTypes.get(emailMessageTypes.size() - 1).getEmailMessageTemplates().size() - 1)
                        .getDEmailSubject(), defaultValue);
    }
    
    String getText(String defaultValue, MessageType messageType) {
        List<EmailMessageType> emailMessageTypes = emailMessageTypeRepository
                .findByNameOrderByIdDesc(messageType.name());
        if (emailMessageTypes.isEmpty()) {
            return defaultValue;
        }
        return dictionaryService.getEnValue(DictionaryName.email_text,
                emailMessageTypes.get(emailMessageTypes.size() - 1).getEmailMessageTemplates().get(
                emailMessageTypes.get(emailMessageTypes.size() - 1).getEmailMessageTemplates().size() - 1)
                        .getDEmailText(), defaultValue);
    }

    public void send(InUserEmail inUserEmail) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user_name", inUserEmail.getUser_name());
        parameters.put("email_confirm_link", emailConfirmUrl + inUserEmail.getConfirmToken());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(getSubject("Welcome to the Personal Trainer application!", MessageType.WelcomeMessage));
        message.setFrom("PT <pt.backend@gmail.com>");
        message.setTo(inUserEmail.getLogin());
        message.setText(
            TemplateEngine.<String, String>template(getText("Dear {{user_name}}!\n"
            + "\n"
            + "Your registration was completed successfully.\n"
            + "Please click to the link to confirm your e-mail {{email_confirm_link}}.\n"
            + "\n"
            + "Yours PT support team.", MessageType.WelcomeMessage)).apply(parameters));
        try {
            this.mailSender.send(message);
        }
        catch (MailException ex) {
            log.warn(ex.getMessage(), ex);
        }
    }

    void sendForgotPassword(InUserEmail inUserEmail) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user_name", inUserEmail.getUser_name());
        parameters.put("reset_password_link", emailResetUrl + inUserEmail.getResetToken());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(getSubject("Reset password service", MessageType.ForgetPasswordMessage));
        message.setFrom("PT <pt.backend@gmail.com>");
        message.setTo(inUserEmail.getLogin());
        message.setText(
            TemplateEngine.<String, String>template(getText("Dear {{user_name}}!\n" +
            "\n" +
            "Please click to the link to reset your password {{reset_password_link}}.\n" +
            "\n" +
            "Sincerely,\n" +
            "PT support team", MessageType.ForgetPasswordMessage)).apply(parameters));
        try {
            this.mailSender.send(message);
        }
        catch (MailException ex) {
            log.warn(ex.getMessage(), ex);
        }
    }

}
