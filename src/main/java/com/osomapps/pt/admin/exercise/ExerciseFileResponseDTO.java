package com.osomapps.pt.admin.exercise;

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
class ExerciseFileResponseDTO {
    Long id;
    String file_name;
    Long file_size;
    String file_type;
    String data_url;
}
