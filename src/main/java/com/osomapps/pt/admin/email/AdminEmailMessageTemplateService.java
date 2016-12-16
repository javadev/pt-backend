package com.osomapps.pt.admin.email;

import com.osomapps.pt.ResourceNotFoundException;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.email.EmailMessageTemplate;
import com.osomapps.pt.email.EmailMessageType;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
class AdminEmailMessageTemplateService {

    private final EmailMessageTemplateRepository emailMessageTemplateRepository;
    private final EmailMessageTypeRepository emailMessageTypeRepository;
    private final DictionaryService dictionaryService;
    
    AdminEmailMessageTemplateService(EmailMessageTemplateRepository emailMessageTemplateRepository,
            EmailMessageTypeRepository emailMessageTypeRepository,
            DictionaryService dictionaryService) {
        this.emailMessageTemplateRepository = emailMessageTemplateRepository;
        this.emailMessageTypeRepository = emailMessageTypeRepository;
        this.dictionaryService = dictionaryService;
    }

    List<EmailMessageTemplateResponseDTO> findAll() {
        return emailMessageTemplateRepository.findAll(sortByIdAsc()).stream().map(template ->
            templateToDto(template)
        ).collect(Collectors.toList());
    }
    
    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }

    private EmailMessageTemplateResponseDTO templateToDto(EmailMessageTemplate template) {
        return EmailMessageTemplateResponseDTO.builder()
                .id(template.getId())
                .emailSubjectEn(dictionaryService.getEnValue(DictionaryName.email_subject, template.getDEmailSubject(), ""))
                .emailSubjectNo(dictionaryService.getNoValue(DictionaryName.email_subject, template.getDEmailSubject(), ""))
                .emailTextEn(dictionaryService.getEnValue(DictionaryName.email_text, template.getDEmailSubject(), ""))
                .emailTextNo(dictionaryService.getNoValue(DictionaryName.email_text, template.getDEmailSubject(), ""))
                .type(template.getEmailMessageType() == null ? null : EmailMessageTypeResponseDTO.builder()
                        .id(template.getEmailMessageType().getId())
                        .name(template.getEmailMessageType().getName())
                        .build())
                .build();
    }

    EmailMessageTemplateResponseDTO findOne(Long id) {
        final EmailMessageTemplate emailMessageTemplate = emailMessageTemplateRepository.findOne(id);
        if (emailMessageTemplate == null) {
            throw new ResourceNotFoundException("EmailMessageTemplate with id " + id + " not found.");
        }
        return templateToDto(emailMessageTemplate);
    }

    EmailMessageTemplateResponseDTO create(EmailMessageTemplateRequestDTO templateRequestDTO) {
        final EmailMessageType emailMessageTypeDb =
                templateRequestDTO.getType() == null ? null : emailMessageTypeRepository
            .findOne(templateRequestDTO.getType().getId());
        final String dataKey = dictionaryService.createDictionaryDataKey(DictionaryName.email_subject,
                dictionaryService.getNewDictionaryDataKey(DictionaryName.email_subject),
                templateRequestDTO.getEmailSubjectEn(), templateRequestDTO.getEmailSubjectNo());
        final String data2Key = dictionaryService.createDictionaryDataKey(DictionaryName.email_text,
                dictionaryService.getNewDictionaryDataKey(DictionaryName.email_text),
                templateRequestDTO.getEmailTextEn(), templateRequestDTO.getEmailTextNo());
        final EmailMessageTemplate emailMessageTemplate = new EmailMessageTemplate();
        emailMessageTemplate.setDEmailSubject(dataKey);
        emailMessageTemplate.setDEmailText(data2Key);
        emailMessageTemplate.setEmailMessageType(emailMessageTypeDb);
        return templateToDto(emailMessageTemplateRepository.save(emailMessageTemplate));
    }

    EmailMessageTemplateResponseDTO update(Long id, EmailMessageTemplateRequestDTO templateRequestDTO) {
        final EmailMessageTemplate existedEmailMessageTemplate = emailMessageTemplateRepository.findOne(id);
        if (existedEmailMessageTemplate == null) {
            throw new ResourceNotFoundException("EmailMessageTemplate with id not found: " + id);
        }
        final String dataKey =
            dictionaryService.createDictionaryDataKey(DictionaryName.email_subject, 
                existedEmailMessageTemplate.getDEmailSubject(),
                templateRequestDTO.getEmailSubjectEn(), templateRequestDTO.getEmailSubjectNo());
        final String data2Key =
            dictionaryService.createDictionaryDataKey(DictionaryName.email_text, 
                existedEmailMessageTemplate.getDEmailSubject(),
                templateRequestDTO.getEmailTextEn(), templateRequestDTO.getEmailTextNo());
        final EmailMessageType emailMessageTypeDb =
            templateRequestDTO.getType().getId() == null ? null : emailMessageTypeRepository
            .findOne(templateRequestDTO.getType().getId());
        existedEmailMessageTemplate.setEmailMessageType(emailMessageTypeDb);
        existedEmailMessageTemplate.setDEmailSubject(dataKey);
        existedEmailMessageTemplate.setDEmailText(data2Key);
        final EmailMessageTemplate savedEmailMessageTemplate = emailMessageTemplateRepository.save(existedEmailMessageTemplate);
        return templateToDto(savedEmailMessageTemplate);
    }

    EmailMessageTemplateResponseDTO delete(Long id) {
        final EmailMessageTemplate existedEmailMessageTemplate = emailMessageTemplateRepository.findOne(id);
        if (existedEmailMessageTemplate == null) {
            throw new ResourceNotFoundException("EmailMessageTemplate with id " + id + " not found.");
        }
        dictionaryService.deleteDatas(DictionaryName.email_subject, existedEmailMessageTemplate.getDEmailSubject());
        dictionaryService.deleteDatas(DictionaryName.email_text, existedEmailMessageTemplate.getDEmailText());
        final EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO = templateToDto(existedEmailMessageTemplate);
        emailMessageTemplateRepository.delete(id);
        return emailMessageTemplateResponseDTO;
    }
}
