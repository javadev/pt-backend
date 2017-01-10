ALTER TABLE ptcore.parse_warmup_workout_item ADD COLUMN exercise_id INT NOT NULL DEFAULT 0;
ALTER TABLE ptcore.parse_workout_item ADD COLUMN exercise_id INT NOT NULL DEFAULT 0;
