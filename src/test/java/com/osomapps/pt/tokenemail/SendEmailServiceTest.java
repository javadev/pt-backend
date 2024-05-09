package com.osomapps.pt.tokenemail;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.email.EmailMessageTemplate;
import com.osomapps.pt.email.EmailMessageType;
import com.osomapps.pt.email.EmailMessageTypeRepository;
import com.osomapps.pt.token.InUser;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@RunWith(MockitoJUnitRunner.class)
public class SendEmailServiceTest {

    @Mock private MailSender mailSender;
    @Mock private EmailMessageTypeRepository emailMessageTypeRepository;
    @Mock private DictionaryService dictionaryService;

    @InjectMocks private SendEmailService sendEmailService;

    @Test
    public void send_error() {
        doThrow(new MailException("") {}).when(mailSender).send(any(SimpleMailMessage.class));
        sendEmailService.send(new InUserEmail().setInUser(new InUser().setId(1L)));
        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    public void send() {
        sendEmailService.send(new InUserEmail().setInUser(new InUser().setId(1L)));
        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    public void send_with_template() {
        when(emailMessageTypeRepository.findByNameOrderByIdDesc(eq("WelcomeMessage")))
                .thenReturn(
                        Arrays.asList(
                                new EmailMessageType()
                                        .setEmailMessageTemplates(
                                                Arrays.asList(new EmailMessageTemplate()))));
        when(dictionaryService.getEnValue(any(DictionaryName.class), isNull(), anyString()))
                .thenReturn("test");
        sendEmailService.send(new InUserEmail().setInUser(new InUser().setId(1L)));
        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    public void sendForgotPassword_error() {
        doThrow(new MailException("") {}).when(mailSender).send(any(SimpleMailMessage.class));
        sendEmailService.sendForgotPassword(new InUserEmail().setInUser(new InUser().setId(1L)));
        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    public void sendForgotPassword() {
        sendEmailService.sendForgotPassword(new InUserEmail().setInUser(new InUser().setId(1L)));
        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    public void sendForgotPassword_with_template() {
        when(emailMessageTypeRepository.findByNameOrderByIdDesc(eq("ForgetPasswordMessage")))
                .thenReturn(
                        Arrays.asList(
                                new EmailMessageType()
                                        .setEmailMessageTemplates(
                                                Arrays.asList(new EmailMessageTemplate()))));
        when(dictionaryService.getEnValue(any(DictionaryName.class), isNull(), anyString()))
                .thenReturn("test");
        sendEmailService.sendForgotPassword(new InUserEmail().setInUser(new InUser().setId(1L)));
        verify(mailSender).send(any(SimpleMailMessage.class));
    }
}
