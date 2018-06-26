package com.osomapps.pt.admin.email;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.email.EmailMessageTemplate;
import com.osomapps.pt.email.EmailMessageTemplateRepository;
import com.osomapps.pt.email.EmailMessageType;
import com.osomapps.pt.email.EmailMessageTypeRepository;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
public class AdminEmailMessageTemplateServiceTest {

    @Mock
    private EmailMessageTemplateRepository emailMessageTemplateRepository;
    @Mock
    private EmailMessageTypeRepository emailMessageTypeRepository;
    @Mock
    private DictionaryService dictionaryService;
    @InjectMocks
    private AdminEmailMessageTemplateService adminEmailMessageTemplateService;

    @Test
    public void findAll() {
        when(emailMessageTemplateRepository.findAll(any(Sort.class))).thenReturn(
                Arrays.asList(new EmailMessageTemplate()));
        List<EmailMessageTemplateResponseDTO> responseDTOs = adminEmailMessageTemplateService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }

    @Test
    public void findOne_not_found() {
        when(emailMessageTemplateRepository.findOne(eq(1L))).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> {adminEmailMessageTemplateService.findOne(1L);});
    }

    @Test
    public void findOne() {
        when(emailMessageTemplateRepository.findOne(eq(1L))).thenReturn(
                new EmailMessageTemplate().setEmailMessageType(new EmailMessageType()));
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO = adminEmailMessageTemplateService.findOne(1L);
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

    @Test
    public void create() {
        when(emailMessageTemplateRepository.save((EmailMessageTemplate) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO = adminEmailMessageTemplateService.create(
            new EmailMessageTemplateRequestDTO().setType(new EmailMessageTypeRequestDTO()));
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

    @Test
    public void create_with_null_type() {
        when(emailMessageTemplateRepository.save((EmailMessageTemplate) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO = adminEmailMessageTemplateService.create(
            new EmailMessageTemplateRequestDTO().setType(null));
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

    @Test
    public void update_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminEmailMessageTemplateService.update(1L,
            new EmailMessageTemplateRequestDTO().setType(new EmailMessageTypeRequestDTO()));});
    }

    @Test
    public void update() {
        when(emailMessageTemplateRepository.findOne(eq(1L))).thenReturn(
                new EmailMessageTemplate().setEmailMessageType(new EmailMessageType()));
        when(emailMessageTemplateRepository.save((EmailMessageTemplate) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO = adminEmailMessageTemplateService.update(1L,
            new EmailMessageTemplateRequestDTO().setType(new EmailMessageTypeRequestDTO()));
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

    @Test
    public void update_with_type_1() {
        when(emailMessageTemplateRepository.findOne(eq(1L))).thenReturn(
                new EmailMessageTemplate().setEmailMessageType(new EmailMessageType()));
        when(emailMessageTemplateRepository.save((EmailMessageTemplate) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO = adminEmailMessageTemplateService.update(1L,
            new EmailMessageTemplateRequestDTO().setType(new EmailMessageTypeRequestDTO().setId(1L)));
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

    @Test
    public void delete_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminEmailMessageTemplateService.delete(1L);});
    }

    @Test
    public void delete() {
        when(emailMessageTemplateRepository.findOne(eq(1L))).thenReturn(
                new EmailMessageTemplate().setEmailMessageType(new EmailMessageType()));
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO = adminEmailMessageTemplateService.delete(1L);
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }
    
}
