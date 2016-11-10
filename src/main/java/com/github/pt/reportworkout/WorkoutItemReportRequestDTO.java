package com.github.pt.reportworkout;

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
class WorkoutItemReportRequestDTO {
    Long id;
    Integer sets;
    Integer repetitions;
    Integer weight;
    Boolean bodyweight;
}
