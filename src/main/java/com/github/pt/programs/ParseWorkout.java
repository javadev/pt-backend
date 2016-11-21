package com.github.pt.programs;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table (name = "parse_workout", schema = "ptcore")
@DynamicInsert
public class ParseWorkout {
    @Id
    @SequenceGenerator(name = "ParseWorkoutIdSequence", sequenceName = "ptcore.parse_workout_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ParseWorkoutIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    Integer column_index;
    Integer row_index;
    @ManyToOne
    @JoinColumn(name="parse_user_id")
    @JsonBackReference
    ParseUser parseUser;
    @OneToMany(mappedBy="parseWorkout")
    List<ParseWarmupWorkoutItem> parseWarmupWorkoutItems;
    @OneToMany(mappedBy="parseWorkout")
    List<ParseWorkoutItem> parseWorkoutItems;
}
