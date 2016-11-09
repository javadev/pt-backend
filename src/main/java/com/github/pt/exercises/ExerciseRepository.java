package com.github.pt.exercises;

import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
