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
class SendEmailService {
    private final MailSender mailSender;
    private final EmailMessageTypeRepository emailMessageTypeRepository;
    private final DictionaryService dictionaryService;
    private final String emailConfirmUrl;

    SendEmailService(MailSender mailSender,
            EmailMessageTypeRepository emailMessageTypeRepository,
            DictionaryService dictionaryService,
            @Value("${app.email.confirm.url}") String emailConfirmUrl) {
        this.mailSender = mailSender;
        this.emailMessageTypeRepository = emailMessageTypeRepository;
        this.dictionaryService = dictionaryService;
        this.emailConfirmUrl = emailConfirmUrl;
    }

    String getSubject(String defaultValue) {
        List<EmailMessageType> emailMessageTypes = emailMessageTypeRepository.findByNameOrderByIdDesc("WelcomeMessage");
        if (emailMessageTypes.isEmpty()) {
            return defaultValue;
        }
        return dictionaryService.getEnValue(DictionaryName.email_subject,
                emailMessageTypes.get(emailMessageTypes.size() - 1).getEmailMessageTemplates().get(
                emailMessageTypes.get(emailMessageTypes.size() - 1).getEmailMessageTemplates().size() - 1)
                        .getDEmailSubject(), defaultValue);
    }
    
    String getText(String defaultValue) {
        List<EmailMessageType> emailMessageTypes = emailMessageTypeRepository.findByNameOrderByIdDesc("WelcomeMessage");
        if (emailMessageTypes.isEmpty()) {
            return defaultValue;
        }
        return dictionaryService.getEnValue(DictionaryName.email_text,
                emailMessageTypes.get(emailMessageTypes.size() - 1).getEmailMessageTemplates().get(
                emailMessageTypes.get(emailMessageTypes.size() - 1).getEmailMessageTemplates().size() - 1)
                        .getDEmailText(), defaultValue);
    }

    void send(InUserEmail inUserEmail) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user_name", inUserEmail.getUser_name());
        parameters.put("email_confirm_link", emailConfirmUrl + inUserEmail.getConfirmToken());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(getSubject("Welcome to the Personal Trainer application!"));
        message.setFrom("PT <pt.backend@gmail.com>");
        message.setTo(inUserEmail.getLogin());
        message.setText(
            TemplateEngine.<String, String>template(getText("Dear {{user_name}}!\n"
            + "\n"
            + "Your registration was completed successfully.\n"
            + "Please click to the link to confirm your e-mail {{email_confirm_link}}.\n"
            + "\n"
            + "Yours PT support team.")).apply(parameters));
        try {
            this.mailSender.send(message);
        }
        catch (MailException ex) {
            log.warn(ex.getMessage(), ex);
        }
    }
}
