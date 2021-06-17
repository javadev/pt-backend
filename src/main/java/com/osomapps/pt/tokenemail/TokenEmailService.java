package com.osomapps.pt.tokenemail;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.token.InUserLoginRepository;
import com.osomapps.pt.token.InUserLogout;
import com.osomapps.pt.token.InUserLogoutRepository;
import com.osomapps.pt.token.InUserRepository;
import com.osomapps.pt.user.UserGoalResponseDTO;
import com.osomapps.pt.user.UserLevel;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class TokenEmailService {

    private final InUserRepository inUserRepository;
    private final InUserEmailRepository inUserEmailRepository;
    private final InUserLoginRepository inUserLoginRepository;
    private final InUserLogoutRepository inUserLogoutRepository;
    private final PasswordEncoder passwordEncoder;

    TokenEmailService(
            InUserRepository inUserRepository,
            InUserEmailRepository inUserEmailRepository,
            InUserLoginRepository inUserLoginRepository,
            InUserLogoutRepository inUserLogoutRepository,
            PasswordEncoder passwordEncoder) {
        this.inUserRepository = inUserRepository;
        this.inUserEmailRepository = inUserEmailRepository;
        this.inUserLoginRepository = inUserLoginRepository;
        this.inUserLogoutRepository = inUserLogoutRepository;
        this.passwordEncoder = passwordEncoder;
    }

    Pair<Boolean, InUserEmail> readOrCreateInUserEmail(TokenEmailRequestDTO tokenEmailRequest) {
        final InUserEmail inUserEmail;
        final boolean isNewLogin;
        final String email = tokenEmailRequest.getEmail().toLowerCase().trim();
        final List<InUserEmail> inUserEmails = inUserEmailRepository.findByLogin(email);
        if (inUserEmails.isEmpty()) {
            throw new UnauthorizedException("E-mail is not registered");
        } else {
            inUserEmail = inUserEmails.get(inUserEmails.size() - 1);
            if (!passwordEncoder.matches(
                    tokenEmailRequest.getPassword(), inUserEmail.getPassword())) {
                throw new UnauthorizedException("Wrong password");
            }
            final List<InUserLogout> inUserLogouts =
                    inUserLogoutRepository.findByToken(
                            inUserEmail
                                    .getInUser()
                                    .getInUserLogins()
                                    .get(inUserEmail.getInUser().getInUserLogins().size() - 1)
                                    .getToken());
            isNewLogin = !inUserLogouts.isEmpty();
        }
        return Pair.of(isNewLogin, inUserEmail);
    }

    TokenEmailResponseDTO createOrReadNewToken(
            TokenEmailRequestDTO tokenRequest, String remoteAddr) {
        final Pair<Boolean, InUserEmail> inUserEmailData = readOrCreateInUserEmail(tokenRequest);
        final boolean isNewLogin = inUserEmailData.getFirst();
        final InUserEmail inUserEmail = inUserEmailData.getSecond();
        final InUserLogin inUserLogin;
        if (isNewLogin) {
            inUserLogin = new InUserLogin();
        } else {
            inUserLogin =
                    inUserEmail
                            .getInUser()
                            .getInUserLogins()
                            .get(inUserEmail.getInUser().getInUserLogins().size() - 1);
        }
        final InUser inUser = inUserEmail.getInUser();
        inUser.getInUserEmails().add(inUserEmail);
        inUser.getInUserLogins().add(inUserLogin);
        final InUser savedInUser = inUserRepository.save(inUser);
        inUserLogin.setInUser(savedInUser);
        inUserLogin.setIp_address(remoteAddr);
        inUserLoginRepository.saveAndFlush(inUserLogin);
        inUserEmail.setInUser(savedInUser);
        inUserEmailRepository.save(inUserEmail);
        final TokenEmailResponseDTO tokenEmailResponseDTO = new TokenEmailResponseDTO();
        tokenEmailResponseDTO.setToken(inUserLogin.getToken());
        final UserSignupResponseDTO user = new UserSignupResponseDTO();
        user.setId(inUserEmail.getInUser().getId());
        user.setName(inUserEmail.getUser_name());
        user.setEmail(inUserEmail.getLogin());
        user.setGender(inUserEmail.getInUser().getD_sex());
        user.setAge(
                inUserEmail.getInUser().getAge() == null
                        ? null
                        : inUserEmail.getInUser().getAge().intValue());
        user.setBirthday(inUserEmail.getInUser().getBirthday());
        user.setAvatar_dataurl(inUserEmail.getInUser().getAvatar_dataurl());
        if (inUser.getD_level() != null) {
            user.setLevel(UserLevel.of(Integer.parseInt(inUser.getD_level())));
        }
        user.setGoals(
                inUser.getInUserGoals().stream()
                        .map(
                                inUserGoal -> {
                                    Map<String, Integer> map = null;
                                    try {
                                        map =
                                                inUserGoal.getGoal_value() == null
                                                        ? null
                                                        : new ObjectMapper()
                                                                .readValue(
                                                                        inUserGoal.getGoal_value(),
                                                                        new TypeReference<
                                                                                Map<
                                                                                        String,
                                                                                        Integer>>() {});
                                    } catch (IOException ex) {
                                    }
                                    return new UserGoalResponseDTO()
                                            .setId(inUserGoal.getGoalId())
                                            .setValues(map);
                                })
                        .collect(Collectors.toList()));
        user.setHeight(inUser.getHeight() == null ? null : inUser.getHeight().longValue());
        user.setWeight(inUser.getWeight() == null ? null : inUser.getWeight().longValue());
        tokenEmailResponseDTO.setUser(user);
        return tokenEmailResponseDTO;
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
