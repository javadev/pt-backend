package com.osomapps.pt.admin.goal;

import com.osomapps.pt.goals.GoalType;
import com.osomapps.pt.goals.GoalTypeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminGoalTypeService {

    private final GoalTypeRepository goalTypeRepository;

    AdminGoalTypeService(GoalTypeRepository goalTypeRepository) {
        this.goalTypeRepository = goalTypeRepository;
    }

    List<GoalTypeResponseDTO> findAll() {
        return goalTypeRepository.findAll().stream()
                .map(AdminGoalTypeService::goalTypeToDto)
                .collect(Collectors.toList());
    }

    private static GoalTypeResponseDTO goalTypeToDto(GoalType type) {
        return GoalTypeResponseDTO.builder().id(type.getId()).name(type.getName()).build();
    }
}
