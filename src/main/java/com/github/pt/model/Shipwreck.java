package com.github.pt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "SHIPWRECK", schema = "ptcore")
public class Shipwreck {
    @Id
    @SequenceGenerator(name = "ShipwreckIdSequence", sequenceName = "ptcore.SHIPWRECK_ID_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ShipwreckIdSequence")
    Long id;
    String name;
    String description;
    String condition;
    Integer depth;
    Double latitude;
    Double longitude;
    Integer yearDiscovered;
}
