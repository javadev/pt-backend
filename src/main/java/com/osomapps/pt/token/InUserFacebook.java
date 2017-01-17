package com.osomapps.pt.token;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
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
@Table(name = "in_user_facebook", schema = "ptcore")
@DynamicInsert
public class InUserFacebook {
    @Id
    @GenericGenerator(
        name = "InUserFacebookIdSequence",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "ptcore.in_user_facebook_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
    )
    @GeneratedValue(generator = "InUserFacebookIdSequence")
    Long id;
    @ManyToOne
    @JoinColumn(name="in_user_id")
    @JsonBackReference
    InUser inUser;
    LocalDateTime created;
    String token;
    @Column(name="device_id")
    String deviceId;
    @Column(name="user_id")
    String userId;
    String user_name;
    String picture_url;
    LocalDate birthday;
}
