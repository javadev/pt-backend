package com.osomapps.pt.admin.program;

import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.programs.InProgram;
import com.osomapps.pt.programs.InProgramRepository;
import com.osomapps.pt.programs.InWarmupWorkoutItem;
import com.osomapps.pt.programs.InWarmupWorkoutItemRepository;
import com.osomapps.pt.programs.InWorkout;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutItemSet;
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
import com.osomapps.pt.programs.ParsePart;
import com.osomapps.pt.programs.ParseProgram;
import com.osomapps.pt.programs.ParseProgramRepository;
import com.osomapps.pt.programs.ParseRound;
import com.osomapps.pt.reportworkout.InWorkoutItemSetRepository;
import com.osomapps.pt.token.InUserGoal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

@Slf4j
@Service
public class AdminProgramAssignService {

    private final InUserRepository inUserRepository;
    private final InProgramRepository inProgramRepository;
    private final InWorkoutRepository inWorkoutRepository;
    private final InWorkoutItemRepository inWorkoutItemRepository;
    private final InWorkoutItemSetRepository inWorkoutItemSetRepository;
    private final ParseProgramRepository parseProgramRepository;
    private final InWarmupWorkoutItemRepository inWarmupWorkoutItemRepository;
    private final AdminProgramScanExerciseService adminProgramScanExerciseService;
    private final DictionaryService dictionaryService;

    AdminProgramAssignService(InUserRepository inUserRepository,
            InProgramRepository inProgramRepository,
            InWorkoutRepository inWorkoutRepository,
            InWorkoutItemRepository inWorkoutItemRepository,
            InWorkoutItemSetRepository inWorkoutItemSetRepository,
            ParseProgramRepository parseProgramRepository,
            InWarmupWorkoutItemRepository inWarmupWorkoutItemRepository,
            AdminProgramScanExerciseService adminProgramScanExerciseService,
            DictionaryService dictionaryService) {
        this.inUserRepository = inUserRepository;
        this.inProgramRepository = inProgramRepository;
        this.inWorkoutRepository = inWorkoutRepository;
        this.inWorkoutItemRepository = inWorkoutItemRepository;
        this.inWorkoutItemSetRepository = inWorkoutItemSetRepository;
        this.parseProgramRepository = parseProgramRepository;
        this.inWarmupWorkoutItemRepository = inWarmupWorkoutItemRepository;
        this.adminProgramScanExerciseService = adminProgramScanExerciseService;
        this.dictionaryService = dictionaryService;
    }

//    private static boolean isNotNullOrEmpty (String str) {
//        return str != null && !str.isEmpty();
//    }

