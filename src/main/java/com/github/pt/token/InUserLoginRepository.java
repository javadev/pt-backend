package com.github.pt.token;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface InUserLoginRepository extends JpaRepository<InUserLogin, Long> {

    List<InUserLogin> findByToken(String token);

}
