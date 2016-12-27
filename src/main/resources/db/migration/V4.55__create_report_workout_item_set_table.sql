ALTER TABLE ptcore.parse_workout_item DROP COLUMN sets;
ALTER TABLE ptcore.parse_workout_item DROP COLUMN repetitions;
ALTER TABLE ptcore.parse_workout_item DROP COLUMN weight;
ALTER TABLE ptcore.parse_workout_item DROP COLUMN bodyweight;
ALTER TABLE ptcore.parse_workout_item DROP COLUMN time_in_min;
ALTER TABLE ptcore.parse_workout_item DROP COLUMN speed;
ALTER TABLE ptcore.parse_workout_item DROP COLUMN resistance;
ALTER TABLE ptcore.in_workout_item_set ALTER COLUMN weight TYPE DECIMAL(9,2);

CREATE TABLE ptcore.parse_workout_item_set (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    parse_workout_item_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    repetitions INT,
    repetitions_to_failure BOOLEAN NOT NULL DEFAULT FALSE,
    weight DECIMAL(9,2),
    bodyweight BOOLEAN NOT NULL DEFAULT FALSE,
    time_in_min INT,
    speed INT,
    incline INT,
    resistance INT,
    FOREIGN KEY (parse_workout_item_id) REFERENCES ptcore.parse_workout_item (id) ON UPDATE CASCADE ON DELETE CASCADE
);
