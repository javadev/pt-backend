package com.osomapps.pt.programs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(name = "parse_workout_item", schema = "ptcore")
@DynamicInsert
@DynamicUpdate
public class ParseWorkoutItem {
    @Id
    @SequenceGenerator(
            name = "ParseWorkoutItemIdSequence",
            sequenceName = "ptcore.parse_workout_item_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ParseWorkoutItemIdSequence")
    Long id;

    LocalDateTime created;
    String name;
    Integer exercise_id;
    Integer column_index;
    Integer row_index;
    Long in_workout_item_id;

    @OneToMany(mappedBy = "parseWorkoutItem")
    List<ParseWorkoutItemSet> parseWorkoutItemSets;

    @ManyToOne
    @JoinColumn(name = "parse_workout_id")
    @JsonBackReference
    ParseWorkout parseWorkout;
}
