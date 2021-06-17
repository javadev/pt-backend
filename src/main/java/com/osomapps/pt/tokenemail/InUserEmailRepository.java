package com.osomapps.pt.tokenemail;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InUserEmailRepository extends JpaRepository<InUserEmail, Long> {

    List<InUserEmail> findByLogin(String login);

    List<InUserEmail> findByConfirmToken(String confirmToken);

    List<InUserEmail> findByResetToken(String resetToken);
}
