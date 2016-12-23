package com.osomapps.pt.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
class UserResponseDTO {
    Long id;
    String name;
    String gender;
    Long age;
    Long height;
    Long weight;
    String avatar;
    String avatar_dataurl;
    @JsonSerialize(using = UserLevelSerializer.class)
    UserLevel level;
    List<UserGoalResponseDTO> goals;
}
