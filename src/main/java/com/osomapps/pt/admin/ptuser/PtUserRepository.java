package com.osomapps.pt.admin.ptuser;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PtUserRepository extends JpaRepository<PtUser, Long> {
    List<PtUser> findByLogin(String login);
}
