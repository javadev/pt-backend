CREATE TABLE ptcore.in_workout_item_report (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_workout_item_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    sets INT,
    repetitions INT,
    weight INT,
    bodyweight BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (in_workout_item_id) REFERENCES ptcore.in_workout_item (id) ON UPDATE CASCADE ON DELETE CASCADE
);
