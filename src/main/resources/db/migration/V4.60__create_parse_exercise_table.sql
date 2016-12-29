CREATE TABLE ptcore.parse_exercise (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_program_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    exercise_id INT,
    exercise_name VARCHAR(50),
    user_group_1_percent INT NOT NULL DEFAULT 0,
    user_group_2_percent INT NOT NULL DEFAULT 0,
    user_group_3_percent INT NOT NULL DEFAULT 0,
    user_group_4_percent INT NOT NULL DEFAULT 0,
    basis_for_calculations VARCHAR(30),
    FOREIGN KEY (parse_program_id) REFERENCES ptcore.parse_program (id) ON UPDATE CASCADE ON DELETE CASCADE
);
