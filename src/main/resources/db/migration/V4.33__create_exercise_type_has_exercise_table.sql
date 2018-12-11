ALTER TABLE ONLY ptcore.exercise DROP CONSTRAINT exercise_exercise_type_id_fkey;

ALTER TABLE ptcore.exercise DROP COLUMN exercise_type_id;
