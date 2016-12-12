package com.osomapps.pt.tokenemail;

import com.osomapps.pt.token.InUser;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api/v1/token-email-signup")
class TokenEmailSignupResource {

    private final TokenEmailSignupService tokenEmailSignupService;
    
    @Autowired
    TokenEmailSignupResource(TokenEmailSignupService tokenEmailSignupService) {
        this.tokenEmailSignupService = tokenEmailSignupService;
    }

    @GetMapping
    List<InUser> list() {
        return Collections.emptyList();
    }

    @PostMapping
    TokenEmailSignupResponseDTO create(@RequestBody TokenEmailSignupRequestDTO tokenEmailSignupRequest, HttpServletRequest request) {
        return tokenEmailSignupService.createNewToken(tokenEmailSignupRequest, request.getRemoteAddr());
    }
}
