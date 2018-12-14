CREATE TABLE ptcore.in_goal (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    goal_id BIGINT,
    d_goal_title VARCHAR(45),
    d_goal_title_2 VARCHAR(45)
);

CREATE TABLE ptcore.in_goal_parameter (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(50)
);

CREATE TABLE ptcore.in_goal_has_in_goal_parameter (
    in_goal_id      BIGINT NOT NULL REFERENCES ptcore.in_goal(id),
    in_goal_parameter_id BIGINT NOT NULL REFERENCES ptcore.in_goal_parameter(id)
);

CREATE TABLE ptcore.goal (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_goal_title VARCHAR(45),
    d_goal_title_2 VARCHAR(45)
);

CREATE TABLE ptcore.goal_parameter (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(50)
);

CREATE TABLE ptcore.goal_has_goal_parameter (
    goal_id      BIGINT NOT NULL REFERENCES ptcore.goal(id),
    goal_parameter_id BIGINT NOT NULL REFERENCES ptcore.goal_parameter(id)
);

CREATE TABLE ptcore.in_user_has_in_goal (
    in_user_id      BIGINT NOT NULL REFERENCES ptcore.in_user(id),
    in_goal_id      BIGINT NOT NULL REFERENCES ptcore.in_goal(id)
);

INSERT INTO ptcore.dictionary_data (dname, dkey, dvalue) VALUES
('goal_title', '10', 'Loose weight'),
('goal_title', '20', 'Get a flat stomach'),
('goal_title', '30', 'Get stronger'),
('goal_title', '40', 'Build muscles'),
('goal_title', '50', 'Increase ednurance'),
('goal_title', '60', 'Correct posture'),
('goal_title', '70', 'Beat time on a specific distance'),
('goal_title', '80', 'Get back in shape after pregnancy');

INSERT INTO ptcore.dictionary_data (dname, dkey, dvalue) VALUES
('goal_title_2', '10', 'General'),
('goal_title_2', '20', 'Upper body'),
('goal_title_2', '30', 'Legs'),
('goal_title_2', '40', 'General'),
('goal_title_2', '50', 'Upper body'),
('goal_title_2', '60', 'Legs'),
('goal_title_2', '70', 'running'),
('goal_title_2', '80', 'cycling');

INSERT INTO ptcore.goal_parameter(name) VALUES
('Weight'),
('Distance');

INSERT INTO ptcore.goal(d_goal_title, d_goal_title_2) VALUES
('10', NULL),
('20', NULL),
('30', '10'),
('30', '20'),
('30', '30'),
('40', '40'),
('40', '50'),
('40', '60'),
('50', NULL),
('60', NULL),
('70', '70'),
('70', '80'),
('80', NULL);

