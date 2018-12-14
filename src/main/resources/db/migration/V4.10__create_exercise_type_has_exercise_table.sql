CREATE TABLE ptcore.exercise_type (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_exercise_type_name VARCHAR(45)
);

INSERT INTO ptcore.exercise_type (d_exercise_type_name) VALUES
('10'),
('20'),
('30');

INSERT INTO ptcore.dictionary_data (dname, dkey, dvalue) VALUES
('exercise_type_name', '10', 'Strength'),
('exercise_type_name', '20', 'Cardio'),
('exercise_type_name', '30', 'Weight Loss');

INSERT INTO ptcore.dictionary_data (dlanguage, dname, dkey, dvalue) VALUES
('nb', 'exercise_type_name', '10', 'Strength'),
('nb', 'exercise_type_name', '20', 'Cardio'),
('nb', 'exercise_type_name', '30', 'Weight Loss');

CREATE TABLE ptcore.exercise_type_has_exercise (
    exercise_type_id BIGINT NOT NULL REFERENCES ptcore.exercise_type(id),
    exercise_id      BIGINT NOT NULL REFERENCES ptcore.exercise(id)
);

INSERT INTO ptcore.exercise_type_has_exercise (exercise_type_id, exercise_id) VALUES
(1, 1);                 -- 20 Squat : Strength

