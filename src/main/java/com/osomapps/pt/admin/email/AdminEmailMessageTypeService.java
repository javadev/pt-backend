package com.osomapps.pt.admin.email;

import com.osomapps.pt.email.EmailMessageTypeRepository;
import com.osomapps.pt.email.EmailMessageType;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
class AdminEmailMessageTypeService {

    private final EmailMessageTypeRepository emailMessageTypeRepository;

    AdminEmailMessageTypeService(EmailMessageTypeRepository emailMessageTypeRepository) {
        this.emailMessageTypeRepository = emailMessageTypeRepository;
    }

    List<EmailMessageTypeResponseDTO> findAll() {
        return emailMessageTypeRepository.findAll(sortByIdAsc()).stream().map(
                AdminEmailMessageTypeService::emailMessageTypeDto
        ).collect(Collectors.toList());
    }

    private Sort sortByIdAsc() {
        return Sort.by(Sort.Direction.ASC, "id");
    }

    private static EmailMessageTypeResponseDTO emailMessageTypeDto(EmailMessageType input) {
        return EmailMessageTypeResponseDTO.builder()
                .id(input.getId())
                .name(input.getName())
                .build();
    }

}
