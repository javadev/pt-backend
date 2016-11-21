package com.github.pt.admin.program;

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
class ParseUserDTO {
    Long id;
    String name;
    List<ParseWorkoutDTO> workouts;
    String errors;
}
