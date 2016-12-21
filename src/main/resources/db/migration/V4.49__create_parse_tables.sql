CREATE TABLE ptcore.parse_program (
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

CREATE TABLE ptcore.parse_goal (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_program_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    FOREIGN KEY (parse_program_id) REFERENCES ptcore.parse_program (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ptcore.parse_user_group (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_goal_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    FOREIGN KEY (parse_goal_id) REFERENCES ptcore.parse_goal (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ptcore.parse_round (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_user_group_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    FOREIGN KEY (parse_user_group_id) REFERENCES ptcore.parse_user_group (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ptcore.parse_part (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_round_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    FOREIGN KEY (parse_round_id) REFERENCES ptcore.parse_round (id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE ptcore.parse_workout CASCADE;

CREATE TABLE ptcore.parse_workout (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_part_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(100),
    column_index INT NOT NULL DEFAULT 0,
    row_index INT NOT NULL DEFAULT 0,
    FOREIGN KEY (parse_part_id) REFERENCES ptcore.parse_part (id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE ptcore.parse_user CASCADE;

DROP TABLE ptcore.program CASCADE;
