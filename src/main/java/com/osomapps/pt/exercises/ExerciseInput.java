package com.osomapps.pt.exercises;

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
@Table (name = "exercise_input", schema = "ptcore")
@DynamicInsert
public class ExerciseInput {
    @Id
    @SequenceGenerator(name = "ExerciseInputIdSequence", sequenceName = "ptcore.exercise_input_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExerciseInputIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    @ManyToMany
    @JoinTable(
            name = "exercise_input_has_exercise",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "exercise_input_id") },
            inverseJoinColumns = { @JoinColumn(name = "exercise_id") }
    )
    List<Exercise> exercises = new ArrayList<>(0);
}