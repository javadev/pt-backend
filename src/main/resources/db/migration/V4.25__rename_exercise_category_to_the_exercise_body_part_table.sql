CREATE TABLE ptcore.exercise_bodypart (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_exercise_bodypart_name VARCHAR(45)
);

INSERT INTO ptcore.exercise_bodypart
   (
      SELECT id,
             created,
             d_exercise_category_name
        FROM ptcore.exercise_category
   );

UPDATE ptcore.dictionary_data SET dname = 'exercise_bodypart_name' WHERE dname = 'exercise_category_name';

ALTER TABLE ptcore.exercise RENAME COLUMN exercise_category_id TO exercise_bodypart_id;

ALTER TABLE ONLY ptcore.exercise DROP CONSTRAINT exercise_exercise_category_id_fkey;

ALTER TABLE ptcore.exercise ADD CONSTRAINT exercise_exercise_bodypart_id_fkey FOREIGN KEY
 (exercise_bodypart_id) REFERENCES ptcore.exercise_bodypart (id) ON UPDATE CASCADE ON DELETE CASCADE;

DROP TABLE IF EXISTS ptcore.exercise_category;
