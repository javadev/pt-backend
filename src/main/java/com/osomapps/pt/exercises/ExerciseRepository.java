package com.osomapps.pt.exercises;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByExerciseId(Integer exerciseId);

}
