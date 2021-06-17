package com.osomapps.pt.exercises;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Entity
@Table(name = "exercise_file", schema = "ptcore")
public class ExerciseFilePreview {
    @Id
    @SequenceGenerator(
            name = "ExerciseFilePreviewIdSequence",
            sequenceName = "ptcore.exercise_file_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExerciseFilePreviewIdSequence")
    Long id;

    LocalDateTime created;
    String file_name;
    Long file_size;
    String file_type;

    @ManyToMany
    @JoinTable(
            name = "exercise_file_has_exercise",
            schema = "ptcore",
            joinColumns = {@JoinColumn(name = "exercise_file_id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_id")})
    List<Exercise> exercises = new ArrayList<>(0);
}
