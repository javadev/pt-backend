package com.osomapps.pt.goals;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class GoalService {
    private final GoalRepository goalRepository;
    private final DictionaryService dictionaryService;

    GoalService(GoalRepository goalRepository,
            DictionaryService dictionaryService) {
        this.goalRepository = goalRepository;
        this.dictionaryService = dictionaryService;
    }

    List<GoalDTO> findAll() {
        List<Goal> goals = goalRepository.findAll();
        return goals.stream().map(goal ->
            new GoalDTO()
                .setId(goal.getId())
                .setTitle(dictionaryService.getEnValue(DictionaryName.goal_title,
                    goal.getDGoalTitle(), null))
                .setTitle2(dictionaryService.getEnValue(DictionaryName.goal_title_2,
                    goal.getDGoalTitle2(), null))
                .setType(goal.getGoalType() == null ? null : goal.getGoalType().getName())
                .setParameters(goal.getGoalParameters()
                    .stream().map(parameter -> parameter.getName()).collect(Collectors.toList()))
        ).sorted((e1, e2) -> Long.compare(e1.getId(), e2.getId()))
                .collect(Collectors.toList());
    }
}
