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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "exercise_type", schema = "ptcore")
@DynamicInsert
public class ExerciseType {
    @Id
    @SequenceGenerator(name = "ExerciseTypeIdSequence", sequenceName = "ptcore.exercise_type_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExerciseTypeIdSequence")
    Long id;
    LocalDateTime created;
    String d_exercise_type_name;
    @ManyToMany
    @JoinTable(
            name = "exercise_type_has_exercise",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "exercise_type_id") },
            inverseJoinColumns = { @JoinColumn(name = "exercise_id") }
    )
    List<Exercise> exercises = new ArrayList<>(0);
}
