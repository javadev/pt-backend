CREATE TABLE ptcore.parse_workout (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_user_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    column_index INT NOT NULL DEFAULT 0,
    row_index INT NOT NULL DEFAULT 0,
    FOREIGN KEY (parse_user_id) REFERENCES ptcore.parse_user (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ptcore.parse_warmup_workout_item (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_workout_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    speed INT,
    incline INT,
    time_in_min INT,
    FOREIGN KEY (parse_workout_id) REFERENCES ptcore.parse_workout (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ptcore.parse_workout_item (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_workout_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    sets INT,
    repetitions INT,
    weight INT,
    bodyweight BOOLEAN NOT NULL DEFAULT FALSE,
    time_in_min INT,
    speed INT,
    resistance INT,
    FOREIGN KEY (parse_workout_id) REFERENCES ptcore.parse_workout (id) ON UPDATE CASCADE ON DELETE CASCADE
);
