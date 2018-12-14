package com.osomapps.pt.reportworkout;

import com.osomapps.pt.programs.InWorkoutItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InWorkoutItemRepository extends JpaRepository<InWorkoutItem, Long> {
}
