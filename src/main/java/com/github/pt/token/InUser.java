package com.github.pt.token;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "in_user", schema = "ptcore")
public class InUser {
    @Id
    @SequenceGenerator(name = "InUserIdSequence", sequenceName = "ptcore.in_user_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InUserIdSequence")
    Long id;
    LocalDateTime created;
    String d_sex;
    Float age;
    Float height;
    Float weight;
    String d_level;
    LocalDateTime updated;
    @OneToMany
    List<InUserFacebook> inUserFacebooks;
    @OneToMany
    List<InUserLogin> inUserLogins;
    @OneToMany
    List<InUserLogout> inUserLogouts;
}
