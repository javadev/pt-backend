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
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "parse_workout_item_test", schema = "ptcore")
@DynamicInsert
@DynamicUpdate
public class ParseWorkoutItemTest {
    @Id
    @SequenceGenerator(name = "ParseWorkoutItemTestIdSequence", sequenceName = "ptcore.parse_workout_item_test_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ParseWorkoutItemTestIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    Integer sets;
    Integer repetitions;
    Integer weight;
    Boolean bodyweight;
    Integer time_in_min;
    Integer speed;
    Integer resistance;
    @ManyToOne
    @JoinColumn(name="parse_workout_test_id")
    @JsonBackReference
    ParseWorkoutTest parseWorkoutTest;
}
