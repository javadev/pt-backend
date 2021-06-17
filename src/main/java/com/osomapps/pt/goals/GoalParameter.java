package com.osomapps.pt.goals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "goal_parameter", schema = "ptcore")
@DynamicInsert
public class GoalParameter {
    @Id
    @GenericGenerator(
            name = "GoalParameterIdSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "ptcore.goal_parameter_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "GoalParameterIdSequence")
    Long id;

    LocalDateTime created;
    String name;

    @ManyToMany
    @JoinTable(
            name = "goal_has_goal_parameter",
            schema = "ptcore",
            joinColumns = {@JoinColumn(name = "goal_parameter_id")},
            inverseJoinColumns = {@JoinColumn(name = "goal_id")})
    List<Goal> goals = new ArrayList<>(0);
}
