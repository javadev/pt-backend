package com.osomapps.pt.token;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.osomapps.pt.admin.user.InUserGoalType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table (name = "in_user_goal", schema = "ptcore")
@DynamicInsert
public class InUserGoal {
    @Id
    @SequenceGenerator(name = "InUserGoalIdSequence", sequenceName = "ptcore.in_user_goal_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InUserGoalIdSequence")
    Long id;
    LocalDateTime created;
    @Column(name = "goal_id")
    Long goalId;
    String d_goal_title;
    String d_goal_title_2;
    String goal_value;
    @ManyToMany
    @JoinTable(
            name = "in_user_has_in_user_goal",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "in_user_goal_id") },
            inverseJoinColumns = { @JoinColumn(name = "in_user_id") }
    )
    List<InUser> inUsers = new ArrayList<>(0);
    @ManyToOne
    @JoinColumn(name="in_user_goal_type_id")
    @JsonBackReference
    InUserGoalType inUserGoalType;
    @ManyToMany
    @JoinTable(
            name = "in_user_goal_has_in_user_goal_photo",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "in_user_goal_id") },
            inverseJoinColumns = { @JoinColumn(name = "in_user_goal_photo_id") }
    )
    List<InUserGoalPhoto> inUserGoalPhotos = new ArrayList<>(0);
}
