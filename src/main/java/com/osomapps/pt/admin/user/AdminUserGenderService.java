package com.osomapps.pt.admin.user;

import com.osomapps.pt.user.UserGender;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
class AdminUserGenderService {

    List<UserGenderResponseDTO> findAll() {
        return Stream.of(UserGender.values())
                .map(
                        gender ->
                                new UserGenderResponseDTO()
                                        .setId(gender.getGender())
                                        .setName(gender.name()))
                .collect(Collectors.toList());
    }
}
