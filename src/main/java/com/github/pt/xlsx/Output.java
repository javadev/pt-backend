package com.github.pt.xlsx;

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
public class Output {
    Integer sets;
    List<Integer> repetitions;
    List<Integer> weights;
    List<Integer> timeInMins;
    List<Integer> speeds;
    List<Integer> inclines;
    List<Integer> resistances;
}
