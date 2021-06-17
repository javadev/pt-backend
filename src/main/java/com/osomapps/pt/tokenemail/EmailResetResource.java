package com.osomapps.pt.tokenemail;

import com.osomapps.pt.token.InUser;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email/reset")
class EmailResetResource {

    private final TokenEmailSignupService tokenEmailSignupService;

    @Autowired
    EmailResetResource(TokenEmailSignupService tokenEmailSignupService) {
        this.tokenEmailSignupService = tokenEmailSignupService;
    }

    @GetMapping
    List<InUser> list() {
        return Collections.emptyList();
    }

    @GetMapping("{resetToken}")
    String create(@PathVariable String resetToken) {
        final Optional<String> newPassword = tokenEmailSignupService.resetToken(resetToken);
        if (newPassword.isPresent()) {
            return "Your password was reseted to the " + newPassword.get();
        }
        return "";
    }
}
