package com.github.pt.admin.exercise;

import com.github.pt.exercises.ExerciseInput;
import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseInputRepository extends JpaRepository<ExerciseInput, Long> {
}
