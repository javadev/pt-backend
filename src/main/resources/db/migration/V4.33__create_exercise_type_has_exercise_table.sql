CREATE TABLE ptcore.exercise_type_has_exercise (
    exercise_type_id BIGINT NOT NULL REFERENCES exercise_type(id),
    exercise_id      BIGINT NOT NULL REFERENCES exercise(id)
);

ALTER TABLE ONLY ptcore.exercise DROP CONSTRAINT exercise_exercise_type_id_fkey;

ALTER TABLE ptcore.exercise DROP COLUMN exercise_type_id;
