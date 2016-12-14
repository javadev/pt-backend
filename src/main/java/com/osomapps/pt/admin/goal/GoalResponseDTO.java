package com.osomapps.pt.admin.goal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
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
    String titleEn2;
    String titleNo2;
    List<GoalParameterResponseDTO> parameters;
}
