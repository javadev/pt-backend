package com.github.pt.reportworkout;

import com.github.pt.programs.InWorkoutItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface InWorkoutItemRepository extends JpaRepository<InWorkoutItem, Long> {

    List<InWorkoutItem> findById(Long id);

}
