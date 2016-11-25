package com.github.pt.activecertificate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
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
class ActiveCertificateResponseDTO {
    Long id;
    String code;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime expiration_date;
}
