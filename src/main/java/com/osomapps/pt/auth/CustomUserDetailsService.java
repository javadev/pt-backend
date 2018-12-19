package com.osomapps.pt.auth;

import com.osomapps.pt.admin.ptuser.PtUser;
import com.osomapps.pt.admin.ptuser.PtUserRepository;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final PtUserRepository ptUserRepository;
    
    CustomUserDetailsService(PtUserRepository ptUserRepository) {
        this.ptUserRepository = ptUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        final List<PtUser> ptUsers = ptUserRepository.findByLogin(username);
        if (ptUsers.isEmpty()) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        }
        return new CustomUserDetails(ptUsers.get(ptUsers.size() - 1));
    }

}
