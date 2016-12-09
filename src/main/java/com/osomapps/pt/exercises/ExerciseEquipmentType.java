package com.osomapps.pt.exercises;

import java.time.LocalDateTime;
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
@Table (name = "exercise_equipment_type", schema = "ptcore")
@DynamicInsert
public class ExerciseEquipmentType {
    @Id
    @SequenceGenerator(name = "ExerciseEquipmentTypeIdSequence", sequenceName = "ptcore.exercise_equipment_type_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExerciseEquipmentTypeIdSequence")
    Long id;
    LocalDateTime created;
    @Column(name = "d_exercise_equipment_type_name")
    String dExerciseEquipmentTypeName;
    @OneToMany(mappedBy = "exerciseEquipmentType")
    List<Exercise> exercises;
}
