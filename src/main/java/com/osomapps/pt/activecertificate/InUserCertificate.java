package com.osomapps.pt.activecertificate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.osomapps.pt.token.InUser;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "in_user_certificate", schema = "ptcore")
@DynamicInsert
public class InUserCertificate {
    @Id
    @GenericGenerator(
        name = "InUserCertificateIdSequence",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "ptcore.in_user_certificate_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
    )
    @GeneratedValue(generator = "InUserCertificateIdSequence")
    Long id;
    LocalDateTime created;
    String code;
    Integer amount_of_days;
    @ManyToOne
    @JoinColumn(name="in_user_id")
    @JsonBackReference
    InUser inUser;
}
