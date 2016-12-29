package com.osomapps.pt.programs;

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
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "parse_exercise", schema = "ptcore")
@DynamicInsert
@DynamicUpdate
public class ParseExercise {
    @Id
    @SequenceGenerator(name = "ParseExerciseIdSequence", sequenceName = "ptcore.parse_exercise_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ParseExerciseIdSequence")
    Long id;
    LocalDateTime created;
    Long exercise_id;
    String exercise_name;
    Integer user_group_1_percent;
    Integer user_group_2_percent;
    Integer user_group_3_percent;
    Integer user_group_4_percent;
    String basis_for_calculations;
    @ManyToOne
    @JoinColumn(name="parse_program_id")
    @JsonBackReference
    ParseProgram parseProgram;
}
