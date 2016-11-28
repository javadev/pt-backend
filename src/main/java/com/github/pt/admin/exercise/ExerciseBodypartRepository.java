package com.github.pt.admin.exercise;

import com.github.pt.exercises.ExerciseBodypart;
import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseBodypartRepository extends JpaRepository<ExerciseBodypart, Long> {
}
