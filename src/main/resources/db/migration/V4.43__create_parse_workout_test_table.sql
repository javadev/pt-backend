CREATE TABLE ptcore.program_test (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(200),
    data_url TEXT,
    updated TIMESTAMP WITHOUT TIME ZONE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    file_name VARCHAR(200) NOT NULL DEFAULT '',
    file_size BIGINT NOT NULL DEFAULT 0,
    file_type VARCHAR(100) NOT NULL DEFAULT ''
);

CREATE TABLE ptcore.parse_workout_test (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    program_test_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    FOREIGN KEY (program_test_id) REFERENCES ptcore.program_test (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ptcore.parse_warmup_workout_item_test (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_workout_test_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    speed INT,
    incline INT,
    time_in_min INT,
    FOREIGN KEY (parse_workout_test_id) REFERENCES ptcore.parse_workout_test (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ptcore.parse_workout_item_test (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_workout_test_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    sets INT,
    repetitions INT,
    weight INT,
    bodyweight BOOLEAN NOT NULL DEFAULT FALSE,
    time_in_min INT,
    speed INT,
    resistance INT,
    FOREIGN KEY (parse_workout_test_id) REFERENCES ptcore.parse_workout_test (id) ON UPDATE CASCADE ON DELETE CASCADE
);
