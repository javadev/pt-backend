package com.osomapps.pt.tokenemail;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
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
class UserSignupResponseDTO {
    Long id;
    String name;
    String email;
    String avatar_dataurl;
    String gender;
    Integer age;
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate birthday;
}
