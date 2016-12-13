package com.osomapps.pt.tokenemail;

import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.token.InUserLoginRepository;
import com.osomapps.pt.token.InUserRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;

@Service
class TokenEmailSignupService {

    private final SendEmailService sendEmailService;
    private final InUserEmailRepository inUserEmailRepository;
    private final EmailValidator emailValidator;
    private final PasswordEncoder passwordEncoder;
    private final InUserRepository inUserRepository;
    private final InUserLoginRepository inUserLoginRepository;
    private final DataurlValidator dataurlValidator;

    TokenEmailSignupService(SendEmailService sendEmailService,
            InUserEmailRepository inUserEmailRepository,
            EmailValidator emailValidator,
            PasswordEncoder passwordEncoder,
            InUserRepository inUserRepository,
            InUserLoginRepository inUserLoginRepository,
            DataurlValidator dataurlValidator) {
        this.sendEmailService = sendEmailService;
        this.inUserEmailRepository = inUserEmailRepository;
        this.emailValidator = emailValidator;
        this.passwordEncoder = passwordEncoder;
        this.inUserRepository = inUserRepository;
        this.inUserLoginRepository = inUserLoginRepository;
        this.dataurlValidator = dataurlValidator;
    }

    InUserEmail createInUserEmail(TokenEmailSignupRequestDTO tokenEmailSignupRequestDTO) {
        final InUserEmail inUserEmail;
        final String email = tokenEmailSignupRequestDTO.getUser().getEmail().toLowerCase().trim();
        final List<InUserEmail> inUserEmails = inUserEmailRepository.findByLogin(email);
        if (inUserEmails.isEmpty()) {
            inUserEmail = new InUserEmail();
            final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
            emailValidator.validate(email, errors);
            if (errors.hasErrors()) {
                throw new UnauthorizedException(errors.getAllErrors().get(0).getDefaultMessage());
            }
            final MapBindingResult errorsDataurl = new MapBindingResult(new HashMap<>(), String.class.getName());
            dataurlValidator.validate(tokenEmailSignupRequestDTO.getUser().getAvatar_dataurl(), errorsDataurl);
            if (errorsDataurl.hasErrors()) {
                throw new UnauthorizedException(errorsDataurl.getAllErrors().get(0).getDefaultMessage());
            }
            inUserEmail.setLogin(email);
            inUserEmail.setUser_name(tokenEmailSignupRequestDTO.getUser().getName());
            inUserEmail.setDevice_id(tokenEmailSignupRequestDTO.getDevice_id());
            inUserEmail.setPassword(passwordEncoder.encode(tokenEmailSignupRequestDTO.getPassword()));
        } else {
            throw new UnauthorizedException("User already registered");
        }
        return inUserEmail;
    }

    TokenEmailSignupResponseDTO createNewToken(TokenEmailSignupRequestDTO tokenEmailSignupRequestDTO, String remoteAddr) {
        final InUserEmail inUserEmail = createInUserEmail(tokenEmailSignupRequestDTO);
        final InUserLogin inUserLogin = new InUserLogin();
        final InUser inUser = new InUser();
        inUser.setInUserEmails(Arrays.asList(inUserEmail));
        inUser.setInUserLogins(Arrays.asList(inUserLogin));
        inUser.setAvatar_dataurl(tokenEmailSignupRequestDTO.getUser().getAvatar_dataurl());
        final InUser savedInUser = inUserRepository.save(inUser);
        inUserLogin.setInUser(savedInUser);
        inUserLogin.setIp_address(remoteAddr);
        inUserLoginRepository.saveAndFlush(inUserLogin);
        inUserEmail.setInUser(savedInUser);
        new Thread(() -> {
            sendEmailService.send(inUserEmail);
        }, "Send-email").start();
        inUserEmailRepository.save(inUserEmail);
        final UserSignupResponseDTO user = new UserSignupResponseDTO();
        user.setId(inUserEmail.getInUser().getId());
        user.setName(inUserEmail.getUser_name());
        user.setEmail(inUserEmail.getLogin());
        user.setAvatar_dataurl(inUser.getAvatar_dataurl());
        return new TokenEmailSignupResponseDTO().setToken(inUserLogin.getToken()).setUser(user);
    }

}