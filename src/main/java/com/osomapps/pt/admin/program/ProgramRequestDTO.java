package com.osomapps.pt.admin.program;

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
class ProgramRequestDTO {
    String name;
    String fileName;
    Long fileSize;
    String fileType;
    String dataUrl;
}
