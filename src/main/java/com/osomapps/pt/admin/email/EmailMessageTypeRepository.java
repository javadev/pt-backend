package com.osomapps.pt.admin.email;

import com.osomapps.pt.email.EmailMessageType;
import org.springframework.data.jpa.repository.JpaRepository;

interface EmailMessageTypeRepository extends JpaRepository<EmailMessageType, Long> {
}
