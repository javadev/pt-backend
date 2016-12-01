DELETE FROM ptcore.exercise_bodypart WHERE d_exercise_bodypart_name in ('800', '850', '890');
DELETE FROM ptcore.dictionary_data WHERE dname = 'exercise_bodypart_name' and dkey in ('800', '850', '890');
