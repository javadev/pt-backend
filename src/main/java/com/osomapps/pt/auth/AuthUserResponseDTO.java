package com.osomapps.pt.auth;

import java.util.List;
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
class AuthUserResponseDTO {

    Long id;
    String name;
    String login;
    List<String> permissions;
}
