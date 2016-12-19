package com.osomapps.pt.xlsx;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@ToString
class UserGroup {
    String name;
    int columnIndex;
    int rowIndex;
    List<Round> rounds = new ArrayList<>();
}
