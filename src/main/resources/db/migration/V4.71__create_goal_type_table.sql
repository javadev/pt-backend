CREATE TABLE ptcore.goal_type (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(50) NOT NULL
);

INSERT INTO ptcore.goal_type (name) VALUES
('weight'),
('percentage'),
('chart'),
('photos');

ALTER TABLE ptcore.goal ADD COLUMN goal_type_id BIGINT;

ALTER TABLE ptcore.goal
  ADD CONSTRAINT goal_type_id_fkey FOREIGN KEY (goal_type_id)
      REFERENCES goal_type (id)
      ON UPDATE CASCADE ON DELETE CASCADE;
