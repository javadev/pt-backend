package com.github.pt.tokenemail;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.UnauthorizedException;
import com.github.pt.token.InUser;
import com.github.pt.token.InUserLogin;
import com.github.pt.token.InUserLoginRepository;
import com.github.pt.token.InUserLogout;
import com.github.pt.token.InUserLogoutRepository;
import com.github.pt.token.InUserRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.MapBindingResult;

@Service
class TokenEmailService {
    
    private final InUserRepository inUserRepository;
    private final InUserEmailRepository inUserEmailRepository;
    private final InUserLoginRepository inUserLoginRepository;
    private final InUserLogoutRepository inUserLogoutRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;
    
    TokenEmailService(InUserRepository inUserRepository,
            InUserEmailRepository inUserEmailRepository,
            InUserLoginRepository inUserLoginRepository,
            InUserLogoutRepository inUserLogoutRepository,
            PasswordEncoder passwordEncoder,
            EmailValidator emailValidator) {
        this.inUserRepository = inUserRepository;
        this.inUserEmailRepository = inUserEmailRepository;
        this.inUserLoginRepository = inUserLoginRepository;
        this.inUserLogoutRepository = inUserLogoutRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailValidator = emailValidator;
    }

    Pair<Boolean, InUserEmail> readOrCreateInUserEmail(TokenEmailRequestDTO tokenEmailRequest) {
        final InUserEmail inUserEmail;
        final boolean isNewLogin;
        final String email = tokenEmailRequest.getEmail().toLowerCase().trim();
        final List<InUserEmail> inUserEmails = inUserEmailRepository.findByLogin(email);
        if (inUserEmails.isEmpty()) {
            inUserEmail = new InUserEmail();
            inUserEmail.setUser_name(tokenEmailRequest.getName());
            final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
            emailValidator.validate(email, errors);
            if (errors.hasErrors()) {
                throw new UnauthorizedException(errors.getAllErrors().get(0).getDefaultMessage());
            }
            inUserEmail.setLogin(email);
            inUserEmail.setPassword(passwordEncoder.encode(tokenEmailRequest.getPassword()));
            isNewLogin = true;
        } else {
            inUserEmail = inUserEmails.get(inUserEmails.size() - 1);
            if (!passwordEncoder.matches(tokenEmailRequest.getPassword(), inUserEmail.getPassword())) {
                throw new UnauthorizedException("Wrong password");
            }
            final List<InUserLogout> inUserLogouts = inUserLogoutRepository.findByToken(
                    inUserEmail.getInUser().getInUserLogins().get(
                    inUserEmail.getInUser().getInUserLogins().size() - 1).getToken());
            isNewLogin = !inUserLogouts.isEmpty();
        }
        return Pair.of(isNewLogin, inUserEmail);
    }

    TokenEmailResponseDTO createOrReadNewToken(TokenEmailRequestDTO tokenRequest, String remoteAddr) {
        final InUser inUser;
        final Pair<Boolean, InUserEmail> inUserEmailData = readOrCreateInUserEmail(tokenRequest);
        final boolean isNewLogin = inUserEmailData.getFirst();
        final InUserEmail inUserEmail = inUserEmailData.getSecond();
        final InUserLogin inUserLogin;
        if (isNewLogin) {
            inUserLogin = new InUserLogin();
        } else {
            inUserLogin = inUserEmail.getInUser().getInUserLogins().get(
                    inUserEmail.getInUser().getInUserLogins().size() - 1);
        }
        if (inUserEmail.getId() == null) {
            inUser = new InUser();
            inUser.setInUserEmails(Arrays.asList(inUserEmail));
            inUser.setInUserLogins(Arrays.asList(inUserLogin)); 
        } else {
            inUser = inUserEmail.getInUser();
            inUser.getInUserEmails().add(inUserEmail);
            inUser.getInUserLogins().add(inUserLogin);
        }
        final InUser savedInUser = inUserRepository.save(inUser);
        inUserLogin.setInUser(savedInUser);
        inUserLogin.setIp_address(remoteAddr);
        inUserLoginRepository.saveAndFlush(inUserLogin);
        inUserEmail.setInUser(inUser);
        inUserEmailRepository.save(inUserEmail);
        final TokenEmailResponseDTO tokenEmailResponseDTO = new TokenEmailResponseDTO();
        tokenEmailResponseDTO.setToken(inUserLogin.getToken());
        final UserResponseDTO user = new UserResponseDTO();
        user.setId(inUserEmail.getInUser().getId());
        user.setName(inUserEmail.getUser_name());
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
            throw new ResourceNotFoundException("Token not found " + token);
        }
    }

}
