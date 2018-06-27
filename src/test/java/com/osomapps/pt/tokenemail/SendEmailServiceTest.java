package com.osomapps.pt.tokenemail;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.email.EmailMessageTemplate;
import com.osomapps.pt.email.EmailMessageType;
import com.osomapps.pt.email.EmailMessageTypeRepository;
import com.osomapps.pt.token.InUser;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SendEmailServiceTest {

    @Mock
    private MailSender mailSender;
    @Mock
    private EmailMessageTypeRepository emailMessageTypeRepository;
    @Mock
    private DictionaryService dictionaryService;

    @InjectMocks
    private SendEmailService sendEmailService;

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
                .thenReturn(Arrays.asList(new EmailMessageType().setEmailMessageTemplates(
                        Arrays.asList(new EmailMessageTemplate()))));
        when(dictionaryService.getEnValue(any(DictionaryName.class), isNull(), anyString())).thenReturn("test");
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
                .thenReturn(Arrays.asList(new EmailMessageType().setEmailMessageTemplates(
                        Arrays.asList(new EmailMessageTemplate()))));
        when(dictionaryService.getEnValue(any(DictionaryName.class), isNull(), anyString())).thenReturn("test");
        sendEmailService.sendForgotPassword(new InUserEmail().setInUser(new InUser().setId(1L)));
        verify(mailSender).send(any(SimpleMailMessage.class));
    }
}
