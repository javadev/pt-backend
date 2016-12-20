package com.osomapps.pt.tokenemail;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.osomapps.pt.token.InUser;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
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
@Table(name = "in_user_email", schema = "ptcore")
@DynamicInsert
public class InUserEmail {
    @Id
    @SequenceGenerator(name = "InUserEmailIdSequence", sequenceName = "ptcore.in_user_email_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InUserEmailIdSequence")
    Long id;
    @ManyToOne
    @JoinColumn(name="in_user_id")
    @JsonBackReference
    InUser inUser;
    LocalDateTime created;
    String login;
    String password;
    String user_name;
    String device_id;
    LocalDateTime confirmed;
    Boolean is_confirmed;
    @Column(name = "confirm_token")
    String confirmToken = "em-" + UUID.randomUUID().toString().replace("-", "");
    LocalDateTime reseted;
    Boolean is_reseted;
    @Column(name = "reset_token")
    String resetToken = "re-" + UUID.randomUUID().toString().replace("-", "");
}
