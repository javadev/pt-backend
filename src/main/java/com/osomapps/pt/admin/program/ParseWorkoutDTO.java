package com.osomapps.pt.admin.program;

import java.util.List;
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
class ParseWorkoutDTO {
    Long id;
    String name;
    ParseWarmupWorkoutItemDTO warmupWorkoutItem;
    List<ParseWorkoutItemDTO> workoutItems;
}
