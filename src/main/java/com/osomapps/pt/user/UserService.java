package com.osomapps.pt.user;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.token.InUserLoginRepository;
import com.osomapps.pt.token.InUserLogoutRepository;
import com.osomapps.pt.token.InUserRepository;
import com.osomapps.pt.tokenemail.DataurlValidator;
import com.osomapps.pt.tokenemail.NameValidator;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;

@Service
public class UserService {
    private final InUserRepository inUserRepository;
    private final InUserLoginRepository inUserLoginRepository;
    private final InUserLogoutRepository inUserLogoutRepository;
    private final DataurlValidator dataurlValidator;
    private final NameValidator nameValidator;

    @Autowired
    UserService(InUserRepository inUserRepository,
            InUserLoginRepository inUserLoginRepository,
            InUserLogoutRepository inUserLogoutRepository,
            DataurlValidator dataurlValidator,
            NameValidator nameValidator) {
        this.inUserRepository = inUserRepository;
        this.inUserLoginRepository = inUserLoginRepository;
        this.inUserLogoutRepository = inUserLogoutRepository;
        this.dataurlValidator = dataurlValidator;
        this.nameValidator = nameValidator;
    }

    public InUserLogin checkUserToken(String token) {
        final List<InUserLogin> inUserLogins = inUserLoginRepository.findByToken(token);
        if (inUserLogins.isEmpty()) {
            throw new ResourceNotFoundException("Token not found " + token);
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

    UserResponseDTO findOne(String token) {
        final InUser inUser = checkUserToken(token).getInUser();
        final UserResponseDTO userResponse = new UserResponseDTO();
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
        userResponse.setAvatar_dataurl(inUser.getAvatar_dataurl());
        userResponse.setName(getUserName(inUser).orElse("?"));
        return userResponse;        
    }

    void updateUser(String token, UserRequestDTO userRequest) {
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
        inUser.setAvatar_dataurl(userRequest.getAvatar_dataurl());
        inUser.setUpdated(LocalDateTime.now());
        inUserRepository.save(inUser);
    }
}
