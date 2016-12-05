ALTER TABLE ptcore.in_workout_item_report DROP COLUMN sets;
ALTER TABLE ptcore.in_workout_item_report DROP COLUMN repetitions;
ALTER TABLE ptcore.in_workout_item_report DROP COLUMN weight;
ALTER TABLE ptcore.in_workout_item_report DROP COLUMN bodyweight;
ALTER TABLE ptcore.in_workout_item_report DROP COLUMN time_in_min;
ALTER TABLE ptcore.in_workout_item_report DROP COLUMN speed;
ALTER TABLE ptcore.in_workout_item_report DROP COLUMN resistance;

CREATE TABLE ptcore.in_workout_item_set_report (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_workout_item_report_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    repetitions INT,
    weight INT,
    bodyweight BOOLEAN NOT NULL DEFAULT FALSE,
    time_in_min INT,
    speed INT,
    incline INT,
    resistance INT,
    FOREIGN KEY (in_workout_item_report_id) REFERENCES ptcore.in_workout_item_report (id) ON UPDATE CASCADE ON DELETE CASCADE
);
