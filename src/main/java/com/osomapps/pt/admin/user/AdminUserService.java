package com.osomapps.pt.admin.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.goals.Goal;
import com.osomapps.pt.goals.GoalRepository;
import com.osomapps.pt.goals.InUserGoalRepository;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserFacebook;
import com.osomapps.pt.token.InUserFacebookRepository;
import com.osomapps.pt.token.InUserGoal;
import com.osomapps.pt.token.InUserRepository;
import com.osomapps.pt.tokenemail.EmailValidator;
import com.osomapps.pt.tokenemail.InUserEmail;
import com.osomapps.pt.tokenemail.InUserEmailRepository;
import java.io.IOException;
import java.util.ArrayList;
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
    private final InUserGoalRepository inUserGoalRepository;
    private final GoalRepository goalRepository;
    
    AdminUserService(InUserRepository inUserRepository,
            InUserEmailRepository inUserEmailRepository,
            InUserFacebookRepository inUserFacebookRepository,
            InUserTypeRepository inUserTypeRepository,
            PasswordEncoder passwordEncoder,
            EmailValidator emailValidator,
            DictionaryService dictionaryService,
            InUserGoalRepository inUserGoalRepository,
            GoalRepository goalRepository) {
        this.inUserRepository = inUserRepository;
        this.inUserEmailRepository = inUserEmailRepository;
        this.inUserFacebookRepository = inUserFacebookRepository;
        this.inUserTypeRepository = inUserTypeRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailValidator = emailValidator;
        this.dictionaryService = dictionaryService;
        this.inUserGoalRepository = inUserGoalRepository;
        this.goalRepository = goalRepository;
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
                .gender(inUser.getD_sex())
                .level(inUser.getD_level() == null ? null : Integer.parseInt(inUser.getD_level()))
                .goals(inUser.getInUserGoals() == null ? null : inUser.getInUserGoals().stream().map(goal ->
                    new UserGoalResponseDTO()
                            .setId(goal.getGoalId())
                        .setTitle(dictionaryService.getEnValue(DictionaryName.goal_title, goal.getD_goal_title(), null))
                        .setTitle2(dictionaryService.getEnValue(DictionaryName.goal_title_2, goal.getD_goal_title_2(), null))
                ).collect(Collectors.toList()))
                .type(inUser.getInUserType() == null ? null : UserTypeResponseDTO.builder()
                    .id(inUser.getInUserType().getId())
                    .nameEn(dictionaryService.getEnValue(DictionaryName.user_type,
                            inUser.getInUserType().getD_user_type(), ""))
                    .nameNo(dictionaryService.getNoValue(DictionaryName.user_type,
                            inUser.getInUserType().getD_user_type(), ""))
                    .build())
                .programs(inUser.getInPrograms() == null ? null : inUser.getInPrograms().stream()
                    .map(AdminUserProgramService::inProgramToDto).collect(Collectors.toList()))
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
        inUser.setD_sex(userRequestDTO.getGender());
        inUser.setD_level(userRequestDTO.getLevel() == null ? null : "" + userRequestDTO.getLevel());
        setupGoals(userRequestDTO, inUser);

        final InUser savedInUser = inUserRepository.save(inUser);
        inUserEmail.setInUser(savedInUser);
        inUserEmailRepository.save(inUserEmail);
        return inUserToDto(savedInUser);
    }

    private void setupGoals(UserRequestDTO userRequestDTO, final InUser inUser) throws UnauthorizedException {
        if (userRequestDTO.getGoals() != null) {
            if (userRequestDTO.getGoals().size() > 2) {
                throw new UnauthorizedException("Amount of goals must be not more than 2");
            }
            inUserGoalRepository.delete(inUser.getInUserGoals());
            inUser.setInUserGoals(new ArrayList<>());
            userRequestDTO.getGoals().forEach((userGoalRequestDTO) -> {
                Goal goal = goalRepository.findOne(userGoalRequestDTO.getId());
                if (goal == null) {
                    throw new UnauthorizedException("Goal with id " + userGoalRequestDTO.getId() + " not found");
                }
                String value = null;
                try {
                    value = new ObjectMapper().writeValueAsString(userGoalRequestDTO.getValues());
                } catch (IOException ex) {
                }
                inUser.getInUserGoals().add(inUserGoalRepository.save(new InUserGoal()
                        .setGoalId(userGoalRequestDTO.getId())
                        .setD_goal_title(goal.getDGoalTitle())
                        .setD_goal_title_2(goal.getDGoalTitle2())
                        .setGoal_value(value)));
            });
        }
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
        inUser.setD_sex(userRequestDTO.getGender());
        inUser.setD_level(userRequestDTO.getLevel() == null ? null : "" + userRequestDTO.getLevel());
        setupGoals(userRequestDTO, inUser);
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
