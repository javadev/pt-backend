package com.github.pt.admin.exercise;

import com.github.pt.exercises.ExerciseCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory, Long> {

    List<ExerciseCategory> findBydExerciseCategoryName(String categoryName);

}
