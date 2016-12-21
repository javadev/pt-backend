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
@Table (name = "parse_round", schema = "ptcore")
@DynamicInsert
@DynamicUpdate
public class ParseRound {
    @Id
    @SequenceGenerator(name = "ParseRoundIdSequence", sequenceName = "ptcore.parse_round_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ParseRoundIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    @ManyToOne
    @JoinColumn(name = "parse_user_group_id")
    @JsonBackReference
    ParseUserGroup parseUserGroup;
    @OneToMany(mappedBy="parseRound")
    List<ParsePart> parseParts;
}
