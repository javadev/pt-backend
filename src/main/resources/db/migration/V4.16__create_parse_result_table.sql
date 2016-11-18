CREATE TABLE ptcore.parse_result (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    program_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    user_name VARCHAR(200),
    workouts VARCHAR(1000),
    errors VARCHAR(1000),
    FOREIGN KEY (program_id) REFERENCES ptcore.program (id) ON UPDATE CASCADE ON DELETE CASCADE
);
