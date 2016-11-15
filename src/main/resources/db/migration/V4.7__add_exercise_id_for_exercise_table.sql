ALTER TABLE ptcore.exercise ADD COLUMN exercise_id INT NOT NULL DEFAULT 0;

UPDATE ptcore.exercise SET exercise_id = CAST(d_exercise_name AS INT);
