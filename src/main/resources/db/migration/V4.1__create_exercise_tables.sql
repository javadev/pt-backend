CREATE TABLE ptcore.exercise_category (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_exercise_category_name VARCHAR(45)
);

INSERT INTO ptcore.exercise_category (d_exercise_category_name) VALUES
('10'),
('140'),
('250'),
('330'),
('430'),
('510'),
('520'),
('590');

CREATE TABLE ptcore.exercise (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    exercise_category_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_exercise_name VARCHAR(45),
    FOREIGN KEY (exercise_category_id) REFERENCES ptcore.exercise_category (id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO ptcore.exercise (exercise_category_id, d_exercise_name) VALUES
(1, '20'),
(1, '30'),
(1, '40'),
(1, '50'),
(1, '60'),
(1, '70'),
(1, '80'),
(1, '90'),
(1, '100'),
(1, '110'),
(1, '120'),
(1, '130'),

(2, '150'),
(2, '160'),
(2, '170'),
(2, '180'),
(2, '190'),
(2, '200'),
(2, '210'),
(2, '220'),
(2, '230'),
(2, '240'),
	
(3, '260'),
(3, '270'),
(3, '280'),
(3, '290'),
(3, '300'),
(3, '310'),
(3, '320'), 
	
(4, '340'),
(4, '350'),
(4, '360'),
(4, '370'),
(4, '380'),
(4, '390'),
(4, '400'),
(4, '410'),
(4, '420'),
	
(5, '440'),
(5, '450'),
(5, '460'),
(5, '470'),
(5, '480'),
(5, '490'),
(5, '500'),
	
(7, '520'),
(7, '530'),
(7, '540'),
(7, '550'), 
(7, '560'),
(7, '570'),
(7, '580'),
	
(8, '600'),
(8, '610'),
(8, '620'),
(8, '630'),
(8, '640'),
(8, '650');

CREATE TABLE ptcore.dictionary_data (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    dlanguage VARCHAR(30) DEFAULT 'en',
    valid BOOLEAN NOT NULL DEFAULT TRUE,
    fromdate TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    todate TIMESTAMP WITHOUT TIME ZONE,
    dname VARCHAR(30),
    dkey VARCHAR(50),
    dvalue VARCHAR(1000)
);

INSERT INTO ptcore.dictionary_data (dname, dkey, dvalue) VALUES
('exercise_category_name', '10', 'Legs'),
('exercise_category_name', '140', 'Back'),
('exercise_category_name', '250', 'Chest'),
('exercise_category_name', '330', 'Shoulders'),
('exercise_category_name', '430', 'Abs'),
('exercise_category_name', '510', 'Arms'),
('exercise_category_name', '520', 'Tricep'),
('exercise_category_name', '590', 'Bicep');

INSERT INTO ptcore.dictionary_data (dname, dkey, dvalue) VALUES
('exercise_name', '20', 'Squat'),
('exercise_name', '30', 'Front Squat'),
('exercise_name', '40', 'Deadlift'),
('exercise_name', '50', 'Sumo Deadlift'),
('exercise_name', '60', 'Lunge'),
('exercise_name', '70', 'Bulgarian Split-Squat'),
('exercise_name', '80', 'Nordic Hamstrings'),
('exercise_name', '90', 'Hip Thrust'),
('exercise_name', '100', 'One-legged Hip Thrust'),
('exercise_name', '110', 'Hyperextension'),
('exercise_name', '120', 'Legpress'),
('exercise_name', '130', 'Standing Calf Raise '),
('exercise_name', '150', 'Pull-ups'),
('exercise_name', '160', 'Chin-ups'),
('exercise_name', '170', 'Bent Over Row'),
('exercise_name', '180', 'One Arm Dumbbell Row'),

('exercise_name', '190', 'Straight Arm Pulldown'),
('exercise_name', '200', 'Lat Pulldown'),
('exercise_name', '210', 'Deadlift'),
('exercise_name', '220', 'Hyperextension'),
('exercise_name', '230', 'Face Pull'),
('exercise_name', '240', 'TRX/Redcord Face Pull'),
	
('exercise_name', '250', 'Chest'),
('exercise_name', '260', 'Bench Press'),
('exercise_name', '270', 'Incline Bench Press'),
('exercise_name', '280', 'Dumbbell Chest Press'),
('exercise_name', '290', 'Incline Dumbbell Chest Press'),
('exercise_name', '300', 'Push-Ups'),
('exercise_name', '310', 'TRX/Redcord Push-Ups'),
('exercise_name', '320', 'Chest Press Machine'), 
	
('exercise_name', '330', 'Shoulders'),
('exercise_name', '340', 'Barbell Shoulder Press'),
('exercise_name', '350', 'Dumbbell Shoulder Press'),
('exercise_name', '360', 'Dumbbell Front Raise'),
('exercise_name', '370', 'Cable Front Raise'),
('exercise_name', '380', 'Dumbbell Side Raise'),
('exercise_name', '390', 'Cable Side Raise'),
('exercise_name', '400', 'Reverse Fly'),
('exercise_name', '410', 'Face Pull'),
('exercise_name', '420', 'TRX/Redcord Face Pull'),
	
('exercise_name', '430', 'Abs'),
('exercise_name', '440', 'Plank'),
('exercise_name', '450', 'Plank On a Ball'),
('exercise_name', '460', 'The Boat'),
('exercise_name', '470', 'Russian Twists'),
('exercise_name', '480', 'Leg/Knee Raise'),
('exercise_name', '490', 'Hanginf Leg/Knee Raise'),
('exercise_name', '500', 'Redcord Knee Raise'),
	
('exercise_name', '510', 'Arms'),
	
('exercise_name', '520', 'Tricep'),
('exercise_name', '530', 'Dips'),
('exercise_name', '540', 'Close Grip Bench Press'),
('exercise_name', '550', 'Scull Crusher'), 
('exercise_name', '560', 'Hindu Push-Ups'),
('exercise_name', '570', 'Close Grip Push-Ups'),
('exercise_name', '580', 'Cable Tricep Extension '),
	
('exercise_name', '590', 'Bicep'),
('exercise_name', '600', 'Barbell Bicep Curl'),
('exercise_name', '610', 'Dumbbell Bicep Curl'),
('exercise_name', '620', 'Dumbbell Hammer Curl'),
('exercise_name', '630', 'TRX/Redcord Bicep Curl'),
('exercise_name', '640', 'Cable Bicep Curl'),
('exercise_name', '650', 'Chin Ups');
