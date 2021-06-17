package com.osomapps.pt.admin.goal;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.goals.Goal;
import com.osomapps.pt.goals.GoalParameterRepository;
import com.osomapps.pt.goals.GoalRepository;
import com.osomapps.pt.goals.GoalType;
import com.osomapps.pt.goals.GoalTypeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
class AdminGoalService {
    private final GoalRepository goalRepository;
    private final GoalParameterRepository goalParameterRepository;
    private final DictionaryService dictionaryService;
    private final GoalTypeRepository goalTypeRepository;

    AdminGoalService(
            GoalRepository goalRepository,
            GoalParameterRepository goalParameterRepository,
            DictionaryService dictionaryService,
            GoalTypeRepository goalTypeRepository) {
        this.goalRepository = goalRepository;
        this.goalParameterRepository = goalParameterRepository;
        this.dictionaryService = dictionaryService;
        this.goalTypeRepository = goalTypeRepository;
    }

    List<GoalResponseDTO> findAll() {
        return goalRepository.findAll(sortByIdAsc()).stream()
                .map(goal -> goalToDto(goal))
                .collect(Collectors.toList());
    }

    private Sort sortByIdAsc() {
        return Sort.by(Sort.Direction.ASC, "id");
    }

    private GoalResponseDTO goalToDto(Goal goal) {
        return GoalResponseDTO.builder()
                .id(goal.getId())
                .titleEn(
                        dictionaryService.getEnValue(
                                DictionaryName.goal_title, goal.getDGoalTitle(), ""))
                .titleNo(
                        dictionaryService.getNoValue(
                                DictionaryName.goal_title, goal.getDGoalTitle(), ""))
                .title2En(
                        dictionaryService.getEnValue(
                                DictionaryName.goal_title_2, goal.getDGoalTitle2(), null))
                .title2No(
                        dictionaryService.getNoValue(
                                DictionaryName.goal_title_2, goal.getDGoalTitle2(), null))
                .parameters(
                        goal.getGoalParameters().stream()
                                .map(
                                        parameter ->
                                                GoalParameterResponseDTO.builder()
                                                        .id(parameter.getId())
                                                        .name(parameter.getName())
                                                        .build())
                                .collect(Collectors.toList()))
                .type(
                        goal.getGoalType() == null
                                ? null
                                : GoalTypeResponseDTO.builder()
                                        .id(goal.getGoalType().getId())
                                        .name(goal.getGoalType().getName())
                                        .build())
                .build();
    }

    GoalResponseDTO findOne(Long id) {
        final Goal goal = goalRepository.findById(id).orElse(null);
        if (goal == null) {
            throw new ResourceNotFoundException("Goal with id " + id + " not found.");
        }
        return goalToDto(goal);
    }

    GoalResponseDTO create(GoalRequestDTO goalRequestDTO) {
        final GoalType goalTypeDb =
                goalRequestDTO.getType().getId() == null
                        ? null
                        : goalTypeRepository
                                .findById(goalRequestDTO.getType().getId())
                                .orElse(null);
        final String dataKey = dictionaryService.getNewDictionaryDataKey(DictionaryName.goal_title);
        dictionaryService.createDictionaryDataKey(
                DictionaryName.goal_title,
                dataKey,
                goalRequestDTO.getTitleEn(),
                goalRequestDTO.getTitleNo());
        final String data2Key =
                dictionaryService.getNewDictionaryDataKey(DictionaryName.goal_title_2);
        dictionaryService.createDictionaryDataKey(
                DictionaryName.goal_title_2,
                dataKey,
                goalRequestDTO.getTitle2En(),
                goalRequestDTO.getTitle2No());
        final Goal goal = new Goal();
        goal.setDGoalTitle(dataKey);
        goal.setDGoalTitle2(data2Key);
        goal.setGoalParameters(
                goalParameterRepository.findAllById(
                        goalRequestDTO.getParameters().stream()
                                .map(type -> type.getId())
                                .collect(Collectors.toList())));
        goal.setGoalType(goalTypeDb);
        return goalToDto(goalRepository.save(goal));
    }

    GoalResponseDTO update(Long id, GoalRequestDTO goalRequestDTO) {
        final Goal existedGoal = goalRepository.findById(id).orElse(null);
        if (existedGoal == null) {
            throw new ResourceNotFoundException("Goal with id not found: " + id);
        }
        final GoalType goalTypeDb =
                goalRequestDTO.getType().getId() == null
                        ? null
                        : goalTypeRepository
                                .findById(goalRequestDTO.getType().getId())
                                .orElse(null);
        final String dataKey = existedGoal.getDGoalTitle();
        dictionaryService.createDictionaryDataKey(
                DictionaryName.goal_title,
                dataKey,
                goalRequestDTO.getTitleEn(),
                goalRequestDTO.getTitleNo());
        final String data2Key =
                dictionaryService.createDictionaryDataKey(
                        DictionaryName.goal_title_2,
                        existedGoal.getDGoalTitle2(),
                        goalRequestDTO.getTitle2En(),
                        goalRequestDTO.getTitle2No());
        existedGoal.setDGoalTitle(dataKey);
        existedGoal.setDGoalTitle2(data2Key);
        existedGoal.setGoalParameters(
                goalParameterRepository.findAllById(
                        goalRequestDTO.getParameters().stream()
                                .map(type -> type.getId())
                                .collect(Collectors.toList())));
        existedGoal.setGoalType(goalTypeDb);
        final Goal savedGoal = goalRepository.save(existedGoal);
        return goalToDto(savedGoal);
    }

    GoalResponseDTO delete(Long id) {
        final Goal goal = goalRepository.findById(id).orElse(null);
        if (goal == null) {
            throw new ResourceNotFoundException("Goal with id " + id + " not found.");
        }
        dictionaryService.deleteDatas(DictionaryName.goal_title, goal.getDGoalTitle());
        dictionaryService.deleteDatas(DictionaryName.goal_title_2, goal.getDGoalTitle2());
        final GoalResponseDTO goalResponseDTO = goalToDto(goal);
        goalRepository.deleteById(id);
        return goalResponseDTO;
    }
}
