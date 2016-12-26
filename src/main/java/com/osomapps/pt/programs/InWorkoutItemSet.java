package com.osomapps.pt.programs;

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
@Table (name = "in_workout_item_set", schema = "ptcore")
@DynamicInsert
public class InWorkoutItemSet {
    @Id
    @SequenceGenerator(name = "InWorkoutItemSetIdSequence", sequenceName = "ptcore.in_workout_item_set_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InWorkoutItemSetIdSequence")
    Long id;
    LocalDateTime created;
    Integer repetitions;
    Boolean repetitions_to_failure;
    Float weight;
    Boolean bodyweight;
    Integer time_in_min;
    Integer speed;
    Integer incline;
    Integer resistance;
    @ManyToOne
    @JoinColumn(name="in_workout_item_id")
    @JsonBackReference
    InWorkoutItem inWorkoutItem;
}
