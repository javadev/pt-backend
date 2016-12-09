package com.osomapps.pt.token;

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
class FacebookResponse {
    String id;
    String name;
    String gender;
    LocalDate birthday;
    Long age;
}
