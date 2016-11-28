package com.github.pt.admin.user;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.UnauthorizedException;
import com.github.pt.dictionary.DictionaryName;
import com.github.pt.dictionary.DictionaryService;
import com.github.pt.token.InUser;
import com.github.pt.token.InUserFacebook;
import com.github.pt.token.InUserFacebookRepository;
import com.github.pt.token.InUserRepository;
import com.github.pt.tokenemail.EmailValidator;
import com.github.pt.tokenemail.InUserEmail;
import com.github.pt.tokenemail.InUserEmailRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;

@Service
class AdminUserService {
    
    private final InUserRepository inUserRepository;
    private final InUserEmailRepository inUserEmailRepository;
    private final InUserFacebookRepository inUserFacebookRepository;
    private final InUserTypeRepository inUserTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;
    private final DictionaryService dictionaryService;
    
    AdminUserService(InUserRepository inUserRepository,
            InUserEmailRepository inUserEmailRepository,
            InUserFacebookRepository inUserFacebookRepository,
            InUserTypeRepository inUserTypeRepository,
            PasswordEncoder passwordEncoder,
            EmailValidator emailValidator,
            DictionaryService dictionaryService) {
        this.inUserRepository = inUserRepository;
        this.inUserEmailRepository = inUserEmailRepository;
        this.inUserFacebookRepository = inUserFacebookRepository;
        this.inUserTypeRepository = inUserTypeRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailValidator = emailValidator;
        this.dictionaryService = dictionaryService;
    }

    List<UserResponseDTO> findAll() {
        return inUserRepository.findAll(sortByIdAsc()).stream().map(inUser ->
                inUserToDto(inUser)
        ).collect(Collectors.toList());
    }

    private UserResponseDTO inUserToDto(InUser inUser) {
        final String userName;
        final String userEmail;
        if (inUser.getInUserFacebooks() == null || inUser.getInUserFacebooks().isEmpty()) {
            if (inUser.getInUserEmails().isEmpty()) {
                userName = "?";
                userEmail = "?";
            } else {
                userName = inUser.getInUserEmails().get(inUser.getInUserEmails().size() - 1).getUser_name();
                userEmail = inUser.getInUserEmails().get(inUser.getInUserEmails().size() - 1).getLogin();
            }
        } else {
            userName = inUser.getInUserFacebooks().get(inUser.getInUserFacebooks().size() - 1).getUser_name();
            userEmail = "Facebook user";
        }
        
        return UserResponseDTO.builder()
                .id(inUser.getId())
                .email(userEmail)
                .name(userName)
                .type(inUser.getInUserType() == null ? null : UserTypeResponseDTO.builder()
                    .id(inUser.getInUserType().getId())
                    .nameEn(dictionaryService.getEnValue(DictionaryName.user_type,
                            inUser.getInUserType().getD_user_type(), ""))
                    .nameNo(dictionaryService.getNoValue(DictionaryName.user_type,
                            inUser.getInUserType().getD_user_type(), ""))
                    .build())
                .programs(inUser.getInPrograms().stream().map(AdminUserProgramService::inProgramToDto).collect(Collectors.toList()))
                .build();
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }

    UserResponseDTO findOne(Long id) {
        final InUser inUser = inUserRepository.findOne(id);
        if (inUser == null) {
            throw new ResourceNotFoundException("User with id " + id + " not found.");
        }
        return inUserToDto(inUser);
    }

    UserResponseDTO create(UserRequestDTO userRequestDTO) {
        final InUserType inUserTypeDb = userRequestDTO.getType().getId() == null ? null
                : inUserTypeRepository.findOne(userRequestDTO.getType().getId());
        final InUser inUser = new InUser();
        final InUserEmail inUserEmail = new InUserEmail();
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        emailValidator.validate(userRequestDTO.getEmail(), errors);
        if (errors.hasErrors()) {
            throw new UnauthorizedException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        inUserEmail.setLogin(userRequestDTO.getEmail());
        inUserEmail.setUser_name(userRequestDTO.getName());
        inUserEmail.setPassword(passwordEncoder.encode("Qwerty+1"));
        inUser.setInUserType(inUserTypeDb);
        inUser.setInUserEmails(Arrays.asList(inUserEmail));
        final InUser savedInUser = inUserRepository.save(inUser);
        inUserEmail.setInUser(savedInUser);
        inUserEmailRepository.save(inUserEmail);
        return inUserToDto(savedInUser);
    }

    UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) {
        final InUser inUser = inUserRepository.findOne(id);
        if (inUser == null) {
            throw new ResourceNotFoundException("User with id " + id + " not found.");
        }
        final InUserType inUserTypeDb = userRequestDTO.getType() == null
                || userRequestDTO.getType().getId() == null ? null
            : inUserTypeRepository.findOne(userRequestDTO.getType().getId());
        inUser.setInUserType(inUserTypeDb);
        if (inUser.getInUserEmails().isEmpty()) {
            final InUserFacebook inUserFacebook = inUser.getInUserFacebooks().get(inUser.getInUserFacebooks().size() - 1);
            inUserFacebook.setUser_name(userRequestDTO.getName());
            inUserFacebookRepository.save(inUserFacebook);
        } else {
            final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
            emailValidator.validate(userRequestDTO.getEmail(), errors);
            if (errors.hasErrors()) {
                throw new UnauthorizedException(errors.getAllErrors().get(0).getDefaultMessage());
            }
            inUser.getInUserEmails().get(inUser.getInUserEmails().size() - 1).setUser_name(userRequestDTO.getName());
            inUser.getInUserEmails().get(inUser.getInUserEmails().size() - 1).setLogin(userRequestDTO.getEmail());
            inUserEmailRepository.save(inUser.getInUserEmails().get(inUser.getInUserEmails().size() - 1));
        }
        return inUserToDto(inUser);
    }

    UserResponseDTO delete(Long id) {
        final InUser inUser = inUserRepository.findOne(id);
        if (inUser == null) {
            throw new ResourceNotFoundException("User with id " + id + " not found.");
        }
        final UserResponseDTO userResponseDTO = inUserToDto(inUser);
        inUserRepository.delete(id);
        return userResponseDTO;
    }
}
