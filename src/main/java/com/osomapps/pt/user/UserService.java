package com.osomapps.pt.user;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.token.InUserLoginRepository;
import com.osomapps.pt.token.InUserLogoutRepository;
import com.osomapps.pt.token.InUserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final InUserRepository inUserRepository;
    private final InUserLoginRepository inUserLoginRepository;
    private final InUserLogoutRepository inUserLogoutRepository;

    @Autowired
    UserService(InUserRepository inUserRepository,
            InUserLoginRepository inUserLoginRepository,
            InUserLogoutRepository inUserLogoutRepository) {
        this.inUserRepository = inUserRepository;
        this.inUserLoginRepository = inUserLoginRepository;
        this.inUserLogoutRepository = inUserLogoutRepository;
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
        inUser.setUpdated(LocalDateTime.now());
        inUserRepository.save(inUser);
    }
}
