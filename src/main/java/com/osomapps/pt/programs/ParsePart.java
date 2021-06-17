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
@Table(name = "parse_part", schema = "ptcore")
@DynamicInsert
@DynamicUpdate
public class ParsePart {
    @Id
    @SequenceGenerator(
            name = "ParsePartIdSequence",
            sequenceName = "ptcore.parse_part_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ParsePartIdSequence")
    Long id;

    LocalDateTime created;
    String name;

    @ManyToOne
    @JoinColumn(name = "parse_round_id")
    @JsonBackReference
    ParseRound parseRound;

    @OneToMany(mappedBy = "parsePart")
    List<ParseWorkout> parseWorkouts;
}
