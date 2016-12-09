package com.osomapps.pt.token;

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
class UserResponseDTO {
    Long id;
    String name;
    String avatar;    
    String gender;
    Long age;
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate birthday;
}
