package com.github.pt.programs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.pt.token.InUser;
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
@Table (name = "in_program", schema = "ptcore")
@DynamicInsert
public class InProgram {
    @Id
    @SequenceGenerator(name = "InProgramIdSequence", sequenceName = "ptcore.in_program_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InProgramIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    String d_program_type;
    @ManyToOne
    @JoinColumn(name="in_user_id")
    @JsonBackReference
    InUser inUser;
    @OneToMany(mappedBy="inProgram")
    List<InWorkout> inWorkouts;
}
