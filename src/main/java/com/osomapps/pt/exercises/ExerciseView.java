package com.osomapps.pt.exercises;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "exercise", schema = "ptcore")
class ExerciseView {
    @Id
    @SequenceGenerator(
            name = "ExerciseViewIdSequence",
            sequenceName = "ptcore.exercise_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExerciseViewIdSequence")
    Long id;

    LocalDateTime created;

    @Column(name = "d_exercise_name")
    String dExerciseName;

    @Column(name = "d_exercise_description")
    String dExerciseDescription;

    @Column(name = "exercise_id")
    Long exerciseId;

    Integer cardio_percent;

    @ManyToMany
    @JoinTable(
            name = "exercise_type_has_exercise",
            schema = "ptcore",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_type_id")})
    List<ExerciseType> exerciseTypes = new ArrayList<>(0);

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "exercise_file_has_exercise",
            schema = "ptcore",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_file_id")})
    List<ExerciseFileView> exerciseFiles = new ArrayList<>(0);
}
