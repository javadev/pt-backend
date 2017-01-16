package com.osomapps.pt.reportphoto;

import com.osomapps.pt.exercises.*;
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
class ReportPhotoFileDTO {
    Long id;
    String fileName;
    String fileType;
}
