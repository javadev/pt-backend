package com.osomapps.pt.programs;

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
@Table (name = "program_test", schema = "ptcore")
@DynamicInsert
public class ProgramTest {
    @Id
    @SequenceGenerator(name = "ProgramTestIdSequence", sequenceName = "ptcore.program_test_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProgramTestIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    String file_name;
    Long file_size;
    String file_type;
    String data_url;
    LocalDateTime updated;
    Boolean active;
    @OneToMany(mappedBy="programTest")
    List<ParseWorkoutTest> parseWorkoutTests;
}
