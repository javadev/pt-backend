package com.osomapps.pt.admin.email;

import com.osomapps.pt.email.EmailMessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

interface EmailMessageTemplateRepository extends JpaRepository<EmailMessageTemplate, Long> {
}
