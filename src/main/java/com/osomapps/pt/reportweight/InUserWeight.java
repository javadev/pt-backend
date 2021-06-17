package com.osomapps.pt.reportweight;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.osomapps.pt.token.InUser;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "in_user_weight", schema = "ptcore")
@DynamicInsert
public class InUserWeight {
    @Id
    @SequenceGenerator(
            name = "InUserWeightIdSequence",
            sequenceName = "ptcore.in_user_weight_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InUserWeightIdSequence")
    Long id;

    LocalDateTime created;
    Float weight;

    @ManyToOne
    @JoinColumn(name = "in_user_id")
    @JsonBackReference
    InUser inUser;
}
