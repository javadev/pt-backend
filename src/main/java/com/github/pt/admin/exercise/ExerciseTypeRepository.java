package com.github.pt.admin.exercise;

import com.github.pt.exercises.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseTypeRepository extends JpaRepository<ExerciseType, Long> {
}
