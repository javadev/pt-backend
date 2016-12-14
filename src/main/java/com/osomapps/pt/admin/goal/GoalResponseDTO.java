package com.osomapps.pt.admin.goal;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Builder
class GoalResponseDTO {
    Long id;
    String titleEn;
    String titleNo;
    String title2En;
    String title2No;
    List<GoalParameterResponseDTO> parameters;
}
