package com.osomapps.pt.reportworkout;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/report-workout")
class ReportWorkoutResource {

    private final ReportWorkoutService reportWorkoutService;

    @Autowired
    ReportWorkoutResource(ReportWorkoutService reportWorkoutService) {
        this.reportWorkoutService = reportWorkoutService;
    }

    @GetMapping
    List<WorkoutReportResponseDTO> findAll(@RequestHeader(value = "X-Token") String token) {
        return reportWorkoutService.findAll(token);
    }

    @PostMapping
    WorkoutReportResponseDTO create(
            @RequestHeader(value = "X-Token") String token,
            @RequestBody WorkoutReportRequestDTO workoutReportRequestDTO) {
        return reportWorkoutService.create(token, workoutReportRequestDTO);
    }
}
