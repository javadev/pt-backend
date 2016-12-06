ALTER TABLE ptcore.in_workout_item ADD COLUMN incline INT;
ALTER TABLE ptcore.in_workout_item ADD COLUMN repetitions_to_failure BOOLEAN NOT NULL DEFAULT FALSE;
