package com.github.pt.programs;

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
public class ProgramResponseDTO {
    Long id;
    String name;
    String type;
    List<WorkoutResponseDTO> workouts;
}
