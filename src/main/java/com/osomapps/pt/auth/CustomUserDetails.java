package com.osomapps.pt.auth;

import com.osomapps.pt.admin.ptuser.PtUser;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

class CustomUserDetails implements UserDetails {

    private final PtUser ptUser;

    CustomUserDetails(PtUser ptUser) {
        this.ptUser = ptUser;
    }

    public PtUser getPtUser() {
        return ptUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(
                ptUser.getPtRoles().stream()
                        .map(ptRole -> ptRole.getName())
                        .collect(Collectors.joining(",")));
    }

    @Override
    public String getPassword() {
        return ptUser.getPassword();
    }

    @Override
    public String getUsername() {
        return ptUser.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !ptUser.getIs_deleted();
    }
}
