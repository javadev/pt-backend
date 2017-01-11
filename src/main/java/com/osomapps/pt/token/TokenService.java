package com.osomapps.pt.token;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.user.UserGoalResponseDTO;
import com.osomapps.pt.user.UserLevel;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
class TokenService {
    private final InUserRepository inUserRepository;
    private final InUserLoginRepository inUserLoginRepository;
    private final InUserLogoutRepository inUserLogoutRepository;
    private final InUserFacebookRepository inUserFacebookRepository;
    private final FacebookService facebookService;

    @Autowired
    TokenService(InUserRepository inUserRepository,
            InUserLoginRepository inUserLoginRepository,
            InUserLogoutRepository inUserLogoutRepository,
            InUserFacebookRepository inUserFacebookRepository,
            FacebookService facebookService) {
        this.inUserRepository = inUserRepository;
        this.inUserLoginRepository = inUserLoginRepository;
        this.inUserLogoutRepository = inUserLogoutRepository;
        this.inUserFacebookRepository = inUserFacebookRepository;
        this.facebookService = facebookService;
    }

    Pair<Boolean, InUserFacebook> readOrCreateInUserFacebook(TokenRequestDTO tokenRequest) {
        final InUserFacebook inUserFacebook;
        final boolean isNewLogin;
        final List<InUserFacebook> inUserFacebooks = inUserFacebookRepository.findByTokenAndDeviceId(
            tokenRequest.getFacebook_token(), tokenRequest.getDevice_id());
        if (inUserFacebooks.isEmpty()) {
            inUserFacebook = new InUserFacebook();
            inUserFacebook.setToken(tokenRequest.getFacebook_token());
            inUserFacebook.setDeviceId(tokenRequest.getDevice_id());
            isNewLogin = true;
        } else {
            final InUserFacebook inUserFacebookOld = inUserFacebooks.get(inUserFacebooks.size() - 1);
            final List<InUserLogout> inUserLogouts = inUserLogoutRepository.findByToken(
                    inUserFacebookOld.getInUser().getInUserLogins().get(
                    inUserFacebookOld.getInUser().getInUserLogins().size() - 1).getToken());
            if (inUserLogouts.isEmpty()) {
                inUserFacebook = inUserFacebookOld;
                isNewLogin = false;
            } else {
                inUserFacebook = new InUserFacebook();
                inUserFacebook.setToken(tokenRequest.getFacebook_token());
                inUserFacebook.setDeviceId(tokenRequest.getDevice_id());                
                isNewLogin = true;
            }
        }
        return Pair.of(isNewLogin, inUserFacebook);
    }

    TokenResponseDTO createOrReadNewToken(TokenRequestDTO tokenRequest, String remoteAddr) {
        final InUser inUser;
        final Pair<Boolean, InUserFacebook> inUserFacebookData = readOrCreateInUserFacebook(tokenRequest);
        final boolean isNewLogin = inUserFacebookData.getFirst();
        InUserFacebook inUserFacebook = inUserFacebookData.getSecond();
        FacebookResponse facebookResponse =
            facebookService.getProfileNameAndId(tokenRequest.getFacebook_token())
                .orElseThrow(() -> new ResourceNotFoundException("Can't load data from facebook"));
        Optional<String> pictureUrl = 
            facebookService.getProfilePictureUrl(tokenRequest.getFacebook_token());
        inUserFacebook.setUserId(facebookResponse.getId());
        if (inUserFacebook.getUser_name() == null) {
            inUserFacebook.setUser_name(facebookResponse.getName());
        }
        inUserFacebook.setPicture_url(pictureUrl.orElse(null));
        inUserFacebook.setBirthday(facebookResponse.getBirthday());
        final List<InUserFacebook> inUserFacebooksNew = inUserFacebookRepository.findByUserId(
            facebookResponse.getId());
        final InUserLogin inUserLogin;
        if (isNewLogin) {
            inUserLogin = new InUserLogin();
        } else {
            inUserLogin = inUserFacebook.getInUser().getInUserLogins().get(
                    inUserFacebook.getInUser().getInUserLogins().size() - 1);
        }
        if (inUserFacebooksNew.isEmpty()) {
            inUser = new InUser();
            inUser.setInUserFacebooks(Arrays.asList(inUserFacebook));
            inUser.setInUserLogins(Arrays.asList(inUserLogin)); 
        } else {
            inUser = inUserFacebooksNew.get(inUserFacebooksNew.size() - 1).getInUser();
            inUser.getInUserFacebooks().add(inUserFacebook);
            inUser.getInUserLogins().add(inUserLogin);
        }
        inUser.setD_sex(facebookResponse.getGender());
        inUser.setAge(facebookResponse.getAge().floatValue());
        inUser.setBirthday(facebookResponse.getBirthday());
        inUser.setUpdated(LocalDateTime.now());
        final InUser savedInUser = inUserRepository.save(inUser);
        inUserLogin.setInUser(savedInUser);
        inUserLogin.setIp_address(remoteAddr);
        inUserLoginRepository.saveAndFlush(inUserLogin);
        inUserFacebook.setInUser(inUser);
        inUserFacebookRepository.save(inUserFacebook);
        final TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setToken(inUserLogin.getToken());
        final UserResponseDTO user = new UserResponseDTO();
        user.setId(inUserFacebook.getInUser().getId());
        user.setName(inUserFacebook.getUser_name());
        user.setAvatar(inUserFacebook.getPicture_url());
        user.setGender(facebookResponse.getGender());
        user.setAge(facebookResponse.getAge());
        user.setBirthday(inUser.getBirthday());
        user.setAvatar_dataurl(inUser.getAvatar_dataurl());
        if (inUser.getD_level() != null) {
            user.setLevel(UserLevel.of(Integer.parseInt(inUser.getD_level())));
        }
        user.setGoals(inUser.getInUserGoals().stream().map(inUserGoal -> {
            Map<String, Integer> map = null;
            try {
                 map = inUserGoal.getGoal_value() == null ? null : new ObjectMapper()
                         .readValue(inUserGoal.getGoal_value(),
                        new TypeReference<Map<String, Integer>>(){});
            } catch (IOException ex) {
            }
            return new UserGoalResponseDTO().setId(inUserGoal.getGoalId()).setValues(map);
        }).collect(Collectors.toList()));
        user.setHeight(inUser.getHeight() == null ? null : inUser.getHeight().longValue());
        user.setWeight(inUser.getWeight() == null ? null : inUser.getWeight().longValue());
        tokenResponseDTO.setUser(user);
        return tokenResponseDTO;        
    }

    void deleteToken(String token, String remoteAddr) {
        final List<InUserLogin> inUserLogins = inUserLoginRepository.findByToken(token);
        if (!inUserLogins.isEmpty()) {
            final List<InUserLogout> inUserLogouts = inUserLogoutRepository.findByToken(token);
            if (!inUserLogouts.isEmpty()) {
                throw new UnauthorizedException("Invalid token");
            }
            InUserLogout inUserLogout = new InUserLogout();
            inUserLogout.setToken(token);
            inUserLogout.setInUser(inUserLogins.get(inUserLogins.size() - 1).getInUser());
            inUserLogout.setIp_address(remoteAddr);
            inUserLogoutRepository.saveAndFlush(inUserLogout);
        } else {
            throw new UnauthorizedException("Token not found");
        }
    }
}
