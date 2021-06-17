package com.osomapps.pt.tokenemail;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.osomapps.pt.token.InUser;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
    @GenericGenerator(
            name = "InUserEmailIdSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "ptcore.in_user_email_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "InUserEmailIdSequence")
    Long id;

    @ManyToOne
    @JoinColumn(name = "in_user_id")
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
    String resetToken;
}
