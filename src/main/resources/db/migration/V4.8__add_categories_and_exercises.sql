INSERT INTO ptcore.exercise_category (d_exercise_category_name) VALUES
('800'),
('850'),
('890');

INSERT INTO ptcore.exercise (exercise_category_id, exercise_id, d_exercise_name) VALUES
(9, 810, '810'),
(9, 820, '820'),
(9, 830, '830'),
(9, 840, '840'),
(10, 860, '860'),
(10, 870, '870'),
(10, 880, '880'),
(11, 900, '900'),
(11, 910, '910'),
(11, 920, '920');

INSERT INTO ptcore.dictionary_data (dname, dkey, dvalue) VALUES
('exercise_category_name', '800', 'Treadmill'),
('exercise_category_name', '850', 'Bike'),
('exercise_category_name', '890', 'Rowing Machine');

INSERT INTO ptcore.dictionary_data (dname, dkey, dvalue) VALUES
('exercise_name', '810', 'Steady Pace Walk or Jog'),
('exercise_name', '820', '4 x 4 intervals'),
('exercise_name', '830', '3 x 3 intervals'),
('exercise_name', '840', '30 second sprints'),

('exercise_name', '860', 'Steady Pace'),
('exercise_name', '870', '4 x 4 intervals'),
('exercise_name', '880', '3 x 3 intervals'),

('exercise_name', '900', 'Steady Pace'),
('exercise_name', '910', '30 second intervals'),
('exercise_name', '920', '3 x 3');
