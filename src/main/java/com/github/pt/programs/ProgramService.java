package com.github.pt.programs;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.token.InUserLogin;
import com.github.pt.user.UserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProgramService {
    private final UserService userService;

    @Autowired
    ProgramService(UserService userService) {
        this.userService = userService;
    }

    List<ProgramResponseDTO> getExamples(String token) {
        if (!token.isEmpty()) {
            InUserLogin inUserLogin = userService.checkUserToken(token);
            List<InProgram> inPrograms = inUserLogin.getInUser().getInPrograms();
            if (inPrograms.isEmpty()) {
                return Collections.emptyList();
            }
            return inPrograms.stream().map(ProgramService::createProgramResponseDTO).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
 
    private static ProgramResponseDTO createProgramResponseDTO(InProgram inProgram) {
        ProgramResponseDTO program = new ProgramResponseDTO();
        program.setId(inProgram.getId());
        program.setName(inProgram.getName());
        program.setType(inProgram.getD_program_type() == null ? "personal" : inProgram.getD_program_type());
        program.setWorkouts(new ArrayList<>());
        for (InWorkout inWorkout : inProgram.getInWorkouts()) {
            WorkoutResponseDTO workout = new WorkoutResponseDTO();
            workout.setId(inWorkout.getId());
            workout.setName(inWorkout.getD_workout_name());
            workout.setItems(new ArrayList<>());
            program.getWorkouts().add(workout);
            if (inWorkout.getInWarmupWorkoutItems() != null && !inWorkout.getInWarmupWorkoutItems().isEmpty()) {
                InWarmupWorkoutItem inWarmupWorkoutItem = inWorkout.getInWarmupWorkoutItems().get(0);
                WarmupWorkoutItemResponseDTO warmupWorkoutItem = new WarmupWorkoutItemResponseDTO();
                warmupWorkoutItem.setId(inWarmupWorkoutItem.getId());
                warmupWorkoutItem.setExercise_id(inWarmupWorkoutItem.getD_exercise_id() == null ? 0L
                        : Long.parseLong(inWarmupWorkoutItem.getD_exercise_id()));
                warmupWorkoutItem.setExercise_name(inWarmupWorkoutItem.getD_exercise_name());
                warmupWorkoutItem.setSpeed(inWarmupWorkoutItem.getSpeed());
                warmupWorkoutItem.setIncline(inWarmupWorkoutItem.getIncline());
                warmupWorkoutItem.setTime_in_min(inWarmupWorkoutItem.getTime_in_min());
                workout.setWarmup(warmupWorkoutItem);
            }
            for (InWorkoutItem inWorkoutItem : inWorkout.getInWorkoutItems()) {
                WorkoutItemResponseDTO workoutItem = new WorkoutItemResponseDTO();
                workoutItem.setId(inWorkoutItem.getId());
                workoutItem.setExercise_id(inWorkoutItem.getD_exercise_id() == null ? 0L
                        : Long.parseLong(inWorkoutItem.getD_exercise_id()));
                workoutItem.setExercise_name(inWorkoutItem.getD_exercise_name());
                workoutItem.setSets(inWorkoutItem.getSets());
                workoutItem.setRepetitions(inWorkoutItem.getRepetitions());
                workoutItem.setWeight(inWorkoutItem.getWeight());
                workoutItem.setBodyweight(BooleanUtils.isTrue(inWorkoutItem.getBodyweight()));                
                workout.getItems().add(workoutItem);
            }
        }
        return program;
    }
}
