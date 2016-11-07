package com.github.pt.programs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
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
@Table (name = "in_workout_item", schema = "ptcore")
@DynamicInsert
public class InWorkoutItem {
    @Id
    @SequenceGenerator(name = "InWorkoutItemIdSequence", sequenceName = "ptcore.in_workout_item_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InWorkoutItemIdSequence")
    Long id;
    LocalDateTime created;
    String d_exercise_id;
    String d_exercise_name;
    Integer sets;
    Integer repetitions;
    Integer weight;
    Boolean bodyweight;
    @ManyToOne
    @JoinColumn(name="in_workout_id")
    @JsonBackReference
    InWorkout inWorkout;
}