package com.osomapps.pt.programs;

import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.user.UserService;
import java.util.ArrayList;
import java.util.Arrays;
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

    List<ProgramResponseDTO> getPredefinedPrograms(String token) {
        if (token.isEmpty()) {
            return Collections.emptyList();
        }
        userService.checkUserToken(token);
        InProgram inProgram1 = new InProgram();
        inProgram1.setId(1L);
        inProgram1.setName("Test program 1");
        inProgram1.setD_program_type("test");
        InWorkout inWorkout11 = new InWorkout();
        inWorkout11.setId(1L);
        inWorkout11.setD_workout_name("Test workout 1");
        InWorkout inWorkout12 = new InWorkout();
        inWorkout12.setId(2L);
        inWorkout12.setD_workout_name("Test workout 2");
        inProgram1.setInWorkouts(Arrays.asList(inWorkout11, inWorkout12));
        InWarmupWorkoutItem inWarmupWorkoutItem111 = new InWarmupWorkoutItem();
        inWarmupWorkoutItem111.setId(0L);
        inWarmupWorkoutItem111.setExercise_id(800);
        inWarmupWorkoutItem111.setD_exercise_name("Treadmill");
        inWarmupWorkoutItem111.setSpeed(10);
        inWarmupWorkoutItem111.setIncline(2);
        inWarmupWorkoutItem111.setTime_in_sec(600);
        inWorkout11.setInWarmupWorkoutItems(Arrays.asList(inWarmupWorkoutItem111));
        InWorkoutItem inWorkoutItem111 = new InWorkoutItem();
        inWorkoutItem111.setId(1L);
        inWorkoutItem111.setExercise_id(210);
        inWorkoutItem111.setD_exercise_name("Deadlift");
        InWorkoutItemSet inWorkoutItemSet111 = new InWorkoutItemSet();
        inWorkoutItemSet111.setRepetitions(10);
        inWorkoutItemSet111.setWeight(75F);
        inWorkoutItem111.setInWorkoutItemSets(Arrays.asList(inWorkoutItemSet111));
        InWorkoutItem inWorkoutItem112 = new InWorkoutItem()
            .setId(2L)
            .setExercise_id(260)
            .setD_exercise_name("Bench Press")
            .setInWorkoutItemSets(Arrays.asList(new InWorkoutItemSet()
                    .setRepetitions(10)
                    .setWeight(65F),
                    new InWorkoutItemSet()
                    .setRepetitions(10)
                    .setWeight(65F),
                    new InWorkoutItemSet()
                    .setRepetitions(10)
                    .setWeight(65F)));

        InWorkoutItem inWorkoutItem113 = new InWorkoutItem()
            .setId(3L)
            .setExercise_id(150)
            .setD_exercise_name("Pull Up")
            .setInWorkoutItemSets(Arrays.asList(new InWorkoutItemSet()
                    .setRepetitions(10)
                    .setBodyweight(true)
                    .setWeight(65F),
                    new InWorkoutItemSet()
                    .setRepetitions(10)
                    .setBodyweight(true)
                    .setWeight(65F),
                    new InWorkoutItemSet()
                    .setRepetitions(10)
                    .setBodyweight(true)
                    .setWeight(65F),
                    new InWorkoutItemSet()
                    .setRepetitions(10)
                    .setBodyweight(true)
                    .setWeight(65F)));

        InWorkoutItem inWorkoutItem114 = new InWorkoutItem()
            .setId(4L)
            .setExercise_id(530)
            .setD_exercise_name("Dips")
           .setInWorkoutItemSets(Arrays.asList(new InWorkoutItemSet()
                    .setRepetitions(10)
                    .setBodyweight(true)
                    .setWeight(65F),
                    new InWorkoutItemSet()
                    .setRepetitions(10)
                    .setBodyweight(true)
                    .setWeight(65F),
                    new InWorkoutItemSet()
                    .setRepetitions(10)
                    .setBodyweight(true)
                    .setWeight(65F)));

        InWorkoutItem inWorkoutItem115 = new InWorkoutItem()
            .setId(5L)
            .setExercise_id(440)
            .setD_exercise_name("Plank")
            .setD_exercise_type("OnTime")
            .setInWorkoutItemSets(Arrays.asList(new InWorkoutItemSet()
                .setTime_in_sec(120)
                .setBodyweight(true)
                .setWeight(65F)));

        InWorkoutItemSet inWorkoutItemSet1 = new InWorkoutItemSet()
                .setIncline(2)
                .setSpeed(8)
                .setTime_in_sec(4);
        InWorkoutItemSet inWorkoutItemSet2 = new InWorkoutItemSet()
                .setIncline(3)
                .setSpeed(9)
                .setTime_in_sec(4);
        InWorkoutItemSet inWorkoutItemSet3 = new InWorkoutItemSet()
                .setIncline(4)
                .setSpeed(10)
                .setTime_in_sec(4);
        InWorkoutItemSet inWorkoutItemSet4 = new InWorkoutItemSet()
                .setIncline(3)
                .setSpeed(9)
                .setTime_in_sec(4);
        InWorkoutItem inWorkoutItem116 = new InWorkoutItem()
            .setId(6L)
            .setExercise_id(820)
            .setD_exercise_name("Treadmill 4x4")
            .setD_exercise_type("OnIntervals")
            .setInWorkoutItemSets(Arrays.asList(inWorkoutItemSet1, inWorkoutItemSet2, inWorkoutItemSet3, inWorkoutItemSet4));

        inWorkout11.setInWorkoutItems(Arrays.asList(inWorkoutItem111,
                inWorkoutItem112, inWorkoutItem113, inWorkoutItem114, inWorkoutItem115,
                inWorkoutItem116));
        inProgram1.setInWorkouts(Arrays.asList(inWorkout11));

        ProgramResponseDTO program1 = createProgramResponseDTO(inProgram1);
        return Arrays.asList(program1);
    }

    List<ProgramResponseDTO> getExamples(String token) {
        if (token.isEmpty()) {
            return Collections.emptyList();
        }
        InUserLogin inUserLogin = userService.checkUserToken(token);
        List<InProgram> inPrograms = inUserLogin.getInUser().getInPrograms();
        if (inPrograms.isEmpty()) {
            return Collections.emptyList();
        }
        class InProgramWrapper {
            private final String name;
            private final InProgram inProgram;
            InProgramWrapper(String name, InProgram inProgram) {
                this.name = name;
                this.inProgram = inProgram;
            }
            @Override
            public boolean equals(Object object) {
                return object instanceof InProgramWrapper && name.equals(((InProgramWrapper) object).name);
            }
            @Override
            public int hashCode() {
                return name.hashCode();
            }
        }
        return inPrograms.stream()
                .sorted((p1, p2) -> Long.compare(p2.getId(), p1.getId()))
                .map(inProgram -> new InProgramWrapper(inProgram.getName(), inProgram))
                .distinct()
                .map(inProgramWrapper -> createProgramResponseDTO(inProgramWrapper.inProgram))
                .collect(Collectors.toList());
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
                warmupWorkoutItem.setExercise_id(inWarmupWorkoutItem.getExercise_id());
                warmupWorkoutItem.setExercise_name(inWarmupWorkoutItem.getD_exercise_name());
                warmupWorkoutItem.setExercise_type("OnTime");
                warmupWorkoutItem.setSets(Arrays.asList(new WarmupWorkoutItemSetResponseDTO()
                    .setSpeed(inWarmupWorkoutItem.getSpeed())
                    .setIncline(inWarmupWorkoutItem.getIncline())
                    .setTime_in_sec(inWarmupWorkoutItem.getTime_in_sec())
                ));
                workout.setWarmup(warmupWorkoutItem);
            }
            for (InWorkoutItem inWorkoutItem : inWorkout.getInWorkoutItems()) {
                WorkoutItemResponseDTO workoutItem = new WorkoutItemResponseDTO();
                workoutItem.setId(inWorkoutItem.getId());
                workoutItem.setExercise_id(inWorkoutItem.getExercise_id());
                workoutItem.setExercise_name(inWorkoutItem.getD_exercise_name());
                workoutItem.setExercise_type(inWorkoutItem.getD_exercise_type() == null ? "OnRepetitions"
                        : inWorkoutItem.getD_exercise_type());
                workoutItem.setSets(inWorkoutItem.getInWorkoutItemSets().stream().map(set ->
                        new WorkoutItemSetResponseDTO()
                        .setRepetitions(set.getRepetitions())
                        .setWeight(set.getWeight())
                        .setBodyweight(BooleanUtils.isTrue(set.getBodyweight()))
                        .setTime_in_sec(set.getTime_in_sec())
                        .setSpeed(set.getSpeed())
                        .setIncline(set.getIncline())
                        .setResistance(set.getResistance())
                ).collect(Collectors.toList()));
                workout.getItems().add(workoutItem);
            }
        }
        return program;
    }
}
