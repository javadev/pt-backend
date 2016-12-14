package com.osomapps.pt.admin.goal;

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
class GoalRequestDTO {
    String titleEn;
    String titleNo;
    String title2En;
    String title2No;
    List<GoalParameterRequestDTO> parameters;
}
