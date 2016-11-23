package com.github.pt.admin.certificate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.Builder;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Builder
class CertificateRequestDTO {
    @NonNull
    String code;
    @NonNull
    Integer amountOfDays;
}
