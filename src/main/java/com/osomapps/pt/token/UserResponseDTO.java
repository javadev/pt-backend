package com.osomapps.pt.token;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.osomapps.pt.user.UserGoalResponseDTO;
import com.osomapps.pt.user.UserLevel;
import com.osomapps.pt.user.UserLevelDeserializer;
import java.time.LocalDate;
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
    String email;
    String avatar;    
    String avatar_dataurl;
    String gender;
    Long age;
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate birthday;
    @JsonDeserialize(using = UserLevelDeserializer.class)
    UserLevel level;
    List<UserGoalResponseDTO> goals;
    Long height;
    Long weight;
}
