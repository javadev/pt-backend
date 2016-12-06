package com.github.pt.admin.user;

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
class UserWorkoutItemResponseDTO {
    Long id;
    Long exercise_id;
    String exercise_name;
    Integer sets;
    Integer repetitions;
    Boolean repetitionsToFailure;
    Integer weight;
    Boolean bodyweight;
    Integer timeInMin;
    Integer speed;
    Integer incline;
    Integer resistance;
    Integer reportSets;
    List<Integer> reportRepetitions;
    List<Integer> reportWeight;
    List<Integer> reportTimeInMin;
    List<Integer> reportSpeed;
    List<Integer> reportIncline;
    List<Integer> reportResistance;
}
