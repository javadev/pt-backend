package com.github.pt.reportworkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("api/v1/report-workout")
class ReportWorkoutResource {

    private final ReportWorkoutService reportWorkoutService;
    
    @Autowired
    ReportWorkoutResource(ReportWorkoutService reportWorkoutService) {
        this.reportWorkoutService = reportWorkoutService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<WorkoutReportResponseDTO> findAll(@RequestHeader(value = "X-Token") String token) {
        return reportWorkoutService.findAll(token);
    }

    @RequestMapping(method = RequestMethod.POST)
    WorkoutReportResponseDTO create(@RequestHeader(value = "X-Token") String token,
            @RequestBody WorkoutReportRequestDTO workoutReportRequestDTO) {
        return reportWorkoutService.create(token, workoutReportRequestDTO);
    }
}
