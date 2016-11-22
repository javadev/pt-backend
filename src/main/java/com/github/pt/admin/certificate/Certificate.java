package com.github.pt.admin.certificate;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "certificate", schema = "ptcore")
@DynamicInsert
public class Certificate {
    @Id
    @SequenceGenerator(name = "CertificateIdSequence", sequenceName = "ptcore.certificate_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CertificateIdSequence")
    Long id;
    LocalDateTime created;
    String code;
    Integer amount_of_days;
    Boolean activated;
}
