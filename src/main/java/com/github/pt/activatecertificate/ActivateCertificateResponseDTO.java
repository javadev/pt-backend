package com.github.pt.activatecertificate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
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
class ActivateCertificateResponseDTO {
    Long id;
    String code;
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate expiration_date;
}