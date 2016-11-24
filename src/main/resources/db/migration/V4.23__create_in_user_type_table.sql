CREATE TABLE ptcore.in_user_type (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_user_type VARCHAR(45) NOT NULL
);

INSERT INTO ptcore.in_user_type (d_user_type) VALUES
('10'),
('20');

INSERT INTO ptcore.dictionary_data (dlanguage, dname, dkey, dvalue) VALUES
('en', 'user_type', '10', 'Test'),
('en', 'user_type', '20', 'Premium'),
('nb', 'user_type', '10', 'Test'),
('nb', 'user_type', '20', 'Premium');

ALTER TABLE ptcore.in_user ADD COLUMN in_user_type_id BIGINT;

ALTER TABLE ptcore.in_user
  ADD CONSTRAINT in_user_type_id_fkey FOREIGN KEY (in_user_type_id)
      REFERENCES in_user (id)
      ON UPDATE CASCADE ON DELETE CASCADE;
