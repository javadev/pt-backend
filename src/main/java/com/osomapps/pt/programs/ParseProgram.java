package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(name = "parse_program", schema = "ptcore")
@DynamicInsert
public class ParseProgram {
    @Id
    @SequenceGenerator(
            name = "ParseProgramIdSequence",
            sequenceName = "ptcore.parse_program_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ParseProgramIdSequence")
    Long id;

    LocalDateTime created;
    String name;
    String file_name;
    Long file_size;
    String file_type;
    String data_url;
    LocalDateTime updated;
    Boolean active;

    @OneToMany(mappedBy = "parseProgram")
    List<ParseGoal> parseGoals;

    @OneToMany(mappedBy = "parseProgram")
    List<ParseExercise> parseExercises;
}
