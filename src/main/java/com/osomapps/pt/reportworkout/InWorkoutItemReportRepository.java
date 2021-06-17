package com.osomapps.pt.reportworkout;

import com.osomapps.pt.programs.InWorkoutItemReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
interface InWorkoutItemReportRepository extends JpaRepository<InWorkoutItemReport, Long> {}
