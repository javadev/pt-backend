package com.github.pt.admin.user;

import com.github.pt.token.InUser;
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
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "in_user_type", schema = "ptcore")
@DynamicInsert
public class InUserType {
    @Id
    @SequenceGenerator(name = "InUserTypeIdSequence", sequenceName = "ptcore.in_user_type_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InUserTypeIdSequence")
    Long id;
    LocalDateTime created;
    String d_user_type;
    @OneToMany(mappedBy="inUserType")
    List<InUser> inUsers;
}
