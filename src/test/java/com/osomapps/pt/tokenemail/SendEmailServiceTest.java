package com.osomapps.pt.tokenemail;

import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.email.EmailMessageTypeRepository;
import com.osomapps.pt.token.InUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@RunWith(MockitoJUnitRunner.class)
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

}
