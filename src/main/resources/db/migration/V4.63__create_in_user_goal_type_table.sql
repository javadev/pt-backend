CREATE TABLE ptcore.in_user_goal_type (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_user_goal_type VARCHAR(45) NOT NULL
);

INSERT INTO ptcore.in_user_goal_type (d_user_goal_type) VALUES
('10'),
('20'),
('30'),
('40');

INSERT INTO ptcore.dictionary_data (dlanguage, dname, dkey, dvalue) VALUES
('en', 'user_goal_type', '10', 'weight'),
('en', 'user_goal_type', '20', 'percentage'),
('en', 'user_goal_type', '30', 'chart'),
('en', 'user_goal_type', '40', 'photos'),
('nb', 'user_goal_type', '10', 'weight'),
('nb', 'user_goal_type', '20', 'percentage'),
('nb', 'user_goal_type', '30', 'chart'),
('nb', 'user_goal_type', '40', 'photos');

ALTER TABLE ptcore.in_user_goal ADD COLUMN in_user_goal_type_id BIGINT;

ALTER TABLE ptcore.in_user_goal
  ADD CONSTRAINT in_user_goal_type_id_fkey FOREIGN KEY (in_user_goal_type_id)
      REFERENCES in_user_goal_type (id)
      ON UPDATE CASCADE ON DELETE CASCADE;
