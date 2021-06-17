package com.osomapps.pt.programs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface InProgramRepository extends JpaRepository<InProgram, Long> {}
