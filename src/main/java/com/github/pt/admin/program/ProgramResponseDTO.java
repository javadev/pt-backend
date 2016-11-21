package com.github.pt.admin.program;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
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
class ProgramResponseDTO {
    Long id;
    String name;
    String fileName;
    Long fileSize;
    String fileType;
    String dataUrl;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime updated;
    List<ParseUserDTO> parseUsers;
}
