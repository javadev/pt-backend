package com.github.pt.admin.certificate;

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
class CertificateResponseDTO {
    Long id;
    String code;
    Integer amount_of_days;
    Boolean activated;
}
