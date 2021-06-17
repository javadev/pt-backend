package com.osomapps.pt.reportworkout;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.programs.InProgramRepository;
import com.osomapps.pt.programs.InWorkout;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutItemReport;
import com.osomapps.pt.programs.InWorkoutItemSetReport;
import com.osomapps.pt.programs.InWorkoutRepository;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.user.UserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
class ReportWorkoutService {
    private final InProgramRepository inProgramRepository;
    private final InWorkoutRepository inWorkoutRepository;
    private final InWorkoutItemRepository inWorkoutItemRepository;
    private final InWorkoutItemReportRepository inWorkoutItemReportRepository;
    private final InWorkoutItemSetReportRepository inWorkoutItemSetReportRepository;
    private final UserService userService;

    @Autowired
    ReportWorkoutService(
            InProgramRepository inProgramRepository,
            InWorkoutRepository inWorkoutRepository,
            InWorkoutItemRepository inWorkoutItemRepository,
            InWorkoutItemReportRepository inWorkoutItemReportRepository,
            InWorkoutItemSetReportRepository inWorkoutItemSetReportRepository,
            UserService userService) {
        this.inProgramRepository = inProgramRepository;
        this.inWorkoutRepository = inWorkoutRepository;
        this.inWorkoutItemRepository = inWorkoutItemRepository;
        this.inWorkoutItemReportRepository = inWorkoutItemReportRepository;
        this.inWorkoutItemSetReportRepository = inWorkoutItemSetReportRepository;
        this.userService = userService;
    }

    List<WorkoutReportResponseDTO> findAll(String token) {
        if (!token.isEmpty()) {
            userService.checkUserToken(token);
        }
        return Collections.emptyList();
    }

    @Transactional
    public WorkoutReportResponseDTO create(
            String token, WorkoutReportRequestDTO workoutReportRequestDTO) {
        if (!token.isEmpty()) {
            final InUser inUser = userService.checkUserToken(token).getInUser();
            final InWorkout inWorkout =
                    inWorkoutRepository.findById(workoutReportRequestDTO.getId()).orElse(null);
            if (inWorkout == null) {
                throw new ResourceNotFoundException(
                        "Workout with id (" + workoutReportRequestDTO.getId() + ") not found");
            }
            final List<Long> inWorkoutItemIds =
                    inWorkout.getInWorkoutItems().stream()
                            .map(inWorkoutItem -> inWorkoutItem.getId())
                            .collect(Collectors.toList());
            final int workoutsSize = inWorkout.getInProgram().getInWorkouts().size();
            inWorkout
                    .getInProgram()
                    .setCurrent_workout_index(
                            (inWorkout.getInProgram().getCurrent_workout_index() + 1)
                                    % workoutsSize);
            inProgramRepository.save(inWorkout.getInProgram());
            final WorkoutReportResponseDTO workoutReportResponseDTO =
                    new WorkoutReportResponseDTO();
            workoutReportResponseDTO.setItems(new ArrayList<>());
            for (WorkoutItemReportRequestDTO workoutItemReportRequestDTO :
                    workoutReportRequestDTO.getItems()) {
                if (!inWorkoutItemIds.contains(workoutItemReportRequestDTO.getId())) {
                    log.warn(
                            "Id {} for inWorkoutItem not found",
                            workoutItemReportRequestDTO.getId());
                    continue;
                }
                final InWorkoutItem inWorkoutItem =
                        inWorkoutItemRepository
                                .findById(workoutItemReportRequestDTO.getId())
                                .orElse(null);
                if (inWorkoutItem == null) {
                    throw new ResourceNotFoundException(
                            "Workout item with id ("
                                    + workoutItemReportRequestDTO.getId()
                                    + ") not found");
                }
                if (!inWorkoutItem
                        .getInWorkout()
                        .getInProgram()
                        .getInUser()
                        .getId()
                        .equals(inUser.getId())) {
                    log.warn(
                            "User with id {} tries to update workout for user with id {}",
                            inUser.getId(),
                            inWorkoutItem.getInWorkout().getInProgram().getInUser().getId());
                    throw new UnauthorizedException(
                            "This workout item with id "
                                    + inWorkoutItem.getId()
                                    + " belong to other user");
                }
                final InWorkoutItemReport inWorkoutItemReport = new InWorkoutItemReport();
                inWorkoutItemReport.setInWorkoutItem(inWorkoutItem);
                inWorkoutItemReport.setInWorkoutItemSetReports(new ArrayList<>());
                InWorkoutItemReport savedInWorkoutItemReport =
                        inWorkoutItemReportRepository.save(inWorkoutItemReport);
                for (WorkoutItemSetReportRequestDTO workoutItemSetReportRequestDTO :
                        workoutItemReportRequestDTO.getSets()) {
                    final InWorkoutItemSetReport inWorkoutItemSetReport =
                            new InWorkoutItemSetReport();
                    inWorkoutItemSetReport.setInWorkoutItemReport(savedInWorkoutItemReport);
                    inWorkoutItemSetReport.setRepetitions(
                            workoutItemSetReportRequestDTO.getRepetitions());
                    inWorkoutItemSetReport.setWeight(
                            workoutItemSetReportRequestDTO.getWeight() == null
                                    ? null
                                    : workoutItemSetReportRequestDTO.getWeight().floatValue());
                    inWorkoutItemSetReport.setBodyweight(
                            BooleanUtils.isTrue(workoutItemSetReportRequestDTO.getBodyweight()));
                    inWorkoutItemSetReport.setTime_in_sec(
                            workoutItemSetReportRequestDTO.getTime_in_sec());
                    inWorkoutItemSetReport.setSpeed(workoutItemSetReportRequestDTO.getSpeed());
                    inWorkoutItemSetReport.setIncline(workoutItemSetReportRequestDTO.getIncline());
                    inWorkoutItemSetReport.setResistance(
                            workoutItemSetReportRequestDTO.getResistance());
                    inWorkoutItemSetReportRepository.save(inWorkoutItemSetReport);
                    inWorkoutItemReport.getInWorkoutItemSetReports().add(inWorkoutItemSetReport);
                }
                final WorkoutItemReportResponseDTO workoutItemReportResponseDTO =
                        new WorkoutItemReportResponseDTO();
                workoutItemReportResponseDTO.setId(savedInWorkoutItemReport.getId());
                workoutItemReportResponseDTO.setSets(
                        inWorkoutItemReport.getInWorkoutItemSetReports().stream()
                                .map(
                                        itemSetReport ->
                                                new WorkoutItemSetReportResponseDTO()
                                                        .setId(itemSetReport.getId())
                                                        .setRepetitions(
                                                                itemSetReport.getRepetitions())
                                                        .setWeight(
                                                                itemSetReport.getWeight() == null
                                                                        ? null
                                                                        : itemSetReport
                                                                                .getWeight()
                                                                                .intValue())
                                                        .setBodyweight(
                                                                itemSetReport.getBodyweight())
                                                        .setTime_in_sec(
                                                                itemSetReport.getTime_in_sec())
                                                        .setSpeed(itemSetReport.getSpeed())
                                                        .setIncline(itemSetReport.getIncline())
                                                        .setResistance(
                                                                itemSetReport.getResistance()))
                                .collect(Collectors.toList()));
                workoutReportResponseDTO.setId(inWorkoutItem.getInWorkout().getId());
                workoutReportResponseDTO.getItems().add(workoutItemReportResponseDTO);
            }
            return workoutReportResponseDTO;
        }
        return new WorkoutReportResponseDTO();
    }
}
