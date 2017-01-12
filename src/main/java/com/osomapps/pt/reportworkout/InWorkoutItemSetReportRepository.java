package com.osomapps.pt.reportworkout;

import com.osomapps.pt.programs.InWorkoutItemSetReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
interface InWorkoutItemSetReportRepository extends JpaRepository<InWorkoutItemSetReport, Long> {

}
