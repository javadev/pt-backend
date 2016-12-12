package com.osomapps.pt.auth;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AuthUserService {

    private final SecurityContextHelper securityContextHelper;

    AuthUserService(SecurityContextHelper securityContextHelper) {
        this.securityContextHelper = securityContextHelper;
    }

    AuthUserResponseDTO findOne() {
        final CustomUserDetails userDetails = securityContextHelper.getUserDetails();
        if (userDetails == null) {
            return new AuthUserResponseDTO();
        }
        return new AuthUserResponseDTO()
                .setId(userDetails.getPtUser().getId())
                .setName(userDetails.getUsername())
                .setLogin(userDetails.getPtUser().getLogin())
                .setPermissions(userDetails.getPtUser().getPtRoles().stream()
                        .map(ptRole -> ptRole.getName()).collect(Collectors.toList()));
    }
}
