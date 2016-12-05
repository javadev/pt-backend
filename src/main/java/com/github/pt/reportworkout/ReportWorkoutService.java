package com.github.pt.reportworkout;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.UnauthorizedException;
import com.github.pt.programs.InWorkoutItem;
import com.github.pt.programs.InWorkoutItemReport;
import com.github.pt.programs.InWorkoutItemSetReport;
import com.github.pt.token.InUser;
import com.github.pt.user.UserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ReportWorkoutService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportWorkoutService.class);
    private final InWorkoutItemRepository inWorkoutItemRepository;
    private final InWorkoutItemReportRepository inWorkoutItemReportRepository;
    private final InWorkoutItemSetReportRepository inWorkoutItemSetReportRepository;
    private final UserService userService;

    @Autowired
    ReportWorkoutService(InWorkoutItemRepository inWorkoutItemRepository,
            InWorkoutItemReportRepository inWorkoutItemReportRepository,
            InWorkoutItemSetReportRepository inWorkoutItemSetReportRepository,
            UserService userService) {
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

    WorkoutReportResponseDTO create(String token, WorkoutReportRequestDTO workoutReportRequestDTO) {
        if (!token.isEmpty()) {
            final InUser inUser = userService.checkUserToken(token).getInUser();
            WorkoutReportResponseDTO workoutReportResponseDTO = new WorkoutReportResponseDTO();
            workoutReportResponseDTO.setItems(new ArrayList<>());
            for (WorkoutItemReportRequestDTO workoutItemReportRequestDTO : workoutReportRequestDTO.getItems()) {
                List<InWorkoutItem> inWorkoutItems = inWorkoutItemRepository.findById(workoutItemReportRequestDTO.getId());
                if (inWorkoutItems.isEmpty()) {
                    throw new ResourceNotFoundException("Workout item with id (" + workoutItemReportRequestDTO.getId()
                            + ") not found");
                }
                final InWorkoutItem inWorkoutItem = inWorkoutItems.get(inWorkoutItems.size() - 1);
                if (!inWorkoutItem.getInWorkout().getInProgram().getInUser().getId().equals(inUser.getId())) {
                    LOG.warn("User with id {} tries to update workout for user with id {}", inUser.getId(),
                        inWorkoutItem.getInWorkout().getInProgram().getInUser().getId());
                    throw new UnauthorizedException("This workout item belong to other user");
                }
                final InWorkoutItemReport inWorkoutItemReport = new InWorkoutItemReport();
                inWorkoutItemReport.setInWorkoutItem(inWorkoutItems.get(inWorkoutItems.size() - 1));
                inWorkoutItemReport.setInWorkoutItemSetReports(new ArrayList<>());
                for (WorkoutItemSetReportRequestDTO workoutItemSetReportRequestDTO : workoutItemReportRequestDTO.getSets()) {
                    final InWorkoutItemSetReport inWorkoutItemSetReport = new InWorkoutItemSetReport();
                    inWorkoutItemSetReport.setRepetitions(workoutItemSetReportRequestDTO.getRepetitions());
                    inWorkoutItemSetReport.setWeight(workoutItemSetReportRequestDTO.getWeight());
                    inWorkoutItemSetReport.setBodyweight(workoutItemSetReportRequestDTO.getBodyweight());
                    inWorkoutItemSetReport.setTime_in_min(workoutItemSetReportRequestDTO.getTime_in_min());
                    inWorkoutItemSetReport.setSpeed(workoutItemSetReportRequestDTO.getSpeed());
                    inWorkoutItemSetReport.setIncline(workoutItemSetReportRequestDTO.getIncline());
                    inWorkoutItemSetReport.setResistance(workoutItemSetReportRequestDTO.getResistance());
                    inWorkoutItemSetReportRepository.save(inWorkoutItemSetReport);
                    inWorkoutItemReport.getInWorkoutItemSetReports().add(inWorkoutItemSetReport);
                }
                InWorkoutItemReport savedInWorkoutItemReport = inWorkoutItemReportRepository.save(inWorkoutItemReport);
                WorkoutItemReportResponseDTO workoutItemReportResponseDTO = new WorkoutItemReportResponseDTO();
                workoutItemReportResponseDTO.setId(savedInWorkoutItemReport.getId());
                workoutItemReportResponseDTO.setSets(inWorkoutItemReport.getInWorkoutItemSetReports().stream()
                .map(itemSetReport -> new WorkoutItemSetReportResponseDTO()
                    .setId(itemSetReport.getId())
                    .setRepetitions(itemSetReport.getRepetitions())
                    .setWeight(itemSetReport.getWeight())
                    .setBodyweight(itemSetReport.getBodyweight())
                    .setTime_in_min(itemSetReport.getTime_in_min())
                    .setSpeed(itemSetReport.getSpeed())
                    .setIncline(itemSetReport.getIncline())
                    .setResistance(itemSetReport.getResistance())
                ).collect(Collectors.toList()));
                workoutReportResponseDTO.getItems().add(workoutItemReportResponseDTO);
            }
            return workoutReportResponseDTO;
        }
        return new WorkoutReportResponseDTO();
    }
}
