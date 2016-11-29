package com.github.pt.admin.exercise;

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
class ExerciseRequestDTO {
    Long exerciseId;
    String nameEn;
    String nameNo;
    String descriptionEn;
    String descriptionNo;
    ExerciseBodypartRequestDTO bodypart;
}
