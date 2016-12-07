package com.github.pt.admin.program;

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
class ParseWorkoutItemDTO {
    Long id;
    String name;
    Integer sets;
    Integer repetitions;
    Integer weight;
    Boolean bodyweight;
    Integer time_in_min;
    Integer speed;
    Integer resistance;
}
