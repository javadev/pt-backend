package com.osomapps.pt.admin.user;

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
    List<Integer> repetitions;
    List<Boolean> repetitionsToFailure;
    List<Float> weight;
    List<Boolean> bodyweight;
    List<Integer> timeInSec;
    List<Integer> speed;
    List<Integer> incline;
    List<Integer> resistance;
    Integer reportSets;
    List<Integer> reportRepetitions;
    List<Integer> reportWeight;
    List<Integer> reportTimeInMin;
    List<Integer> reportSpeed;
    List<Integer> reportIncline;
    List<Integer> reportResistance;
}
