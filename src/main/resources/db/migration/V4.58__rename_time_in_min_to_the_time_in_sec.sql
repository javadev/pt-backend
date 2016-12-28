ALTER TABLE ptcore.in_warmup_workout_item RENAME COLUMN time_in_min TO time_in_sec;
ALTER TABLE ptcore.in_workout_item_set RENAME COLUMN time_in_min TO time_in_sec;
ALTER TABLE ptcore.in_workout_item_set_report RENAME COLUMN time_in_min TO time_in_sec;
ALTER TABLE ptcore.parse_warmup_workout_item RENAME COLUMN time_in_min TO time_in_sec;
ALTER TABLE ptcore.parse_workout_item_set RENAME COLUMN time_in_min TO time_in_sec;
