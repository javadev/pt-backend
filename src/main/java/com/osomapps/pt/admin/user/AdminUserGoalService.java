package com.osomapps.pt.admin.user;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.goals.GoalRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminUserGoalService {
    private final GoalRepository goalRepository;
    private final DictionaryService dictionaryService;
    
    AdminUserGoalService(GoalRepository goalRepository,
            DictionaryService dictionaryService) {
        this.goalRepository = goalRepository;
        this.dictionaryService = dictionaryService;
    }

    List<UserGoalResponseDTO> findAll() {
        return goalRepository.findAll().stream().map(goal ->
            new UserGoalResponseDTO()
                    .setId(goal.getId())
                    .setTitle(dictionaryService.getEnValue(
                            DictionaryName.goal_title, goal.getDGoalTitle(), null))
                    .setTitle2(dictionaryService.getEnValue(
                            DictionaryName.goal_title_2, goal.getDGoalTitle2(), null))
        ).collect(Collectors.toList());
    }

}
