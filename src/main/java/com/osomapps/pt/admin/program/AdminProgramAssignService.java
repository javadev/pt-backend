package com.osomapps.pt.admin.program;

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
import com.osomapps.pt.programs.ParseWorkout;
import com.osomapps.pt.reportworkout.InWorkoutItemRepository;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.osomapps.pt.programs.ParseProgram;
import com.osomapps.pt.programs.ParseProgramRepository;
import com.osomapps.pt.programs.ParseRound;
import com.osomapps.pt.reportworkout.InWorkoutItemSetRepository;
import com.osomapps.pt.token.InUserGoal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
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

    public InUser assign(InUser inUser) {

        List<ParseProgram> parsePrograms = parseProgramRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        if (parsePrograms.isEmpty()) {
            log.warn("There are no programs found.");
            return inUser;
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
        final List<ParseWorkout> parseWorkouts;
        final List<String> goalNames = new ArrayList<>();
        if (!parseRounds.isEmpty()) {
            goalNames.add(parseRounds.get(0).getParseUserGroup().getParseGoal().getName());
        }
        if (inUser.getInUserGoals().size() > 1) {
            List<ParseRound> parseRounds2 = getRoundsGorGoalAndUserGroup(parsePrograms,
                    inUser.getInUserGoals().get(1), userGroup.get());
            parseWorkouts = mergeLists(getParseWorkouts(parseRounds), getParseWorkouts(parseRounds2));
            goalNames.add(parseRounds2.get(0).getParseUserGroup().getParseGoal().getName());
        } else {
            parseWorkouts = getParseWorkouts(parseRounds);
        }
        final AtomicInteger index = new AtomicInteger(-1);
        final InProgram inProgram = new InProgram()
                .setName("Test program for user " + inUser.getId())
                .setInWorkouts(parseWorkouts.stream().map(parseWorkout
                        -> new InWorkout()
                        .setD_workout_name(parseWorkout.getName())
                        .setWorkout_index(index.incrementAndGet())
                        .setPart_name(parseWorkout.getParsePart().getName())
                        .setGoal_index(goalNames.indexOf(parseWorkout.getParsePart().getParseRound().getParseUserGroup().getParseGoal().getName()))
                        .setInWarmupWorkoutItems(parseWorkout.getParseWarmupWorkoutItems().stream().map(parseWarmupWorkoutItem
                                -> new InWarmupWorkoutItem()
                                .setD_exercise_name(parseWarmupWorkoutItem.getName())
                                .setExercise_id(parseWarmupWorkoutItem.getExercise_id())
                                .setTime_in_sec(minToSec(parseWarmupWorkoutItem.getTime_in_min()))
                                .setSpeed(parseWarmupWorkoutItem.getSpeed())
                                .setIncline(parseWarmupWorkoutItem.getIncline())
                        ).collect(Collectors.toList()))
                        .setInWorkoutItems(parseWorkout.getParseWorkoutItems().stream().map(parseWorkoutItem
                                -> new InWorkoutItem()
                                .setD_exercise_name(parseWorkoutItem.getName())
                                .setExercise_id(parseWorkoutItem.getExercise_id())
                                .setInWorkoutItemSets(parseWorkoutItem.getParseWorkoutItemSets().stream().map(parseWorkoutItemSet
                                        -> new InWorkoutItemSet()
                                        .setRepetitions(parseWorkoutItemSet.getRepetitions())
                                        .setRepetitions_to_failure(parseWorkoutItemSet.getRepetitions_to_failure())
                                        .setWeight(parseWorkoutItemSet.getWeight())
                                        .setBodyweight(parseWorkoutItemSet.getBodyweight())
                                        .setTime_in_sec(minToSec(parseWorkoutItemSet.getTime_in_min()))
                                        .setSpeed(parseWorkoutItemSet.getSpeed())
                                        .setIncline(parseWorkoutItemSet.getIncline())
                                        .setResistance(parseWorkoutItemSet.getResistance())
                                ).collect(Collectors.toList()))
                                .setInWorkoutItemReports(Collections.emptyList())
                        ).collect(Collectors.toList()))).collect(Collectors.toList()));
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

    private Integer minToSec(Float value) {
        return value == null ? null : Float.valueOf(value * 60).intValue();
    }

    private List<ParseRound> getRoundsGorGoalAndUserGroup(List<ParseProgram> parsePrograms,
            InUserGoal inUserGoal, Integer userGroup) {
        final List<ParseRound> parseRounds = new ArrayList<>();
        parsePrograms.get(0).getParseGoals().forEach(parseGoal -> {
            boolean nameFound = getOnlySymbols(parseGoal.getName()).equalsIgnoreCase(
                    getOnlySymbols(getGoalName(inUserGoal)));
            if (nameFound) {
                final List<ParseRound> localParseRounds = new ArrayList<>();
                parseGoal.getParseUserGroups().forEach(parseUserGroup -> {
                    if (parseUserGroup.getName().equals("" + userGroup)) {
                        localParseRounds.addAll(parseUserGroup.getParseRounds());
                    }
                });
                parseRounds.addAll(multiplyLists(localParseRounds, parseGoal.getLoops()));
            }
        });
        return parseRounds;
    }

    List<ParseRound> multiplyLists(List<ParseRound> parseRounds, Integer loops) {
        if (loops == null) {
            return parseRounds;
        }
        final List<ParseRound> result = new ArrayList<>(parseRounds.size() * loops);
        parseRounds.forEach(parseRound -> {
            for (int index = 0; index < loops; index += 1) {
                result.add(parseRound);
            }
        });
        return result;
    }

    private String getOnlySymbols(String value) {
        return value.replace("on a specific distance", "").replaceAll("[\\s\\.\\,]+", "");
    }

    private String getGoalName(InUserGoal inUserGoal) {
        return Arrays.asList(dictionaryService.getEnValue(DictionaryName.goal_title,
                inUserGoal.getD_goal_title(), null),
                dictionaryService.getEnValue(DictionaryName.goal_title_2,
                        inUserGoal.getD_goal_title_2(), null)).stream().filter(Objects::nonNull).collect(Collectors.joining(", "));
    }

    private List<ParseWorkout> getParseWorkouts(List<ParseRound> parseRounds) {
        final List<ParseWorkout> parseWorkouts = new ArrayList<>();
        parseRounds.forEach(parseRound -> {
            parseRound.getParseParts().forEach(parsePart -> {
                parsePart.getParseWorkouts().forEach(parseWorkout -> {
                    parseWorkouts.add(parseWorkout);
                });
            });
        });
        return parseWorkouts;
    }

    List<ParseWorkout> mergeLists(List<ParseWorkout> parseWorkouts1, List<ParseWorkout> parseWorkouts2) {
        final List<ParseWorkout> parseWorkouts = new ArrayList<>();
        for (int index = 0; index < Math.max(parseWorkouts1.size(), parseWorkouts2.size()); index += 1) {
            if (!parseWorkouts1.isEmpty()) {
                parseWorkouts.add(parseWorkouts1.get(index % parseWorkouts1.size()));
            }
            if (!parseWorkouts2.isEmpty()) {
                parseWorkouts.add(parseWorkouts2.get(index % parseWorkouts2.size()));
            }
        }
        return parseWorkouts;
    }

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
