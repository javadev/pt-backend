package com.osomapps.pt.admin.program;

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
class ParseWorkoutItemSetDTO {
    Integer repetitions;
    Boolean repetitions_to_failure;
    Float weight;
    Boolean bodyweight;
    Float time_in_min;
    Integer speed;
    Integer incline;
    Integer resistance;
}
