package com.osomapps.pt.admin.exercise;

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
class ExerciseEquipmentTypeResponseDTO {
    Long id;
    String nameEn;
    String nameNo;
}
