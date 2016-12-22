CREATE TABLE ptcore.exercise_file (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    file_name VARCHAR(200) NOT NULL DEFAULT '',
    file_size BIGINT NOT NULL DEFAULT 0,
    file_type VARCHAR(100) NOT NULL DEFAULT '',
    data_url TEXT
);

CREATE TABLE ptcore.exercise_file_has_exercise (
    exercise_file_id BIGINT NOT NULL REFERENCES exercise_file(id),
    exercise_id      BIGINT NOT NULL REFERENCES exercise(id)
);
