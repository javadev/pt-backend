package com.github.pt.programs;

import com.github.pt.user.UserService;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProgramService {
    private final InProgramRepository inProgramRepository;
    private final UserService userService;

    @Autowired
    ProgramService(InProgramRepository inProgramRepository,
            UserService userService) {
        this.inProgramRepository = inProgramRepository;
        this.userService = userService;
    }

    List<ProgramResponseDTO> getExamples(String token) {
        if (!token.isEmpty()) {
            userService.checkUserToken(token);
        }
        InProgram inProgram = new InProgram();
        inProgram.setId(1L);
        inProgram.setName("Test program");
        inProgram.setD_program_type("test");
        InWorkout inWorkout1 = new InWorkout();
        inWorkout1.setId(1L);
        inWorkout1.setD_workout_name("Test workout 1");
        InWorkout inWorkout2 = new InWorkout();
        inWorkout2.setId(2L);
        inWorkout2.setD_workout_name("Test workout 2");
        inProgram.setInWorkouts(Arrays.asList(inWorkout1, inWorkout2));
        InWorkoutItem inWorkoutItem11 = new InWorkoutItem();
        inWorkoutItem11.setId(1L);
        inWorkoutItem11.setD_exercise_id("Exercise id 1 1");
        inWorkoutItem11.setD_exercise_name("Exercise name 1 1");
        inWorkoutItem11.setSets(3);
        inWorkoutItem11.setRepetitions(4);
        inWorkoutItem11.setWeight(10);
        InWorkoutItem inWorkoutItem12 = new InWorkoutItem();
        inWorkoutItem12.setId(2L);
        inWorkoutItem12.setD_exercise_id("Exercise id 1 2");
        inWorkoutItem12.setD_exercise_name("Exercise name 1 2");
        inWorkoutItem12.setSets(5);
        inWorkoutItem12.setRepetitions(6);
        inWorkoutItem12.setWeight(11);
        inWorkout1.setInWorkoutItems(Arrays.asList(inWorkoutItem11, inWorkoutItem12));
        InWorkoutItem inWorkoutItem21 = new InWorkoutItem();
        inWorkoutItem21.setId(3L);
        inWorkoutItem21.setD_exercise_id("Exercise id 2 1");
        inWorkoutItem21.setD_exercise_name("Exercise name 2 1");
        inWorkoutItem21.setSets(6);
        inWorkoutItem21.setRepetitions(7);
        inWorkoutItem21.setWeight(12);
        InWorkoutItem inWorkoutItem22 = new InWorkoutItem();
        inWorkoutItem22.setId(4L);
        inWorkoutItem22.setD_exercise_id("Exercise id 2 2");
        inWorkoutItem22.setD_exercise_name("Exercise name 2 2");
        inWorkoutItem22.setSets(7);
        inWorkoutItem22.setRepetitions(8);
        inWorkoutItem22.setWeight(13);
        inWorkout2.setInWorkoutItems(Arrays.asList(inWorkoutItem21, inWorkoutItem22));
        ProgramResponseDTO program = new ProgramResponseDTO();
        program.setId(inProgram.getId());
        program.setName(inProgram.getName());
        program.setType(inProgram.getD_program_type());
        WorkoutResponseDTO workout1 = new WorkoutResponseDTO();
        workout1.setId(inWorkout1.getId());
        workout1.setName(inWorkout1.getD_workout_name());
        WorkoutResponseDTO workout2 = new WorkoutResponseDTO();
        workout2.setId(inWorkout2.getId());
        workout2.setName(inWorkout2.getD_workout_name());
        program.setWorkouts(Arrays.asList(workout1, workout2));
        WorkoutItemResponseDTO workoutItem11 = new WorkoutItemResponseDTO();
        workoutItem11.setId(inWorkoutItem11.getId());
        workoutItem11.setExercise_id(inWorkoutItem11.getD_exercise_id());
        workoutItem11.setExercise_name(inWorkoutItem11.getD_exercise_name());
        workoutItem11.setSets(inWorkoutItem11.getSets());
        workoutItem11.setRepetitions(inWorkoutItem11.getRepetitions());
        workoutItem11.setWeight(inWorkoutItem11.getWeight());
        workoutItem11.setBodyweight(BooleanUtils.isTrue(inWorkoutItem11.getBodyweight()));
        WorkoutItemResponseDTO workoutItem12 = new WorkoutItemResponseDTO();
        workoutItem12.setId(inWorkoutItem12.getId());
        workoutItem12.setExercise_id(inWorkoutItem12.getD_exercise_id());
        workoutItem12.setExercise_name(inWorkoutItem12.getD_exercise_name());
        workoutItem12.setSets(inWorkoutItem12.getSets());
        workoutItem12.setRepetitions(inWorkoutItem12.getRepetitions());
        workoutItem12.setWeight(inWorkoutItem12.getWeight());
        workoutItem12.setBodyweight(BooleanUtils.isTrue(inWorkoutItem12.getBodyweight()));
        workout1.setItems(Arrays.asList(workoutItem11, workoutItem12));
        WorkoutItemResponseDTO workoutItem21 = new WorkoutItemResponseDTO();
        workoutItem21.setId(inWorkoutItem21.getId());
        workoutItem21.setExercise_id(inWorkoutItem21.getD_exercise_id());
        workoutItem21.setExercise_name(inWorkoutItem21.getD_exercise_name());
        workoutItem21.setSets(inWorkoutItem21.getSets());
        workoutItem21.setRepetitions(inWorkoutItem21.getRepetitions());
        workoutItem21.setWeight(inWorkoutItem21.getWeight());
        workoutItem21.setBodyweight(BooleanUtils.isTrue(inWorkoutItem21.getBodyweight()));
        WorkoutItemResponseDTO workoutItem22 = new WorkoutItemResponseDTO();
        workoutItem22.setId(inWorkoutItem22.getId());
        workoutItem22.setExercise_id(inWorkoutItem22.getD_exercise_id());
        workoutItem22.setExercise_name(inWorkoutItem22.getD_exercise_name());
        workoutItem22.setSets(inWorkoutItem22.getSets());
        workoutItem22.setRepetitions(inWorkoutItem22.getRepetitions());
        workoutItem22.setWeight(inWorkoutItem22.getWeight());
        workoutItem22.setBodyweight(BooleanUtils.isTrue(inWorkoutItem22.getBodyweight()));
        workout2.setItems(Arrays.asList(workoutItem21, workoutItem22));
        return Arrays.asList(program);
    }
}