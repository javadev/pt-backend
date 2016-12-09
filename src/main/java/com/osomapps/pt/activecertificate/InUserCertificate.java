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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;

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
    @SequenceGenerator(name = "InUserCertificateIdSequence", sequenceName = "ptcore.in_user_certificate_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InUserCertificateIdSequence")
    Long id;
    LocalDateTime created;
    String code;
    Integer amount_of_days;
    @ManyToOne
    @JoinColumn(name="in_user_id")
    @JsonBackReference
    InUser inUser;
}
