package com.github.pt.admin.user;

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
class UserWorkoutItemSetReportResponseDTO {
    Long id;
    Integer repetitions;
    Integer weight;
    Boolean bodyweight;
    Integer timeInMin;
    Integer speed;
    Integer incline;
    Integer resistance;
}
