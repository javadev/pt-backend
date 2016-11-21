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
@Table (name = "parse_user", schema = "ptcore")
@DynamicInsert
public class ParseUser {
    @Id
    @SequenceGenerator(name = "ParseUserIdSequence", sequenceName = "ptcore.parse_user_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ParseUserIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    Integer sheet_index;
    String errors;
    @ManyToOne
    @JoinColumn(name="program_id")
    @JsonBackReference
    Program program;
    @OneToMany(mappedBy="parseUser")
    List<ParseWorkout> parseWorkouts;
}
