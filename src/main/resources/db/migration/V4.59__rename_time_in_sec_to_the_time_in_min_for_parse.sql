ALTER TABLE ptcore.parse_warmup_workout_item RENAME COLUMN time_in_sec TO time_in_min;
ALTER TABLE ptcore.parse_workout_item_set RENAME COLUMN time_in_sec TO time_in_min;
ALTER TABLE ptcore.parse_warmup_workout_item ALTER COLUMN time_in_min TYPE DECIMAL(9,2);
ALTER TABLE ptcore.parse_workout_item_set ALTER COLUMN time_in_min TYPE DECIMAL(9,2);
