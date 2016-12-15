package com.osomapps.pt.admin.email;

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
class EmailMessageTemplateResponseDTO {
    Long id;
    String emailSubjectEn;
    String emailSubjectNo;
    String emailTextEn;
    String emailTextNo;
    EmailMessageTypeResponseDTO type;
}
