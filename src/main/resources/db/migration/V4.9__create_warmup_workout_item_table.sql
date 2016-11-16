CREATE TABLE ptcore.in_warmup_workout_item (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_workout_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_exercise_id VARCHAR(45),
    d_exercise_name VARCHAR(45),
    speed INT,
    incline INT,
    time_in_min INT,
    FOREIGN KEY (in_workout_id) REFERENCES ptcore.in_workout (id) ON UPDATE CASCADE ON DELETE CASCADE
);
