package com.github.pt.reportworkout;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.programs.InWorkoutItem;
import com.github.pt.programs.InWorkoutItemReport;
import com.github.pt.user.UserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ReportWorkoutService {
    private final InWorkoutItemRepository inWorkoutItemRepository;
    private final InWorkoutItemReportRepository inWorkoutItemReportRepository;
    private final UserService userService;

    @Autowired
    ReportWorkoutService(InWorkoutItemRepository inWorkoutItemRepository,
            InWorkoutItemReportRepository inWorkoutItemReportRepository,
            UserService userService) {
        this.inWorkoutItemRepository = inWorkoutItemRepository;
        this.inWorkoutItemReportRepository = inWorkoutItemReportRepository;
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
            userService.checkUserToken(token);
            WorkoutReportResponseDTO workoutReportResponseDTO = new WorkoutReportResponseDTO();
            workoutReportResponseDTO.setItems(new ArrayList<>());
            for (WorkoutItemReportRequestDTO workoutItemReportRequestDTO : workoutReportRequestDTO.getItems()) {
                List<InWorkoutItem> inWorkoutItems = inWorkoutItemRepository.findById(workoutItemReportRequestDTO.getId());
                if (inWorkoutItems.isEmpty()) {
                    throw new ResourceNotFoundException("Workout item with id (" + workoutItemReportRequestDTO.getId()
                            + ") not found");
                }
                final InWorkoutItemReport inWorkoutItemReport = new InWorkoutItemReport();
                inWorkoutItemReport.setInWorkoutItem(inWorkoutItems.get(inWorkoutItems.size() - 1));
                inWorkoutItemReport.setSets(workoutItemReportRequestDTO.getSets());
                inWorkoutItemReport.setRepetitions(workoutItemReportRequestDTO.getRepetitions());
                inWorkoutItemReport.setWeight(workoutItemReportRequestDTO.getWeight());
                inWorkoutItemReport.setBodyweight(workoutItemReportRequestDTO.getBodyweight());
                InWorkoutItemReport savedInWorkoutItemReport = inWorkoutItemReportRepository.save(inWorkoutItemReport);
                WorkoutItemReportResponseDTO workoutItemReportResponseDTO = new WorkoutItemReportResponseDTO();
                workoutItemReportResponseDTO.setId(savedInWorkoutItemReport.getId());
                workoutItemReportResponseDTO.setSets(savedInWorkoutItemReport.getSets());
                workoutItemReportResponseDTO.setRepetitions(savedInWorkoutItemReport.getRepetitions());
                workoutItemReportResponseDTO.setWeight(savedInWorkoutItemReport.getWeight());
                workoutItemReportResponseDTO.setBodyweight(savedInWorkoutItemReport.getBodyweight());
                workoutReportResponseDTO.getItems().add(workoutItemReportResponseDTO);
            }
            return workoutReportResponseDTO;
        }
        return new WorkoutReportResponseDTO();
    }
}
