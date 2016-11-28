package com.github.pt.admin.user;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.programs.InProgram;
import com.github.pt.programs.InProgramRepository;
import com.github.pt.programs.InWarmupWorkoutItem;
import com.github.pt.programs.InWorkout;
import com.github.pt.programs.InWorkoutItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

@Service
class AdminUserProgramService {
    
    private final InProgramRepository inProgramRepository;
    
    AdminUserProgramService(InProgramRepository inProgramRepository) {
        this.inProgramRepository = inProgramRepository;
    }

    static UserProgramResponseDTO inProgramToDto(InProgram inProgram) {
        UserProgramResponseDTO program = new UserProgramResponseDTO();
        program.setId(inProgram.getId());
        program.setName(inProgram.getName());
        program.setType(inProgram.getD_program_type());
        program.setWorkouts(new ArrayList<>());
        program.setCreated(inProgram.getCreated());
        for (InWorkout inWorkout : inProgram.getInWorkouts()) {
            UserWorkoutResponseDTO workout = new UserWorkoutResponseDTO();
            workout.setId(inWorkout.getId());
            workout.setName(inWorkout.getD_workout_name());
            workout.setItems(new ArrayList<>());
            program.getWorkouts().add(workout);
            if (inWorkout.getInWarmupWorkoutItems() != null && !inWorkout.getInWarmupWorkoutItems().isEmpty()) {
                InWarmupWorkoutItem inWarmupWorkoutItem = inWorkout.getInWarmupWorkoutItems().get(0);
                UserWarmupWorkoutItemResponseDTO warmupWorkoutItem = new UserWarmupWorkoutItemResponseDTO();
                warmupWorkoutItem.setId(inWarmupWorkoutItem.getId());
                warmupWorkoutItem.setExercise_id(Long.parseLong(inWarmupWorkoutItem.getD_exercise_id()));
                warmupWorkoutItem.setExercise_name(inWarmupWorkoutItem.getD_exercise_name());
                warmupWorkoutItem.setSpeed(inWarmupWorkoutItem.getSpeed());
                warmupWorkoutItem.setIncline(inWarmupWorkoutItem.getIncline());
                warmupWorkoutItem.setTime_in_min(inWarmupWorkoutItem.getTime_in_min());
                workout.setWarmup(warmupWorkoutItem);
            }
            for (InWorkoutItem inWorkoutItem : inWorkout.getInWorkoutItems()) {
                UserWorkoutItemResponseDTO workoutItem = new UserWorkoutItemResponseDTO();
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

    List<UserProgramResponseDTO> findAll() {
        return inProgramRepository.findAll().stream()
            .map(AdminUserProgramService::inProgramToDto).collect(Collectors.toList());
    }

    UserProgramResponseDTO findOne(Long id) {
        final InProgram inProgram = inProgramRepository.findOne(id);
        if (inProgram == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found.");
        }
        return inProgramToDto(inProgram);
    }
    
    UserProgramResponseDTO create(UserProgramRequestDTO userProgramRequestDTO) {
        final InProgram inProgram = new InProgram();
        final InProgram savedInProgram = inProgramRepository.save(inProgram);
        return new UserProgramResponseDTO();
    }

    UserProgramResponseDTO update(Long id, UserProgramRequestDTO userProgramRequestDTO) {
        final InProgram inProgram = inProgramRepository.findOne(id);
        if (inProgram == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found.");
        }
        return inProgramToDto(inProgram);
    }

    UserProgramResponseDTO delete(Long id) {
        final InProgram inProgram = inProgramRepository.findOne(id);
        if (inProgram == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found.");
        }
        UserProgramResponseDTO userProgramResponseDTO = inProgramToDto(inProgram);
        inProgramRepository.delete(id);
        return userProgramResponseDTO;
    }
}
