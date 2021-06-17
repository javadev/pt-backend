package com.osomapps.pt.admin.exercise;

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
class ExerciseResponseDTO {
    Long id;
    Integer exerciseId;
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
