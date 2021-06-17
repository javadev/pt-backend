package com.osomapps.pt.exercises;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(name = "exercise_bodypart", schema = "ptcore")
@DynamicInsert
public class ExerciseBodypart {
    @Id
    @GenericGenerator(
            name = "ExerciseBodypartIdSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "ptcore.exercise_bodypart_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "ExerciseBodypartIdSequence")
    Long id;

    LocalDateTime created;

    @Column(name = "d_exercise_bodypart_name")
    String dExerciseBodypartName;

    @OneToMany(mappedBy = "exerciseBodypart")
    List<Exercise> exercises;
}
