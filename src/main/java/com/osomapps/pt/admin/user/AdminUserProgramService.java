package com.osomapps.pt.admin.user;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.programs.InProgram;
import com.osomapps.pt.programs.InProgramRepository;
import com.osomapps.pt.programs.InWarmupWorkoutItem;
import com.osomapps.pt.programs.InWorkout;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutItemReport;
import java.util.ArrayList;
import java.util.Collections;
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
                warmupWorkoutItem.setExercise_id(inWarmupWorkoutItem.getExercise_id());
                warmupWorkoutItem.setExercise_name(inWarmupWorkoutItem.getD_exercise_name());
                warmupWorkoutItem.setSpeed(inWarmupWorkoutItem.getSpeed());
                warmupWorkoutItem.setIncline(inWarmupWorkoutItem.getIncline());
                warmupWorkoutItem.setTime_in_sec(inWarmupWorkoutItem.getTime_in_sec());
                workout.setWarmup(warmupWorkoutItem);
            }
            for (InWorkoutItem inWorkoutItem : inWorkout.getInWorkoutItems()) {
                UserWorkoutItemResponseDTO userWorkoutItemResponseDTO = new UserWorkoutItemResponseDTO()
                        .setId(inWorkoutItem.getId())
                        .setExercise_id(inWorkoutItem.getExercise_id())
                        .setExercise_name(inWorkoutItem.getD_exercise_name())
                        .setSets(inWorkoutItem.getInWorkoutItemSets().size())
                        .setRepetitions(inWorkoutItem.getInWorkoutItemSets().stream().map(set -> set.getRepetitions()).collect(Collectors.toList()))
                        .setRepetitionsToFailure(inWorkoutItem.getInWorkoutItemSets().stream().map(set -> BooleanUtils.isTrue(set.getRepetitions_to_failure())).collect(Collectors.toList()))
                        .setWeight(inWorkoutItem.getInWorkoutItemSets().stream().map(set -> set.getWeight()).collect(Collectors.toList()))
                        .setBodyweight(inWorkoutItem.getInWorkoutItemSets().stream().map(set -> BooleanUtils.isTrue(set.getBodyweight())).collect(Collectors.toList()))
                        .setTimeInSec(inWorkoutItem.getInWorkoutItemSets().stream().map(set -> set.getTime_in_sec()).collect(Collectors.toList()))
                        .setSpeed(inWorkoutItem.getInWorkoutItemSets().stream().map(set -> set.getSpeed()).collect(Collectors.toList()))
                        .setIncline(inWorkoutItem.getInWorkoutItemSets().stream().map(set -> set.getIncline()).collect(Collectors.toList()))
                        .setResistance(inWorkoutItem.getInWorkoutItemSets().stream().map(set -> set.getResistance()).collect(Collectors.toList()));
                InWorkoutItemReport inWorkoutItemReport = inWorkoutItem.getInWorkoutItemReports().isEmpty()
                        ? null : inWorkoutItem.getInWorkoutItemReports().get(
                                inWorkoutItem.getInWorkoutItemReports().size() - 1);
                if (inWorkoutItemReport != null) {
                    userWorkoutItemResponseDTO
                            .setReportSets(inWorkoutItemReport.getInWorkoutItemSetReports().size())
                            .setReportRepetitions(inWorkoutItemReport.getInWorkoutItemSetReports().stream()
                                    .map(set -> set.getRepetitions()).collect(Collectors.toList()))
                            .setReportWeight(inWorkoutItemReport.getInWorkoutItemSetReports().stream()
                                    .map(set -> set.getWeight() == null ? null : set.getWeight().intValue()).collect(Collectors.toList()))
                            .setReportTimeInMin(inWorkoutItemReport.getInWorkoutItemSetReports().stream()
                                    .map(set -> set.getTime_in_sec()).collect(Collectors.toList()))
                            .setReportSpeed(inWorkoutItemReport.getInWorkoutItemSetReports().stream()
                                    .map(set -> set.getSpeed()).collect(Collectors.toList()))
                            .setReportIncline(inWorkoutItemReport.getInWorkoutItemSetReports().stream()
                                    .map(set -> set.getIncline()).collect(Collectors.toList()))
                            .setReportResistance(inWorkoutItemReport.getInWorkoutItemSetReports().stream()
                                    .map(set -> set.getResistance()).collect(Collectors.toList()));
                }
                workout.getItems().add(userWorkoutItemResponseDTO);
            }
        }
        return program;
    }

    List<UserProgramResponseDTO> findAll() {
        return inProgramRepository.findAll().stream()
            .map(AdminUserProgramService::inProgramToDto).collect(Collectors.toList());
    }

    UserProgramResponseDTO findOne(Long id) {
        final InProgram inProgram = inProgramRepository.findById(id).orElse(null);
        if (inProgram == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found.");
        }
        return inProgramToDto(inProgram);
    }
    
    UserProgramResponseDTO create(UserProgramRequestDTO userProgramRequestDTO) {
        final InProgram inProgram = new InProgram()
                .setName(userProgramRequestDTO.getName())
                .setInWorkouts(userProgramRequestDTO.getWorkouts().stream()
                        .map(workout -> new InWorkout()
                                .setD_workout_name(workout.getName())
                                .setInWorkoutItems(workout.getItems().stream()
                                        .map(item -> new InWorkoutItem()
                                                .setInWorkoutItemSets(Collections.emptyList())
                                                .setInWorkoutItemReports(Collections.emptyList()))
                                        .collect(Collectors.toList()))
                        )
                        .collect(Collectors.toList()));
        final InProgram savedInProgram = inProgramRepository.save(inProgram);
        return inProgramToDto(savedInProgram);
    }

    UserProgramResponseDTO update(Long id, UserProgramRequestDTO userProgramRequestDTO) {
        final InProgram inProgram = inProgramRepository.findById(id).orElse(null);
        if (inProgram == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found.");
        }
        return inProgramToDto(inProgram);
    }

    UserProgramResponseDTO delete(Long id) {
        final InProgram inProgram = inProgramRepository.findById(id).orElse(null);
        if (inProgram == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found.");
        }
        UserProgramResponseDTO userProgramResponseDTO = inProgramToDto(inProgram);
        inProgramRepository.deleteById(id);
        return userProgramResponseDTO;
    }
}
