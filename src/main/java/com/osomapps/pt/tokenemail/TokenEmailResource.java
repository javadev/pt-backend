package com.osomapps.pt.tokenemail;

import com.osomapps.pt.token.InUser;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/token-email")
class TokenEmailResource {

    private final TokenEmailService tokenEmailService;
    
    @Autowired
    TokenEmailResource(TokenEmailService tokenEmailService) {
        this.tokenEmailService = tokenEmailService;
    }

    @GetMapping
    List<InUser> list() {
        return Collections.emptyList();
    }

    @PostMapping
    TokenEmailResponseDTO create(@RequestBody TokenEmailRequestDTO tokenEmailRequest, HttpServletRequest request) {
        return tokenEmailService.createOrReadNewToken(tokenEmailRequest, request.getRemoteAddr());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@RequestHeader(value = "X-Token") String token, HttpServletRequest request) {
        tokenEmailService.deleteToken(token, request.getRemoteAddr());
    }
}
