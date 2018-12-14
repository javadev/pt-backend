DROP TABLE ptcore.exercise_type CASCADE;
CREATE TABLE ptcore.exercise_type (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(50)
);

INSERT INTO ptcore.exercise_type (name) VALUES
('OnTime'),
('OnRepetitions'),
('OnMaxWeight'),
('OnDistance');

ALTER TABLE ptcore.exercise ADD COLUMN exercise_type_id BIGINT;

ALTER TABLE ptcore.exercise ADD CONSTRAINT exercise_exercise_type_id_fkey FOREIGN KEY
 (exercise_type_id) REFERENCES ptcore.exercise_type (id) ON UPDATE CASCADE ON DELETE CASCADE;
