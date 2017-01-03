package com.osomapps.pt.exercises;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "exercise", schema = "ptcore")
@DynamicInsert
public class Exercise {
    @Id
    @SequenceGenerator(name = "ExerciseIdSequence", sequenceName = "ptcore.exercise_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExerciseIdSequence")
    Long id;
    LocalDateTime created;
    @Column(name = "d_exercise_name")
    String dExerciseName;
    @Column(name = "d_exercise_description")
    String dExerciseDescription;
    @Column(name = "exercise_id")
    Long exerciseId;
    @ManyToOne
    @JoinColumn(name="exercise_bodypart_id")
    @JsonBackReference
    ExerciseBodypart exerciseBodypart;
    @ManyToOne
    @JoinColumn(name="exercise_equipment_type_id")
    @JsonBackReference
    ExerciseEquipmentType exerciseEquipmentType;
    Integer cardio_percent;
    @ManyToMany
    @JoinTable(
            name = "exercise_type_has_exercise",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "exercise_id") },
            inverseJoinColumns = { @JoinColumn(name = "exercise_type_id") }
    )
    List<ExerciseType> exerciseTypes = new ArrayList<>(0);
    @ManyToMany
    @JoinTable(
            name = "exercise_input_has_exercise",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "exercise_id") },
            inverseJoinColumns = { @JoinColumn(name = "exercise_input_id") }
    )
    List<ExerciseInput> exerciseInputs = new ArrayList<>(0);
    @ManyToMany
    @JoinTable(
            name = "exercise_output_has_exercise",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "exercise_id") },
            inverseJoinColumns = { @JoinColumn(name = "exercise_output_id") }
    )
    List<ExerciseOutput> exerciseOutputs = new ArrayList<>(0);
    @ManyToMany
    @JoinTable(
            name = "exercise_file_has_exercise",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "exercise_id") },
            inverseJoinColumns = { @JoinColumn(name = "exercise_file_id") }
    )
    List<ExerciseFile> exerciseFiles = new ArrayList<>(0);
}
