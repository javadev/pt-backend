CREATE TABLE ptcore.email_message_type (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(50)
);

INSERT INTO ptcore.email_message_type (name) VALUES
('WelcomeMessage'),
('ForgetPasswordMessage');

CREATE TABLE ptcore.email_message_template (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    email_message_type_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_email_subject VARCHAR(45),
    d_email_text VARCHAR(45),
    FOREIGN KEY (email_message_type_id) REFERENCES ptcore.email_message_type (id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO ptcore.email_message_template (email_message_type_id, d_email_subject, d_email_text) VALUES
(1, '10', '10'),
(1, '20', '20');

INSERT INTO ptcore.dictionary_data (dname, dkey, dvalue) VALUES
('email_subject', '10', 'Welcome to the Personal Trainer Application!'),
('email_subject', '20', 'Reset password service');

INSERT INTO ptcore.dictionary_data (dname, dkey, dvalue) VALUES
('email_text', '10', 'Dear {{user_name}}!\n\nYour registration was completed succesfully. Please click to the link to confirm your e-mail {{email_confirm_link}}.\n\nYours PT support team.'),
('email_text', '20', 'Dear {{user_name}}!\n\nPlease click to the link to reset your password {{reset_password_link}}.\n\nYours PT support team.');