    public InUser assign(InUser inUser) {
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
        List<ParseProgram> parsePrograms = parseProgramRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        if (parsePrograms.isEmpty()) {
            throw new UnauthorizedException("There are no programs found.");
        }
        Optional<Integer> userGroup = getUserGroup(inUser);
        if (!userGroup.isPresent()) {
            log.warn("User group not recognized, level - {}, gender - {}", inUser.getD_level(), inUser.getD_sex());
            return inUser;
        }
        if (inUser.getInUserGoals().isEmpty()) {
            log.warn("Cannot create program for user without goals.");
            return inUser;            
        }
        List<ParseRound> parseRounds = getRoundsGorGoalAndUserGroup(parsePrograms,
                inUser.getInUserGoals().get(0), userGroup.get());
        final InProgram inProgram = new InProgram()
                .setName("Test program for user with id " + inUser.getId())
                .setInWorkouts(Arrays.asList(new InWorkout()
                        .setD_workout_name("Test workout")
                        .setInWarmupWorkoutItems(Arrays.asList(
                                new InWarmupWorkoutItem()
                                        .setD_exercise_name("Test warmup workout item")))
                                        .setInWorkoutItems(Arrays.asList(
                                                new InWorkoutItem()
                                                        .setD_exercise_name("Test workout item")
                                                .setInWorkoutItemSets(Arrays.asList(
                                                        new InWorkoutItemSet()))
                                                .setInWorkoutItemReports(Collections.emptyList())
                                        ))));
        inProgram.setInUser(inUser);
        inProgram.getInWorkouts().forEach(inWorkout -> {
            inWorkout.setInProgram(inProgram);
            inWorkout.getInWarmupWorkoutItems().forEach(inWarmupWorkoutItem -> {
                inWarmupWorkoutItem.setInWorkout(inWorkout);
            });
            inWorkout.getInWorkoutItems().forEach(inWorkoutItem -> {
                inWorkoutItem.setInWorkout(inWorkout);
                inWorkoutItem.getInWorkoutItemSets().forEach(inWorkoutItemSet -> {
                    inWorkoutItemSet.setInWorkoutItem(inWorkoutItem);
                });
            });
        });
        inProgramRepository.save(inProgram);
        inWorkoutRepository.save(inProgram.getInWorkouts());
        inProgram.getInWorkouts().forEach(inWorkout -> {
            inWarmupWorkoutItemRepository.save(inWorkout.getInWarmupWorkoutItems());
            inWorkoutItemRepository.save(inWorkout.getInWorkoutItems());
            inWorkout.getInWorkoutItems().forEach(inWorkoutItem -> {
                inWorkoutItemSetRepository.save(inWorkoutItem.getInWorkoutItemSets());
            });
        });
        return inUser;
    }

    private List<ParseRound> getRoundsGorGoalAndUserGroup(List<ParseProgram> parsePrograms,
            InUserGoal inUserGoal, Integer userGroup) {
        final List<ParseRound> parseRounds = new ArrayList<>();
        parsePrograms.get(0).getParseGoals().forEach(parseGoal -> {
                boolean nameFound = getOnlySymbols(parseGoal.getName()).equalsIgnoreCase(
                        getOnlySymbols(getGoalName(inUserGoal)));
                if (nameFound) {
                     parseGoal.getParseUserGroups().forEach(parseUserGroup -> {
                        if (parseUserGroup.getName().equals("" + userGroup)) {
                            parseRounds.addAll(parseUserGroup.getParseRounds());
                        }
                     });
                }
        });
        return parseRounds;
    }
    
    private String getOnlySymbols(String value) {
        return value.replaceAll("[^\\D\\.]+", "");
    }

    private String getGoalName(InUserGoal inUserGoal) {
        return Arrays.asList(dictionaryService.getEnValue(DictionaryName.goal_title,
                    inUserGoal.getD_goal_title(), null),
                dictionaryService.getEnValue(DictionaryName.goal_title_2,
                    inUserGoal.getD_goal_title_2(), null)).stream().filter(Objects::nonNull).collect(Collectors.joining(", "));
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

    enum Gender {
        male, female, unknown
    }

    enum Level {
        unexperienced, experienced, unknown
    }

    private Optional<Integer> getUserGroup(InUser inUser) {
        final Gender gender;
        if ("male".equalsIgnoreCase(inUser.getD_sex())) {
            gender = Gender.male;
        } else if ("female".equalsIgnoreCase(inUser.getD_sex())) {
            gender = Gender.female;            
        } else {
            gender = Gender.unknown;
        }
        final Level level;
        if ("1".equalsIgnoreCase(inUser.getD_level())) {
            level = Level.unexperienced;
        } else if ("2".equalsIgnoreCase(inUser.getD_level())) {
            level = Level.experienced;
        } else {
            level = Level.unknown;
        }
        final Integer userGroup;
        if (gender == Gender.male && level == Level.experienced) {
            userGroup = 1;
        } else if (gender == Gender.male && level == Level.unexperienced) {
            userGroup = 2;
        } else if (gender == Gender.female && level == Level.experienced) {
            userGroup = 3;
        } else if (gender == Gender.female && level == Level.unexperienced) {
            userGroup = 4;
        } else {
            userGroup = null;
        }
        return Optional.ofNullable(userGroup);
    }
}
