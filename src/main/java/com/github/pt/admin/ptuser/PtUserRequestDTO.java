package com.github.pt.admin.ptuser;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
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
class PtUserRequestDTO {
    String login;
    String name;
    String password;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime activated;
    Boolean is_blocked;
    Boolean is_blocked_date_set;
    String blocked_comment;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime blocked_start;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime blocked_finish;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime deleted;
    String deleted_comment;
    Boolean is_deleted;
    Boolean is_default_password;
    String description;
    String phone;
    String phone2;
    List<PtRoleRequestDTO> roles;
}
