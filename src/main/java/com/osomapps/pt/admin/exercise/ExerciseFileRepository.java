package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.exercises.ExerciseFile;
import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseFileRepository extends JpaRepository<ExerciseFile, Long> {
}
