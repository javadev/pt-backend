package com.osomapps.pt.programs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "in_workout_item_set_report", schema = "ptcore")
@DynamicInsert
public class InWorkoutItemSetReport {
    @Id
    @SequenceGenerator(
            name = "InWorkoutItemSetReportIdSequence",
            sequenceName = "ptcore.in_workout_item_set_report_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "InWorkoutItemSetReportIdSequence")
    Long id;

    LocalDateTime created;
    Integer repetitions;
    Float weight;
    Boolean bodyweight;
    Integer time_in_sec;
    Integer speed;
    Integer incline;
    Integer resistance;

    @ManyToOne
    @JoinColumn(name = "in_workout_item_report_id")
    @JsonBackReference
    InWorkoutItemReport inWorkoutItemReport;
}
