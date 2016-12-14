package com.osomapps.pt.goals;

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
class GoalDTO {
    Long id;
    String title;
    String title2;
    List<String> parameters;
}
