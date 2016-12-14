package com.osomapps.pt.goals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
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
@Table (name = "goal_parameter", schema = "ptcore")
@DynamicInsert
public class GoalParameter {
    @Id
    @SequenceGenerator(name = "GoalParameterIdSequence", sequenceName = "ptcore.goal_parameter_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GoalParameterIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    @ManyToMany
    @JoinTable(
            name = "goal_has_goal_parameter",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "goal_parameter_id") },
            inverseJoinColumns = { @JoinColumn(name = "goal_id") }
    )
    List<Goal> goals = new ArrayList<>(0);
}
