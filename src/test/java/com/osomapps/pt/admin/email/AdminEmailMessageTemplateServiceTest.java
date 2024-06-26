package com.osomapps.pt.admin.email;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.email.EmailMessageTemplate;
import com.osomapps.pt.email.EmailMessageTemplateRepository;
import com.osomapps.pt.email.EmailMessageType;
import com.osomapps.pt.email.EmailMessageTypeRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class AdminEmailMessageTemplateServiceTest {

    @Mock private EmailMessageTemplateRepository emailMessageTemplateRepository;
    @Mock private EmailMessageTypeRepository emailMessageTypeRepository;
    @Mock private DictionaryService dictionaryService;
    @InjectMocks private AdminEmailMessageTemplateService adminEmailMessageTemplateService;

    @Test
    public void findAll() {
        when(emailMessageTemplateRepository.findAll(any(Sort.class)))
                .thenReturn(Arrays.asList(new EmailMessageTemplate()));
        List<EmailMessageTemplateResponseDTO> responseDTOs =
                adminEmailMessageTemplateService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findOne_not_found() {
        when(emailMessageTemplateRepository.findById(eq(1L))).thenReturn(Optional.empty());
        adminEmailMessageTemplateService.findOne(1L);
    }

    @Test
    public void findOne() {
        when(emailMessageTemplateRepository.findById(eq(1L)))
                .thenReturn(
                        Optional.of(
                                new EmailMessageTemplate()
                                        .setEmailMessageType(new EmailMessageType())));
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO =
                adminEmailMessageTemplateService.findOne(1L);
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

    @Test
    public void create() {
        when(emailMessageTemplateRepository.save((EmailMessageTemplate) any()))
                .thenAnswer(i -> i.getArguments()[0]);
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO =
                adminEmailMessageTemplateService.create(
                        new EmailMessageTemplateRequestDTO()
                                .setType(new EmailMessageTypeRequestDTO()));
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

    @Test
    public void create_with_null_type() {
        when(emailMessageTemplateRepository.save((EmailMessageTemplate) any()))
                .thenAnswer(i -> i.getArguments()[0]);
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO =
                adminEmailMessageTemplateService.create(
                        new EmailMessageTemplateRequestDTO().setType(null));
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void update_not_found() {
        adminEmailMessageTemplateService.update(
                1L, new EmailMessageTemplateRequestDTO().setType(new EmailMessageTypeRequestDTO()));
    }

    @Test
    public void update() {
        when(emailMessageTemplateRepository.findById(eq(1L)))
                .thenReturn(
                        Optional.of(
                                new EmailMessageTemplate()
                                        .setEmailMessageType(new EmailMessageType())));
        when(emailMessageTemplateRepository.save((EmailMessageTemplate) any()))
                .thenAnswer(i -> i.getArguments()[0]);
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO =
                adminEmailMessageTemplateService.update(
                        1L,
                        new EmailMessageTemplateRequestDTO()
                                .setType(new EmailMessageTypeRequestDTO()));
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

    @Test
    public void update_with_type_1() {
        when(emailMessageTemplateRepository.findById(eq(1L)))
                .thenReturn(
                        Optional.of(
                                new EmailMessageTemplate()
                                        .setEmailMessageType(new EmailMessageType())));
        when(emailMessageTemplateRepository.save((EmailMessageTemplate) any()))
                .thenAnswer(i -> i.getArguments()[0]);
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO =
                adminEmailMessageTemplateService.update(
                        1L,
                        new EmailMessageTemplateRequestDTO()
                                .setType(new EmailMessageTypeRequestDTO().setId(1L)));
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_not_found() {
        adminEmailMessageTemplateService.delete(1L);
    }

    @Test
    public void delete() {
        when(emailMessageTemplateRepository.findById(eq(1L)))
                .thenReturn(
                        Optional.of(
                                new EmailMessageTemplate()
                                        .setEmailMessageType(new EmailMessageType())));
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO =
                adminEmailMessageTemplateService.delete(1L);
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }
}
