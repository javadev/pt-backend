ALTER TABLE ptcore.in_program ADD COLUMN current_workout_index INT NOT NULL DEFAULT 0;
ALTER TABLE ptcore.in_workout ADD COLUMN workout_index INT NOT NULL DEFAULT 0;
