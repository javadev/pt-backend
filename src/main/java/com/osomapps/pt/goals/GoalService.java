package com.osomapps.pt.goals;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.user.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class GoalService {
    private final GoalRepository goalRepository;
    private final DictionaryService dictionaryService;
    private final UserService userService;

    GoalService(GoalRepository goalRepository,
            DictionaryService dictionaryService,
            UserService userService) {
        this.goalRepository = goalRepository;
        this.dictionaryService = dictionaryService;
        this.userService = userService;
    }

    List<GoalDTO> findAll(String token) {
        if (!token.isEmpty()) {
            userService.checkUserToken(token);
        }
        List<Goal> goals = goalRepository.findAll();
        return goals.stream().map(goal -> {
            GoalDTO goalDTO = new GoalDTO();
            goalDTO.setId(goal.getId());
            goalDTO.setTitle(dictionaryService.getEnValue(DictionaryName.goal_title,
                    goal.getDGoalTitle(), null));
            goalDTO.setTitle2(dictionaryService.getEnValue(DictionaryName.goal_title_2,
                    goal.getDGoalTitle2(), null));
            return goalDTO;
        }).sorted((e1, e2) -> Long.compare(e1.getId(), e2.getId()))
                .collect(Collectors.toList());
    }
}
