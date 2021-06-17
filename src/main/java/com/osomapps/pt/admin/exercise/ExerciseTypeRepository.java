package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.exercises.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseTypeRepository extends JpaRepository<ExerciseType, Long> {}
