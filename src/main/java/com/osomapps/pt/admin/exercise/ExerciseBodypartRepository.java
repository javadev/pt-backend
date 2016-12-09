package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.exercises.ExerciseBodypart;
import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseBodypartRepository extends JpaRepository<ExerciseBodypart, Long> {
}
