package com.osomapps.pt.exercises;

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
class ExerciseDTO {
    Long id;
    String name;
    Integer cardio_percent;
    List<String> types;
}
