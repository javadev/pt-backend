TRUNCATE TABLE ptcore.goal_parameter CASCADE;

INSERT INTO ptcore.goal_parameter(id, name) VALUES
(1, 'current-weight'),
(2, 'goal-weight'),
(3, 'distance');

SELECT setval('goal_parameter_id_seq', (select max(id) from ptcore.goal_parameter) + 1, true);
