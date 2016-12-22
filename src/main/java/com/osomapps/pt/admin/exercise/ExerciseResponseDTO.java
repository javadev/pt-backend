package com.osomapps.pt.admin.exercise;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Builder
class ExerciseResponseDTO {
    Long id;
    Long exerciseId;
    String nameEn;
    String nameNo;
    String descriptionEn;
    String descriptionNo;
    ExerciseBodypartResponseDTO bodypart;
    ExerciseEquipmentTypeResponseDTO equipmentType;
    List<ExerciseTypeResponseDTO> types;
    List<ExerciseInputResponseDTO> inputs;
    List<ExerciseOutputResponseDTO> outputs;
    List<ExerciseFileResponseDTO> files;
    Integer cardioPercent;
}
