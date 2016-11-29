CREATE TABLE ptcore.exercise_equipment_type (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_exercise_equipment_type_name VARCHAR(45)
);

INSERT INTO ptcore.exercise_equipment_type (d_exercise_equipment_type_name) VALUES
('10'),
('20'),
('30');

INSERT INTO ptcore.dictionary_data (dname, dkey, dvalue) VALUES
('exercise_equipment_type_name', '10', 'Treadmill'),
('exercise_equipment_type_name', '20', 'Bike'),
('exercise_equipment_type_name', '30', 'Rowing Machine');

INSERT INTO ptcore.dictionary_data (dlanguage, dname, dkey, dvalue) VALUES
('nb', 'exercise_equipment_type_name', '10', 'Treadmill'),
('nb', 'exercise_equipment_type_name', '20', 'Bike'),
('nb', 'exercise_equipment_type_name', '30', 'Rowing Machine');

ALTER TABLE ptcore.exercise ADD COLUMN exercise_equipment_type_id BIGINT;

ALTER TABLE ptcore.exercise ADD CONSTRAINT exercise_exercise_equipment_type_id_fkey FOREIGN KEY
 (exercise_equipment_type_id) REFERENCES ptcore.exercise_equipment_type (id) ON UPDATE CASCADE ON DELETE CASCADE;
