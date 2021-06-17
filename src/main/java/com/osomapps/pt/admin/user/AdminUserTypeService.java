package com.osomapps.pt.admin.user;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminUserTypeService {

    private final InUserTypeRepository inUserTypeRepository;
    private final DictionaryService dictionaryService;

    AdminUserTypeService(
            InUserTypeRepository inUserTypeRepository, DictionaryService dictionaryService) {
        this.inUserTypeRepository = inUserTypeRepository;
        this.dictionaryService = dictionaryService;
    }

    List<UserTypeResponseDTO> findAll() {
        return inUserTypeRepository.findAll().stream()
                .map(userType -> userTypeToDto(userType))
                .collect(Collectors.toList());
    }

    private UserTypeResponseDTO userTypeToDto(InUserType inUserType) {
        final String userTypeEnName =
                dictionaryService.getEnValue(
                        DictionaryName.user_type, inUserType.getD_user_type(), "");
        final String userTypeNoName =
                dictionaryService.getNoValue(
                        DictionaryName.user_type, inUserType.getD_user_type(), "");
        return UserTypeResponseDTO.builder()
                .id(inUserType.getId())
                .nameEn(userTypeEnName)
                .nameNo(userTypeNoName)
                .build();
    }
}
