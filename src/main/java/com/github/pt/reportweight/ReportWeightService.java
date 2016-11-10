package com.github.pt.reportweight;

import com.github.pt.token.InUserLogin;
import com.github.pt.user.UserService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ReportWeightService {
    private final InUserWeightRepository inUserWeightRepository;
    private final UserService userService;

    @Autowired
    ReportWeightService(InUserWeightRepository inUserWeightRepository,
            UserService userService) {
        this.inUserWeightRepository = inUserWeightRepository;
        this.userService = userService;
    }

    List<WeightResponseDTO> findAll(String token) {
        if (!token.isEmpty()) {
            final InUserLogin inUserLogin = userService.checkUserToken(token);
            return inUserLogin.getInUser().getInUserWeights().stream().map(inUserWeight ->
                new WeightResponseDTO(inUserWeight.getId(), inUserWeight.getWeight().longValue())
            ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    WeightResponseDTO create(String token, WeightRequestDTO weightRequestDTO) {
        if (!token.isEmpty()) {
            final InUserLogin inUserLogin = userService.checkUserToken(token);
            InUserWeight inUserWeight = new InUserWeight();
            inUserWeight.setInUser(inUserLogin.getInUser());
            inUserWeight.setWeight(weightRequestDTO.getWeight().floatValue());
            InUserWeight InUserWeightSaved = inUserWeightRepository.save(inUserWeight);
            return new WeightResponseDTO(InUserWeightSaved.getId(), InUserWeightSaved.getWeight().longValue());
        }
        return new WeightResponseDTO();
    }
}
