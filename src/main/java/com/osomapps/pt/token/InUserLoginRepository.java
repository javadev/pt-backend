package com.osomapps.pt.token;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InUserLoginRepository extends JpaRepository<InUserLogin, Long> {

    List<InUserLogin> findByToken(String token);

}
