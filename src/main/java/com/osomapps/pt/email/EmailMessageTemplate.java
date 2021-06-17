package com.osomapps.pt.email;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "email_message_template", schema = "ptcore")
@DynamicInsert
public class EmailMessageTemplate {
    @Id
    @GenericGenerator(
            name = "EmailMessageTemplateIdSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "ptcore.email_message_template_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "EmailMessageTemplateIdSequence")
    Long id;

    LocalDateTime created;

    @Column(name = "d_email_subject")
    String dEmailSubject;

    @Column(name = "d_email_text")
    String dEmailText;

    @ManyToOne
    @JoinColumn(name = "email_message_type_id")
    @JsonBackReference
    EmailMessageType emailMessageType;
}
