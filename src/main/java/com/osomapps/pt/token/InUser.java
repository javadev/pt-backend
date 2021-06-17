package com.osomapps.pt.token;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.osomapps.pt.activecertificate.InUserCertificate;
import com.osomapps.pt.admin.user.InUserType;
import com.osomapps.pt.programs.InProgram;
import com.osomapps.pt.reportweight.InUserWeight;
import com.osomapps.pt.tokenemail.InUserEmail;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "in_user", schema = "ptcore")
@DynamicInsert
public class InUser {
    @Id
    @GenericGenerator(
            name = "InUserIdSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "ptcore.in_user_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "InUserIdSequence")
    Long id;

    LocalDateTime created;
    String d_sex;
    Float age;
    LocalDate birthday;
    Float height;
    Float weight;
    String d_level;
    String avatar_dataurl;
    LocalDateTime updated;

    @OneToMany(mappedBy = "inUser")
    @JsonManagedReference
    List<InUserFacebook> inUserFacebooks;

    @OneToMany(mappedBy = "inUser")
    @JsonManagedReference
    List<InUserLogin> inUserLogins;

    @OneToMany(mappedBy = "inUser")
    List<InUserLogout> inUserLogouts;

    @OneToMany(mappedBy = "inUser")
    List<InProgram> inPrograms;

    @OneToMany(mappedBy = "inUser")
    List<InUserWeight> inUserWeights;

    @OneToMany(mappedBy = "inUser")
    List<InUserCertificate> inUserCertificates;

    @OneToMany(mappedBy = "inUser")
    List<InUserEmail> inUserEmails;

    @ManyToOne
    @JoinColumn(name = "in_user_type_id")
    @JsonBackReference
    InUserType inUserType;

    @ManyToMany
    @JoinTable(
            name = "in_user_has_in_user_goal",
            schema = "ptcore",
            joinColumns = {@JoinColumn(name = "in_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "in_user_goal_id")})
    List<InUserGoal> inUserGoals = new ArrayList<>(0);

    @ManyToMany
    @JoinTable(
            name = "in_user_has_in_user_photo",
            schema = "ptcore",
            joinColumns = {@JoinColumn(name = "in_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "in_user_photo_id")})
    List<InUserPhoto> inUserPhotos = new ArrayList<>(0);
}
