package com.osomapps.pt.admin.email;

import com.osomapps.pt.ResourceNotFoundException;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.email.EmailMessageTemplate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
class AdminEmailMessageTemplateService {

    private final EmailMessageTemplateRepository emailMessageTemplateRepository;
    private final DictionaryService dictionaryService;
    
    AdminEmailMessageTemplateService(EmailMessageTemplateRepository emailMessageTemplateRepository,
            DictionaryService dictionaryService) {
        this.emailMessageTemplateRepository = emailMessageTemplateRepository;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    EmailMessageTemplateResponseDTO update(Long id, EmailMessageTemplateRequestDTO templateRequestDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    EmailMessageTemplateResponseDTO delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
