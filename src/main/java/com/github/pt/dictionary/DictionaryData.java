package com.github.pt.dictionary;

import java.time.LocalDateTime;
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
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "dictionary_data", schema = "ptcore")
@DynamicInsert
public class DictionaryData {
    @Id
    @SequenceGenerator(name = "DictionaryIdSequence", sequenceName = "ptcore.dictionary_data_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DictionaryIdSequence")
    Long id;
    LocalDateTime created;
    String dlanguage;
    Boolean valid;
    LocalDateTime fromdate;
    LocalDateTime todate;
    String dname;
    String dkey;
    String dvalue;
}
