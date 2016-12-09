package com.osomapps.pt.admin.user;

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
class UserWorkoutResponseDTO {
    Long id;
    String name;
    UserWarmupWorkoutItemResponseDTO warmup;
    List<UserWorkoutItemResponseDTO> items;
}
