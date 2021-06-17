package com.osomapps.pt.programs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.osomapps.pt.token.InUser;
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
@Table(name = "in_program", schema = "ptcore")
@DynamicInsert
public class InProgram {
    @Id
    @GenericGenerator(
            name = "InProgramIdSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "ptcore.in_program_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "InProgramIdSequence")
    Long id;

    LocalDateTime created;
    String name;
    String d_program_type;
    Integer current_workout_index;

    @ManyToOne
    @JoinColumn(name = "in_user_id")
    @JsonBackReference
    InUser inUser;

    @OneToMany(mappedBy = "inProgram")
    List<InWorkout> inWorkouts;
}
