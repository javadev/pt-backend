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
@Table (name = "in_workout", schema = "ptcore")
@DynamicInsert
class InWorkout {
    @Id
    @SequenceGenerator(name = "InWorkoutIdSequence", sequenceName = "ptcore.in_workout_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InWorkoutIdSequence")
    Long id;
    LocalDateTime created;
    String d_workout_name;
    @ManyToOne
    @JoinColumn(name="in_program_id")
    @JsonBackReference
    InProgram inProgram;
    @OneToMany(mappedBy="inWorkout")
    List<InWorkoutItem> inWorkoutItems;
}
