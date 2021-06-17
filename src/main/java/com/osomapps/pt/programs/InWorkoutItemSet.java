package com.osomapps.pt.programs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(name = "in_workout_item_set", schema = "ptcore")
@DynamicInsert
public class InWorkoutItemSet {
    @Id
    @SequenceGenerator(
            name = "InWorkoutItemSetIdSequence",
            sequenceName = "ptcore.in_workout_item_set_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InWorkoutItemSetIdSequence")
    Long id;

    LocalDateTime created;
    Integer repetitions;
    Boolean repetitions_to_failure;
    Float weight;
    Boolean bodyweight;
    Integer time_in_sec;
    Integer speed;
    Integer incline;
    Integer resistance;
    Integer exercise_weight_percent;
    Float exercise_weight_in_kg;
    Float goal_weight_coef;
    Integer exercise_repetitions_percent;
    Integer goal_repetitions;
    Integer exercise_time_percent;
    Integer goal_time_in_sec;
    Integer exercise_speed_percent;
    Integer goal_speed;
    String exercise_basis;

    @ManyToOne
    @JoinColumn(name = "in_workout_item_id")
    @JsonBackReference
    InWorkoutItem inWorkoutItem;
}
