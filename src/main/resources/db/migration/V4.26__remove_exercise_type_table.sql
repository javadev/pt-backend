DROP TABLE IF EXISTS ptcore.exercise_type_has_exercise;
DROP TABLE IF EXISTS ptcore.exercise_type;

DELETE FROM ptcore.dictionary_data where dkey = 'exercise_type_name';
