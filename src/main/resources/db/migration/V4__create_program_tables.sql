CREATE TABLE ptcore.in_program (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_user_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    d_program_type VARCHAR(45),
    FOREIGN KEY (in_user_id) REFERENCES ptcore.in_user (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ptcore.in_workout (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_program_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_workout_name VARCHAR(45),
    FOREIGN KEY (in_program_id) REFERENCES ptcore.in_program (id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE ptcore.in_workout_item (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_workout_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_exercise_id VARCHAR(45),
    d_exercise_name VARCHAR(45),
    sets INT,
    repetitions INT,
    weight INT,
    bodyweight BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (in_workout_id) REFERENCES ptcore.in_workout (id) ON UPDATE CASCADE ON DELETE CASCADE
);
