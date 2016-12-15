package com.osomapps.pt.email;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table (name = "email_message_template", schema = "ptcore")
@DynamicInsert
public class EmailMessageTemplate {
    @Id
    @SequenceGenerator(name = "EmailMessageTemplateIdSequence", sequenceName = "ptcore.email_message_template_id_seq",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EmailMessageTemplateIdSequence")
    Long id;
    LocalDateTime created;
    @Column(name = "d_email_subject")
    String dEmailSubject;
    @Column(name = "d_email_text")
    String dEmailText;
    @ManyToOne
    @JoinColumn(name="email_message_type_id")
    @JsonBackReference
    EmailMessageType emailMessageType;
}
