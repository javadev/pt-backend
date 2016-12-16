package com.osomapps.pt.email;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailMessageTypeRepository extends JpaRepository<EmailMessageType, Long> {
    List<EmailMessageType> findByNameOrderByIdDesc(String name);
}
