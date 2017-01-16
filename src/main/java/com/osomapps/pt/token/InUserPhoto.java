package com.osomapps.pt.token;

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
@Table (name = "in_user_photo", schema = "ptcore")
@DynamicInsert
public class InUserPhoto {
    @Id
    @SequenceGenerator(name = "InUserPhotoIdSequence", sequenceName = "ptcore.in_user_photo_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InUserPhotoIdSequence")
    Long id;
    LocalDateTime created;
    Long goal_id;
    String file_name;
    Long file_size;
    String file_type;
    String data_url;
    @ManyToMany
    @JoinTable(
            name = "in_user_has_in_user_photo",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "in_user_photo_id") },
            inverseJoinColumns = { @JoinColumn(name = "in_user_id") }
    )
    List<InUser> inUsers = new ArrayList<>(0);
}
