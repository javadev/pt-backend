package com.osomapps.pt.admin.user;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Builder
class UserResponseDTO {
    Long id;
    String name;
    String email;
    UserTypeResponseDTO type;
    String gender;
    Integer level;
    Integer weight;
    List<UserGoalResponseDTO> goals;
    List<UserProgramResponseDTO> programs;
}
