package com.osomapps.pt.xlsx;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class WarmupWorkoutItem {
    String exercise;
    int exerciseId;
    Integer speed;
    Integer incline;
    Float timeInMin;
}
