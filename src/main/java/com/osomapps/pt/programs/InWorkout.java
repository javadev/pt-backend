package com.osomapps.pt.programs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(name = "in_workout", schema = "ptcore")
@DynamicInsert
public class InWorkout {
    @Id
    @GenericGenerator(
            name = "InWorkoutIdSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "ptcore.in_workout_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "InWorkoutIdSequence")
    Long id;

    LocalDateTime created;
    String d_workout_name;
    Integer workout_index;
    Integer goal_index;
    String part_name;

    @ManyToOne
    @JoinColumn(name = "in_program_id")
    @JsonBackReference
    InProgram inProgram;

    @OneToMany(mappedBy = "inWorkout")
    List<InWarmupWorkoutItem> inWarmupWorkoutItems;

    @OneToMany(mappedBy = "inWorkout")
    List<InWorkoutItem> inWorkoutItems;
}
