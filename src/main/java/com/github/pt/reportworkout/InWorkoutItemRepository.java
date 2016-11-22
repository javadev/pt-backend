package com.github.pt.reportworkout;

import com.github.pt.programs.InWorkoutItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InWorkoutItemRepository extends JpaRepository<InWorkoutItem, Long> {

    List<InWorkoutItem> findById(Long id);

}
