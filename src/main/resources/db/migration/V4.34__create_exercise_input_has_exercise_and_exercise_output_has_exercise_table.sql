CREATE TABLE ptcore.exercise_input (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(50)
);

INSERT INTO ptcore.exercise_input (name) VALUES
('Weight'),
('Reps'),
('Sets'),
('Time'),
('Speed'),
('Incline'),
('Resistance');

CREATE TABLE ptcore.exercise_input_has_exercise (
    exercise_input_id BIGINT NOT NULL REFERENCES ptcore.exercise_input(id),
    exercise_id      BIGINT NOT NULL REFERENCES ptcore.exercise(id)
);

CREATE TABLE ptcore.exercise_output (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(50)
);

INSERT INTO ptcore.exercise_output (name) VALUES
('Weight'),
('Reps'),
('Sets'),
('Time'),
('Speed'),
('Incline'),
('Resistance');

CREATE TABLE ptcore.exercise_output_has_exercise (
    exercise_output_id BIGINT NOT NULL REFERENCES ptcore.exercise_output(id),
    exercise_id      BIGINT NOT NULL REFERENCES ptcore.exercise(id)
);
