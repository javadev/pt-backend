package com.osomapps.pt.admin.program;

import java.util.List;
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
class ParseGoalDTO {
    Long id;
    String name;
    Integer loops;
    List<ParseUserGroupDTO> userGroups;
    String errors;
}
