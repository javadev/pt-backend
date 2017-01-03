package com.osomapps.pt.admin.user;

import com.osomapps.pt.user.UserLevel;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
class AdminUserLevelService {

    List<UserLevelResponseDTO> findAll() {
        return Stream.of(UserLevel.values()).map(level ->
            new UserLevelResponseDTO().setId(level.getLevel()).setName(level.name()))
                .collect(Collectors.toList());
    }
}
