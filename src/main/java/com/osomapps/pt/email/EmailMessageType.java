package com.osomapps.pt.email;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table (name = "email_message_type", schema = "ptcore")
@DynamicInsert
public class EmailMessageType {
    @Id
    @SequenceGenerator(name = "EmailMessageTypeIdSequence", sequenceName = "ptcore.email_message_type_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EmailMessageTypeIdSequence")
    Long id;
    LocalDateTime created;
    String name;
    @OneToMany(mappedBy="emailMessageType", fetch = FetchType.EAGER)
    List<EmailMessageTemplate> emailMessageTemplates;
}
