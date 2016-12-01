package com.github.pt.exercises;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "exercise_output", schema = "ptcore")
@DynamicInsert
public class ExerciseOutput {
    @Id
    @SequenceGenerator(name = "ExerciseOutputIdSequence", sequenceName = "ptcore.exercise_output_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExerciseOutputIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    @ManyToMany
    @JoinTable(
            name = "exercise_output_has_exercise",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "exercise_output_id") },
            inverseJoinColumns = { @JoinColumn(name = "exercise_id") }
    )
    List<Exercise> exercises = new ArrayList<>(0);
}
