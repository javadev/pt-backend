package com.github.pt.programs;

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
class WorkoutItemResponseDTO {
    Long id;
    Long exercise_id;
    String exercise_name;
    String exercise_type;
    Integer sets;
    Integer repetitions;
    Integer weight;
    Boolean bodyweight;
    Integer time_in_min;
    Integer speed;
    Integer incline;
    Integer resistance;
}
