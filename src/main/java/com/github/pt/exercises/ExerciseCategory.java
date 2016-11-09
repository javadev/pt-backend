package com.github.pt.exercises;

import java.time.LocalDateTime;
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
@Table (name = "exercise_category", schema = "ptcore")
@DynamicInsert
class ExerciseCategory {
    @Id
    @SequenceGenerator(name = "ExerciseCategoryIdSequence", sequenceName = "ptcore.exercise_category_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExerciseCategoryIdSequence")
    Long id;
    LocalDateTime created;
    String d_exercise_category_name;
    @OneToMany(mappedBy="exerciseCategory")
    List<Exercise> exercise;
}
