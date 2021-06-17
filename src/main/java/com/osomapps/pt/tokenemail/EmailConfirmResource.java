package com.osomapps.pt.tokenemail;

import com.osomapps.pt.token.InUser;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email/confirm")
class EmailConfirmResource {

    private final TokenEmailSignupService tokenEmailSignupService;

    @Autowired
    EmailConfirmResource(TokenEmailSignupService tokenEmailSignupService) {
        this.tokenEmailSignupService = tokenEmailSignupService;
    }

    @GetMapping
    List<InUser> list() {
        return Collections.emptyList();
    }

    @GetMapping("{confirmToken}")
    String create(@PathVariable String confirmToken) {
        if (tokenEmailSignupService.confirmToken(confirmToken)) {
            return "Your e-mail was confirmed";
        }
        return "";
    }
}
