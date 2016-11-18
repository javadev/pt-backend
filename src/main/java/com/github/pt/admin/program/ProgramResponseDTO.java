package com.github.pt.admin.program;

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
}
