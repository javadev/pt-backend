UPDATE ptcore.in_workout_item SET d_exercise_id = '0' WHERE d_exercise_id is NULL;
ALTER TABLE ptcore.in_workout_item
  RENAME COLUMN d_exercise_id TO exercise_id;
ALTER TABLE ptcore.in_workout_item
  ALTER COLUMN exercise_id TYPE INT USING exercise_id::integer,
  ALTER COLUMN exercise_id SET NOT NULL,
  ALTER COLUMN exercise_id SET DEFAULT 0;
UPDATE ptcore.in_warmup_workout_item SET d_exercise_id = '0' WHERE d_exercise_id is NULL;
ALTER TABLE ptcore.in_warmup_workout_item
  RENAME COLUMN d_exercise_id TO exercise_id;
ALTER TABLE ptcore.in_warmup_workout_item
  ALTER COLUMN exercise_id TYPE INT USING exercise_id::integer,
  ALTER COLUMN exercise_id SET NOT NULL,
  ALTER COLUMN exercise_id SET DEFAULT 0;
