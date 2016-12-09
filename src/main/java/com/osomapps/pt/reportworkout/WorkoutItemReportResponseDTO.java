package com.osomapps.pt.reportworkout;

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
class WorkoutItemReportResponseDTO {
    Long id;
    List<WorkoutItemSetReportResponseDTO> sets;
}
