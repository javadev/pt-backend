package com.osomapps.pt.admin.user;

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
class UserWorkoutItemRequestDTO {
    Long exercise_id;
    String exercise_name;
    Integer sets;
    Integer repetitions;
    Integer weight;
    Boolean bodyweight;    
}
