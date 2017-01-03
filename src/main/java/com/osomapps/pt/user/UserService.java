package com.osomapps.pt.user;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.goals.Goal;
import com.osomapps.pt.goals.GoalRepository;
import com.osomapps.pt.goals.InUserGoalRepository;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserGoal;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.token.InUserLoginRepository;
import com.osomapps.pt.token.InUserLogoutRepository;
import com.osomapps.pt.token.InUserRepository;
import com.osomapps.pt.tokenemail.DataurlValidator;
import com.osomapps.pt.tokenemail.NameValidator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;

@Service
public class UserService {
    private final InUserRepository inUserRepository;
    private final InUserLoginRepository inUserLoginRepository;
    private final InUserLogoutRepository inUserLogoutRepository;
    private final GoalRepository goalRepository;
    private final InUserGoalRepository inUserGoalRepository;
    private final DataurlValidator dataurlValidator;
    private final NameValidator nameValidator;

    @Autowired
    UserService(InUserRepository inUserRepository,
            InUserLoginRepository inUserLoginRepository,
            InUserLogoutRepository inUserLogoutRepository,
            GoalRepository goalRepository,
            InUserGoalRepository inUserGoalRepository,
            DataurlValidator dataurlValidator,
            NameValidator nameValidator) {
        this.inUserRepository = inUserRepository;
        this.inUserLoginRepository = inUserLoginRepository;
        this.inUserLogoutRepository = inUserLogoutRepository;
        this.goalRepository = goalRepository;
        this.inUserGoalRepository = inUserGoalRepository;
        this.dataurlValidator = dataurlValidator;
        this.nameValidator = nameValidator;
    }

    public InUserLogin checkUserToken(String token) {
        final List<InUserLogin> inUserLogins = inUserLoginRepository.findByToken(token);
        if (inUserLogins.isEmpty()) {
            throw new UnauthorizedException("Token not found");
        } else {
            if (!inUserLogoutRepository.findByToken(token).isEmpty()) {
                throw new UnauthorizedException("Invalid token");
            }
        }
        return inUserLogins.get(inUserLogins.size() - 1);
    }

    private Optional<String> getUserName(InUser inUser) {
        final Optional<String> userName;
        if (inUser.getInUserFacebooks() == null || inUser.getInUserFacebooks().isEmpty()) {
            if (inUser.getInUserEmails() == null || inUser.getInUserEmails().isEmpty()) {
                userName = Optional.empty();
            } else {
                userName = Optional.of(inUser.getInUserEmails().get(inUser.getInUserEmails().size() - 1).getUser_name());
            }
        } else {
            userName = Optional.of(inUser.getInUserFacebooks().get(inUser.getInUserFacebooks().size() - 1).getUser_name());
        }
        return userName;
    }

    private void setUserName(InUser inUser, String userName) {
        if (inUser.getInUserFacebooks() == null || inUser.getInUserFacebooks().isEmpty()) {
            if (inUser.getInUserEmails() != null && !inUser.getInUserEmails().isEmpty()) {
                inUser.getInUserEmails().get(inUser.getInUserEmails().size() - 1).setUser_name(userName);
            }
        } else {
            inUser.getInUserFacebooks().get(inUser.getInUserFacebooks().size() - 1).setUser_name(userName);
        }
    }
    
    private Optional<String> getAvatar(InUser inUser) {
        if (inUser.getInUserFacebooks() == null || inUser.getInUserFacebooks().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(inUser.getInUserFacebooks()
                .get(inUser.getInUserFacebooks().size() - 1).getPicture_url());
    }

    private Optional<String> getEmail(InUser inUser) {
        if (inUser.getInUserEmails() == null || inUser.getInUserEmails().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(inUser.getInUserEmails()
                .get(inUser.getInUserEmails().size() - 1).getLogin());
    }

    UserResponseDTO findOne(String token) {
        final InUser inUser = checkUserToken(token).getInUser();
        return userToDto(inUser);        
    }

    private UserResponseDTO userToDto(final InUser inUser) {
        final UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setId(inUser.getId());
        userResponse.setGender(inUser.getD_sex());
        if (inUser.getAge() != null) {
            userResponse.setAge(inUser.getAge().longValue());
        }
        if (inUser.getHeight() != null) {
            userResponse.setHeight(inUser.getHeight().longValue());
        }
        if (inUser.getWeight() != null) {
            userResponse.setWeight(inUser.getWeight().longValue());
        }
        if (inUser.getD_level() != null) {
            userResponse.setLevel(UserLevel.of(Integer.parseInt(inUser.getD_level())));
        }
        userResponse.setGoals(inUser.getInUserGoals().stream().map(inUserGoal ->
                new UserGoalResponseDTO().setId(inUserGoal.getGoalId()).setValue(inUserGoal.getGoal_value())
        ).collect(Collectors.toList()));
        userResponse.setAvatar_dataurl(inUser.getAvatar_dataurl());
        userResponse.setName(getUserName(inUser).orElse("?"));
        userResponse.setAvatar(getAvatar(inUser).orElse(null));
        userResponse.setEmail(getEmail(inUser).orElse(null));
        userResponse.setBirthday(inUser.getBirthday());
        return userResponse;        
    }

    UserResponseDTO updateUser(String token, UserRequestDTO userRequest) {
        final InUserLogin inUserLogin = checkUserToken(token);
        final InUser inUser = inUserLogin.getInUser();
        if (userRequest.getGender() != null) {
            inUser.setD_sex(userRequest.getGender());
        }
        if (userRequest.getAge() != null) {
            inUser.setAge(userRequest.getAge().floatValue());
        }
        if (userRequest.getHeight() != null) {
            inUser.setHeight(userRequest.getHeight().floatValue());
        }
        if (userRequest.getWeight() != null) {
            inUser.setWeight(userRequest.getWeight().floatValue());
        }
        if (userRequest.getLevel() != null) {
            inUser.setD_level("" + userRequest.getLevel().getLevel());
        }
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        dataurlValidator.validate(userRequest.getAvatar_dataurl(), errors);
        if (errors.hasErrors()) {
            throw new UnauthorizedException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        if (userRequest.getName() != null) {
            final MapBindingResult errorsName = new MapBindingResult(new HashMap<>(), String.class.getName());
            nameValidator.validate(userRequest.getName(), errorsName);
            if (errorsName.hasErrors()) {
                throw new UnauthorizedException(errorsName.getAllErrors().get(0).getDefaultMessage());
            }
            setUserName(inUser, userRequest.getName());
        }
        if (userRequest.getGoals() != null) {
            if (userRequest.getGoals().size() > 2) {
                throw new UnauthorizedException("Amount of goals must be not more than 2");
            }
            inUserGoalRepository.delete(inUser.getInUserGoals());
            inUser.setInUserGoals(new ArrayList<>());
            userRequest.getGoals().forEach((userGoalRequestDTO) -> {
                Goal goal = goalRepository.findOne(userGoalRequestDTO.getId());
                if (goal == null) {
                    throw new UnauthorizedException("Goal with id " + userGoalRequestDTO.getId() + " not found");
                }
                inUser.getInUserGoals().add(inUserGoalRepository.save(new InUserGoal()
                        .setGoalId(userGoalRequestDTO.getId())
                        .setD_goal_title(goal.getDGoalTitle())
                        .setD_goal_title_2(goal.getDGoalTitle2())
                        .setGoal_value(userGoalRequestDTO.getValue())));
            });
        }
        inUser.setAvatar_dataurl(userRequest.getAvatar_dataurl());
        inUser.setUpdated(LocalDateTime.now());
        return userToDto(inUserRepository.save(inUser));
    }
}
