package com.osomapps.pt.token;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.util.UUID;
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
@Table(name = "in_user_login", schema = "ptcore")
@DynamicInsert
public class InUserLogin {
    @Id
    @SequenceGenerator(
            name = "InUserLoginIdSequence",
            sequenceName = "ptcore.in_user_login_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InUserLoginIdSequence")
    Long id;

    @ManyToOne
    @JoinColumn(name = "in_user_id")
    @JsonBackReference
    InUser inUser;

    LocalDateTime created;
    String token = "pt-" + UUID.randomUUID().toString().replace("-", "");
    String ip_address;
}
