package com.github.pt.exercises;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
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
    Long exercise_id;
    @ManyToOne
    @JoinColumn(name="exercise_bodypart_id")
    @JsonBackReference
    ExerciseBodypart exerciseBodypart;
    @ManyToOne
    @JoinColumn(name="exercise_equipment_type_id")
    @JsonBackReference
    ExerciseEquipmentType exerciseEquipmentType;
}
