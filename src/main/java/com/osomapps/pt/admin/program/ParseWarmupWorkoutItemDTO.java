package com.osomapps.pt.admin.program;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Builder
class ParseWarmupWorkoutItemDTO {
    Long id;
    String name;
    Integer speed;
    Integer incline;
    Float time_in_min;
}
