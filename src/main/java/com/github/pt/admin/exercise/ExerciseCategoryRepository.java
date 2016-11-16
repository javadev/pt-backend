package com.github.pt.admin.exercise;

import com.github.pt.exercises.ExerciseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory, Long> {
}
