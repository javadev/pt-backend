package com.github.pt.admin.ptuser;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "pt_role", schema = "ptcore")
@DynamicInsert
public class PtRole {
    @Id
    @SequenceGenerator(name = "PtRoleIdSequence", sequenceName = "ptcore.pt_role_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PtRoleIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    @ManyToMany
    @JoinTable(
            name = "pt_user_has_pt_role",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "pt_role_id") },
            inverseJoinColumns = { @JoinColumn(name = "pt_user_id") }
    )
    List<PtUser> ptUsers = new ArrayList<>(0);
}
