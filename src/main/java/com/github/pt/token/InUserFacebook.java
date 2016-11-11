package com.github.pt.token;

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
@Table(name = "in_user_facebook", schema = "ptcore")
@DynamicInsert
public class InUserFacebook {
    @Id
    @SequenceGenerator(name = "InUserFacebookIdSequence", sequenceName = "ptcore.in_user_facebook_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InUserFacebookIdSequence")
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
