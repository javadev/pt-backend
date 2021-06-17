package com.osomapps.pt.goals;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "goal_type", schema = "ptcore")
@DynamicInsert
public class GoalType {
    @Id
    @SequenceGenerator(
            name = "GoalTypeIdSequence",
            sequenceName = "ptcore.goal_type_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GoalTypeIdSequence")
    Long id;

    LocalDateTime created;
    String name;

    @OneToMany(mappedBy = "goalType")
    List<Goal> goals;
}
