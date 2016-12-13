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
@Table (name = "parse_warmup_workout_item_test", schema = "ptcore")
@DynamicInsert
public class ParseWarmupWorkoutItemTest {
    @Id
    @SequenceGenerator(name = "ParseWarmupWorkoutItemTestIdSequence", sequenceName = "ptcore.parse_warmup_workout_item_test_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ParseWarmupWorkoutItemTestIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    Integer speed;
    Integer incline;
    Integer time_in_min;
    @ManyToOne
    @JoinColumn(name="parse_workout_test_id")
    @JsonBackReference
    ParseWorkoutTest parseWorkoutTest;
}
