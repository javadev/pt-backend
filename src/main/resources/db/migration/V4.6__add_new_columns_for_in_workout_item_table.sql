ALTER TABLE ptcore.in_workout_item ADD COLUMN d_exercise_type VARCHAR(45);
ALTER TABLE ptcore.in_workout_item ADD COLUMN time_in_min INT;
ALTER TABLE ptcore.in_workout_item ADD COLUMN speed INT;
ALTER TABLE ptcore.in_workout_item ADD COLUMN resistance INT;

ALTER TABLE ptcore.in_workout_item_report ADD COLUMN time_in_min INT;
ALTER TABLE ptcore.in_workout_item_report ADD COLUMN speed INT;
ALTER TABLE ptcore.in_workout_item_report ADD COLUMN resistance INT;
