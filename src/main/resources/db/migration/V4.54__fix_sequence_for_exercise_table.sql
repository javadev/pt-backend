--
-- SEQUENCES
--

SELECT setval('ptcore.exercise_id_seq', (select max(id) from ptcore.exercise) + 1, true);
