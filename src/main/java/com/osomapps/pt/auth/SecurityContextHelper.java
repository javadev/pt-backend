package com.osomapps.pt.auth;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
class SecurityContextHelper {
    String getPrincipal() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUsername();
    }

    String getCredentials() {
        return SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
    }

    CustomUserDetails getUserDetails() {
        final Object details =
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (details instanceof CustomUserDetails) {
            return (CustomUserDetails) details;
        } else {
            return null;
        }
    }

    Collection<String> getAuthorities() {
        final Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities.stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());
    }
}
