package com.osomapps.pt.admin.certificate;

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
class CertificateResponseDTO {
    Long id;
    String code;
    Integer amountOfDays;
    Boolean activated;
}
