package com.osomapps.pt.reportphoto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
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
class PhotoResponseDTO {
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime date;

    String photo;
}
