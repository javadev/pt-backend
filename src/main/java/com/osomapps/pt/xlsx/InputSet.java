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
public class InputSet {
    Integer repetitions;
    Boolean repetitionsToFailure;
    Float weight;
    Boolean bodyweight;
    Float timeInMin;
    Integer speed;
    Integer incline;
    Integer resistance;
}
