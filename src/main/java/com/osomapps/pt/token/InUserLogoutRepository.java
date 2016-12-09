package com.osomapps.pt.token;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InUserLogoutRepository extends JpaRepository<InUserLogout, Long> {
    
    List<InUserLogout> findByToken(String token);

}
