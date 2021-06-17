package com.osomapps.pt.admin.ptuser;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "pt_user", schema = "ptcore")
@DynamicInsert
public class PtUser implements Serializable {
    @Id
    @SequenceGenerator(
            name = "PtUserIdSequence",
            sequenceName = "ptcore.pt_user_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PtUserIdSequence")
    Long id;

    LocalDateTime created;
    String login;
    String password;
    String name;
    LocalDateTime activated;
    Boolean is_blocked;
    Boolean is_blocked_date_set;
    String blocked_comment;
    LocalDateTime blocked_start;
    LocalDateTime blocked_finish;
    LocalDateTime deleted;
    String deleted_comment;
    Boolean is_deleted;
    Boolean is_default_password;
    String description;
    String phone;
    String phone2;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pt_user_has_pt_role",
            schema = "ptcore",
            joinColumns = {@JoinColumn(name = "pt_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "pt_role_id")})
    List<PtRole> ptRoles = new ArrayList<>(0);
}
