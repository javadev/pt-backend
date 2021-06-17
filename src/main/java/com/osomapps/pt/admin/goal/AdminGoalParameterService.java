package com.osomapps.pt.admin.goal;

import com.osomapps.pt.goals.GoalParameter;
import com.osomapps.pt.goals.GoalParameterRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminGoalParameterService {

    private final GoalParameterRepository goalParameterRepository;

    AdminGoalParameterService(GoalParameterRepository goalParameterRepository) {
        this.goalParameterRepository = goalParameterRepository;
    }

    List<GoalParameterResponseDTO> findAll() {
        return goalParameterRepository.findAll().stream()
                .map(AdminGoalParameterService::goalParameterToDto)
                .collect(Collectors.toList());
    }

    private static GoalParameterResponseDTO goalParameterToDto(GoalParameter parameter) {
        return GoalParameterResponseDTO.builder()
                .id(parameter.getId())
                .name(parameter.getName())
                .build();
    }
}
