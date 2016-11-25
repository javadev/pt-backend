package com.github.pt.admin.ptuser;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
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
class PtUserResponseDTO {
    Long id;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime created;
    String login;
    String name;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime activated;
    Boolean is_blocked;
    Boolean is_blocked_date_set;
    String blocked_comment;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime blocked_start;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime blocked_finish;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime deleted;
    String deleted_comment;
    Boolean is_deleted;
    Boolean is_default_password;
    String description;
    String phone;
    String phone2;    
}
