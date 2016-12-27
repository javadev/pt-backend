package com.osomapps.pt.admin.program;

import com.osomapps.pt.programs.InProgram;
import com.osomapps.pt.programs.InProgramRepository;
import com.osomapps.pt.programs.InWarmupWorkoutItem;
import com.osomapps.pt.programs.InWarmupWorkoutItemRepository;
import com.osomapps.pt.programs.InWorkout;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutRepository;
import com.osomapps.pt.programs.ParseGoal;
import com.osomapps.pt.programs.ParseUserGroup;
import com.osomapps.pt.programs.ParseWarmupWorkoutItem;
import com.osomapps.pt.programs.ParseWorkout;
import com.osomapps.pt.programs.ParseWorkoutItem;
import com.osomapps.pt.reportworkout.InWorkoutItemRepository;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.osomapps.pt.programs.ParseGoalRepository;

@Service
class AdminProgramAssignService {

    private final InUserRepository inUserRepository;
    private final InProgramRepository inProgramRepository;
    private final InWorkoutRepository inWorkoutRepository;
    private final InWorkoutItemRepository inWorkoutItemRepository;
    private final ParseGoalRepository parseGoalRepository;
    private final InWarmupWorkoutItemRepository inWarmupWorkoutItemRepository;
    private final AdminProgramScanExerciseService adminProgramScanExerciseService;

    AdminProgramAssignService(InUserRepository inUserRepository,
            InProgramRepository inProgramRepository,
            InWorkoutRepository inWorkoutRepository,
            InWorkoutItemRepository inWorkoutItemRepository,
            ParseGoalRepository parseGoalRepository,
            InWarmupWorkoutItemRepository inWarmupWorkoutItemRepository,
            AdminProgramScanExerciseService adminProgramScanExerciseService) {
        this.inUserRepository = inUserRepository;
        this.inProgramRepository = inProgramRepository;
        this.inWorkoutRepository = inWorkoutRepository;
        this.inWorkoutItemRepository = inWorkoutItemRepository;
        this.parseGoalRepository = parseGoalRepository;
        this.inWarmupWorkoutItemRepository = inWarmupWorkoutItemRepository;
        this.adminProgramScanExerciseService = adminProgramScanExerciseService;
    }

    private static boolean isNotNullOrEmpty (String str) {
        return str != null && !str.isEmpty();
    }

    List<ParseGoal> assign(List<ParseGoal> parseGoals) {
//        parseGoals.stream().forEachOrdered(parseUser -> {
//            final List<InUser> inUsers = inUserRepository.findAll();
//            final List<InUser> inUsersWithName = inUsers.stream().filter(inUser -> 
//                    getUserName(inUser).orElse("").equals(parseUser.getName())).collect(Collectors.toList());
//            if (inUsersWithName.isEmpty()) {
//                parseUser.setErrors(Arrays.asList(parseUser.getErrors(),
//                    "Cannot assign user " + parseUser.getName() + ". User not found.")
//                        .stream().filter(AdminProgramAssignService::isNotNullOrEmpty).collect(Collectors.joining(", ")));
//            } else if (inUsersWithName.size() > 1) {
//                parseUser.setErrors(Arrays.asList(parseUser.getErrors(),
//                    "Cannot assign user " + parseUser.getName() + ". More than one user found.")
//                        .stream().filter(AdminProgramAssignService::isNotNullOrEmpty).collect(Collectors.joining(", ")));
//            } else {
//                parseUser.setIn_user_id(inUsersWithName.get(0).getId());
//                final InProgram inProgram = new InProgram();
//                inProgram.setInUser(inUsersWithName.get(0));
//                inProgram.setName(parseUser.getProgram().getName());
//                inProgram.setD_program_type("personal");
//                final InProgram savedInProgram = inProgramRepository.save(inProgram);
//                for (ParseWorkout parseWorkout : parseUser.getParseWorkouts()) {
//                    final InWorkout inWorkout = new InWorkout();
//                    inWorkout.setInProgram(savedInProgram);
//                    inWorkout.setD_workout_name(parseWorkout.getName());
//                    final InWorkout savedInWorkout = inWorkoutRepository.save(inWorkout);
//                    if (!parseWorkout.getParseWarmupWorkoutItems().isEmpty()) {
//                        final ParseWarmupWorkoutItem parseWarmupWorkoutItem = parseWorkout.getParseWarmupWorkoutItems().get(0);
//                        final InWarmupWorkoutItem inWarmupWorkoutItem = new InWarmupWorkoutItem();
//                        inWarmupWorkoutItem.setInWorkout(savedInWorkout);
//                        inWarmupWorkoutItem.setD_exercise_name(parseWarmupWorkoutItem.getName());
//                        final Optional<Long> exerciseId = adminProgramScanExerciseService.getExerciseIdByName(
//                                parseWarmupWorkoutItem.getName());
//                        inWarmupWorkoutItem.setD_exercise_id("" + exerciseId.orElse(0L));
//                        inWarmupWorkoutItem.setSpeed(parseWarmupWorkoutItem.getSpeed());
//                        inWarmupWorkoutItem.setIncline(parseWarmupWorkoutItem.getIncline());
//                        inWarmupWorkoutItem.setTime_in_min(parseWarmupWorkoutItem.getTime_in_min());
//                        inWarmupWorkoutItemRepository.save(inWarmupWorkoutItem);
//                    }
//                    for (ParseWorkoutItem parseWorkoutItem : parseWorkout.getParseWorkoutItems()) {
//                        final InWorkoutItem inWorkoutItem = new InWorkoutItem();
//                        inWorkoutItem.setInWorkout(savedInWorkout);
//                        inWorkoutItem.setD_exercise_name(parseWorkoutItem.getName());
//                        final Optional<Long> exerciseId = adminProgramScanExerciseService.getExerciseIdByName(
//                                parseWorkoutItem.getName());
//                        inWorkoutItem.setD_exercise_id("" + exerciseId.orElse(0L));
//                        inWorkoutItem.setSets(parseWorkoutItem.getSets());
//                        inWorkoutItem.setRepetitions(parseWorkoutItem.getRepetitions());
//                        inWorkoutItem.setWeight(parseWorkoutItem.getWeight() == null ? null : parseWorkoutItem.getWeight().floatValue());
//                        inWorkoutItem.setTime_in_min(parseWorkoutItem.getTime_in_min());
//                        inWorkoutItem.setSpeed(parseWorkoutItem.getSpeed());
//                        inWorkoutItem.setResistance(parseWorkoutItem.getResistance());
//                        final InWorkoutItem savedInWorkoutItem = inWorkoutItemRepository.save(inWorkoutItem);
//                        parseWorkoutItem.setIn_workout_item_id(savedInWorkoutItem.getId());
//                    }
//                }
//            }
//        });
        return parseGoalRepository.save(parseGoals);
    }

//    private Optional<String> getUserName(InUser inUser) {
//        final Optional<String> userName;
//        if (inUser.getInUserFacebooks() == null || inUser.getInUserFacebooks().isEmpty()) {
//            if (inUser.getInUserEmails().isEmpty()) {
//                userName = Optional.empty();
//            } else {
//                userName = Optional.of(inUser.getInUserEmails().get(inUser.getInUserEmails().size() - 1).getUser_name());
//            }
//        } else {
//            userName = Optional.of(inUser.getInUserFacebooks().get(inUser.getInUserFacebooks().size() - 1).getUser_name());
//        }
//        return userName;
//    }
}
