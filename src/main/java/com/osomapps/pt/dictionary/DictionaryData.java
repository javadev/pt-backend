package com.osomapps.pt.dictionary;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "dictionary_data", schema = "ptcore")
@DynamicInsert
public class DictionaryData {
    @Id
    @GenericGenerator(
            name = "DictionaryIdSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "ptcore.dictionary_data_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "DictionaryIdSequence")
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
