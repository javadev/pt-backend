package com.osomapps.pt.goals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
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
@Table (name = "goal", schema = "ptcore")
@DynamicInsert
public class Goal {
    @Id
    @SequenceGenerator(name = "GoalIdSequence", sequenceName = "ptcore.goal_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GoalIdSequence")
    Long id;
    LocalDateTime created;
    @Column(name = "d_goal_title")
    String dGoalTitle;
    @Column(name = "d_goal_title_2")
    String dGoalTitle2;
    @ManyToMany
    @JoinTable(
            name = "goal_has_goal_parameter",
            schema = "ptcore",
            joinColumns = { @JoinColumn(name = "goal_id") },
            inverseJoinColumns = { @JoinColumn(name = "goal_parameter_id") }
    )
    List<GoalParameter> goalParameters = new ArrayList<>(0);
    @ManyToOne
    @JoinColumn(name="goal_type_id")
    @JsonBackReference
    GoalType goalType;

}
