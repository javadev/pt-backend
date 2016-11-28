package com.github.pt.admin.user;

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
class UserWarmupWorkoutItemRequestDTO {
    Long exercise_id;
    String exercise_name;
    Integer speed;
    Integer incline;
    Integer time_in_min;    
}
