package com.github.pt.token;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface InUserLogoutRepository extends JpaRepository<InUserLogout, Long> {
    
    List<InUserLogout> findByToken(String token);

}
