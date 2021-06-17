package com.osomapps.pt.admin.program;

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
class ProgramResponseDTO {
    Long id;
    String name;
    String fileName;
    Long fileSize;
    String fileType;
    String dataUrl;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime updated;

    List<ParseExerciseDTO> parseExercises;
    List<ParseGoalDTO> parseGoals;
}
