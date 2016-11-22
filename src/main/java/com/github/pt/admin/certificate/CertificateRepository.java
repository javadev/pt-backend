package com.github.pt.admin.certificate;

import org.springframework.data.jpa.repository.JpaRepository;

interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
