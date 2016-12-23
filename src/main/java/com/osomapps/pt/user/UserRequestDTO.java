package com.osomapps.pt.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
class UserRequestDTO {
    String name;
    String gender;
    Long age;
    Long height;
    Long weight;
    String avatar_dataurl;
    @JsonDeserialize(using = UserLevelDeserializer.class)
    UserLevel level;
    List<UserGoalRequestDTO> goals;
}
