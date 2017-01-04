package com.osomapps.pt.admin.user;

import com.osomapps.pt.token.InUserGoal;
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
@Table (name = "in_user_goal_type", schema = "ptcore")
@DynamicInsert
public class InUserGoalType {
    @Id
    @SequenceGenerator(name = "InUserGoalTypeIdSequence", sequenceName = "ptcore.in_user_goal_type_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InUserGoalTypeIdSequence")
    Long id;
    LocalDateTime created;
    String d_user_goal_type;
    @OneToMany(mappedBy="inUserGoalType")
    List<InUserGoal> inUserGoals;
}
