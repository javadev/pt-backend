package com.osomapps.pt.xlsx;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class Workout {
    String name;
    int columnIndex;
    int rowIndex;
    WarmupWorkoutItem warmup;
    List<WorkoutItem> workoutItems = new ArrayList<>();
}
