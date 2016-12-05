TRUNCATE TABLE ptcore.exercise_bodypart CASCADE;
TRUNCATE TABLE ptcore.exercise_equipment_type CASCADE;
TRUNCATE TABLE ptcore.exercise CASCADE;

COPY ptcore.exercise_bodypart (id, created, d_exercise_bodypart_name) FROM stdin;
1	2016-11-09 10:33:29.835394	10
2	2016-11-09 10:33:29.835394	140
3	2016-11-09 10:33:29.835394	250
4	2016-11-09 10:33:29.835394	330
5	2016-11-09 10:33:29.835394	430
6	2016-11-09 10:33:29.835394	510
7	2016-11-09 10:33:29.835394	520
8	2016-11-09 10:33:29.835394	590
\.

COPY ptcore.exercise_equipment_type (id, created, d_exercise_equipment_type_name) FROM stdin;
1	2016-11-29 11:40:42.840928	10
2	2016-11-29 11:40:42.840928	20
3	2016-11-29 11:40:42.840928	30
\.

COPY ptcore.exercise (id, exercise_bodypart_id, created, d_exercise_name, exercise_id, d_exercise_description, exercise_equipment_type_id, cardio_percent) FROM stdin;
4	1	2016-11-09 10:33:29.835394	50	50	150	\N	13
5	1	2016-11-09 10:33:29.835394	60	60	160	\N	14
73	\N	2016-11-16 10:39:14.357514	920	920	40	3	0
72	\N	2016-11-16 10:39:14.357514	910	910	130	3	0
7	1	2016-11-09 10:33:29.835394	80	80	180	\N	0
8	1	2016-11-09 10:33:29.835394	90	90	190	\N	0
71	\N	2016-11-16 10:39:14.357514	900	900	120	3	0
70	\N	2016-11-16 10:39:14.357514	880	880	110	2	0
69	\N	2016-11-16 10:39:14.357514	870	870	100	2	0
68	\N	2016-11-16 10:39:14.357514	860	860	90	2	0
67	\N	2016-11-16 10:39:14.357514	840	840	80	1	0
58	8	2016-11-09 10:33:29.835394	650	650	200	\N	0
9	1	2016-11-09 10:33:29.835394	100	100	210	\N	0
10	1	2016-11-09 10:33:29.835394	110	110	220	\N	0
11	1	2016-11-09 10:33:29.835394	120	120	230	\N	0
12	1	2016-11-09 10:33:29.835394	130	130	240	\N	0
66	\N	2016-11-16 10:39:14.357514	830	830	70	1	0
65	\N	2016-11-16 10:39:14.357514	820	820	60	1	0
64	\N	2016-11-16 10:39:14.357514	810	810	50	1	0
13	2	2016-11-09 10:33:29.835394	150	150	250	\N	0
14	2	2016-11-09 10:33:29.835394	160	160	260	\N	0
15	2	2016-11-09 10:33:29.835394	170	170	270	\N	0
34	4	2016-11-09 10:33:29.835394	380	380	280	\N	0
28	3	2016-11-09 10:33:29.835394	310	310	290	\N	0
43	5	2016-11-09 10:33:29.835394	480	480	300	\N	0
49	7	2016-11-09 10:33:29.835394	550	550	310	\N	0
57	8	2016-11-09 10:33:29.835394	640	640	320	\N	0
53	8	2016-11-09 10:33:29.835394	600	600	330	\N	0
20	2	2016-11-09 10:33:29.835394	220	220	340	\N	0
17	2	2016-11-09 10:33:29.835394	190	190	350	\N	0
18	2	2016-11-09 10:33:29.835394	200	200	360	\N	0
19	2	2016-11-09 10:33:29.835394	210	210	370	\N	0
16	2	2016-11-09 10:33:29.835394	180	180	380	\N	0
37	4	2016-11-09 10:33:29.835394	410	410	390	\N	0
39	5	2016-11-09 10:33:29.835394	440	440	400	\N	0
23	3	2016-11-09 10:33:29.835394	260	260	410	\N	0
21	2	2016-11-09 10:33:29.835394	230	230	420	\N	0
22	2	2016-11-09 10:33:29.835394	240	240	430	\N	0
56	8	2016-11-09 10:33:29.835394	630	630	440	\N	0
30	4	2016-11-09 10:33:29.835394	340	340	450	\N	0
24	3	2016-11-09 10:33:29.835394	270	270	460	\N	0
25	3	2016-11-09 10:33:29.835394	280	280	470	\N	0
26	3	2016-11-09 10:33:29.835394	290	290	480	\N	0
27	3	2016-11-09 10:33:29.835394	300	300	490	\N	0
29	3	2016-11-09 10:33:29.835394	320	320	500	\N	0
31	4	2016-11-09 10:33:29.835394	350	350	510	\N	0
32	4	2016-11-09 10:33:29.835394	360	360	520	\N	0
55	8	2016-11-09 10:33:29.835394	620	620	530	\N	0
54	8	2016-11-09 10:33:29.835394	610	610	540	\N	0
52	7	2016-11-09 10:33:29.835394	580	580	550	\N	0
51	7	2016-11-09 10:33:29.835394	570	570	560	\N	0
50	7	2016-11-09 10:33:29.835394	560	560	570	\N	0
33	4	2016-11-09 10:33:29.835394	370	370	580	\N	0
35	4	2016-11-09 10:33:29.835394	390	390	590	\N	0
36	4	2016-11-09 10:33:29.835394	400	400	600	\N	0
38	4	2016-11-09 10:33:29.835394	420	420	610	\N	0
40	5	2016-11-09 10:33:29.835394	450	450	620	\N	0
48	7	2016-11-09 10:33:29.835394	540	540	630	\N	0
41	5	2016-11-09 10:33:29.835394	460	460	640	\N	0
47	7	2016-11-09 10:33:29.835394	530	530	650	\N	0
44	5	2016-11-09 10:33:29.835394	490	490	660	\N	0
45	5	2016-11-09 10:33:29.835394	500	500	670	\N	0
42	5	2016-11-09 10:33:29.835394	470	470	680	\N	0
6	1	2016-11-09 10:33:29.835394	70	70	170	\N	15
1	1	2016-11-09 10:33:29.835394	20	20	10	\N	10
2	1	2016-11-09 10:33:29.835394	30	30	20	\N	11
3	1	2016-11-09 10:33:29.835394	40	40	140	\N	12
\.

TRUNCATE TABLE ptcore.exercise_type_has_exercise;

COPY ptcore.exercise_type_has_exercise (exercise_type_id, exercise_id) FROM stdin;
2	26
2	29
3	29
2	32
2	33
2	34
2	35
2	37
1	39
1	40
2	40
2	15
1	41
1	42
2	42
2	43
2	44
2	45
2	47
2	48
3	48
2	49
2	50
2	51
2	22
2	52
2	53
1	19
2	19
3	19
1	27
2	27
1	28
2	28
1	23
2	23
3	23
1	25
2	25
2	30
3	30
2	31
2	36
2	38
2	54
2	55
2	56
2	57
2	58
1	64
1	65
1	66
1	67
1	68
1	69
1	70
1	71
1	72
1	73
1	1
2	1
3	1
1	2
2	2
3	2
1	3
2	3
3	3
1	4
2	4
3	4
1	5
2	5
1	11
2	11
3	11
2	6
2	7
2	8
2	9
2	10
2	12
2	13
2	14
2	16
2	17
2	18
2	20
2	21
2	24
3	24
\.

TRUNCATE TABLE ptcore.exercise_input_has_exercise;

COPY ptcore.exercise_input_has_exercise (exercise_input_id, exercise_id) FROM stdin;
1	6
2	6
3	6
2	7
3	7
1	8
2	8
3	8
1	9
2	9
3	9
1	10
2	10
3	10
1	12
2	12
3	12
1	13
2	13
3	13
1	14
2	14
3	14
1	16
2	16
3	16
1	17
2	17
3	17
1	18
2	18
3	18
1	20
2	20
3	20
1	21
2	21
3	21
1	24
2	24
3	24
1	26
2	26
3	26
1	29
2	29
3	29
1	32
2	32
3	32
1	33
2	33
3	33
1	34
2	34
3	34
1	35
2	35
3	35
1	37
2	37
3	37
3	39
4	39
2	40
3	40
4	40
3	41
4	41
2	42
3	42
4	42
2	43
3	43
2	44
3	44
2	45
3	45
1	47
2	47
3	47
1	48
2	48
3	48
1	49
2	49
3	49
2	50
3	50
1	51
2	51
3	51
1	52
2	52
3	52
1	53
2	53
3	53
1	54
2	54
3	54
1	55
2	55
3	55
2	56
3	56
1	57
2	57
3	57
1	58
2	58
3	58
4	64
5	64
6	64
4	65
5	65
6	65
4	66
5	66
6	66
4	67
5	67
6	67
4	68
5	68
7	68
4	69
5	69
7	69
4	70
5	70
7	70
4	71
5	71
7	71
4	72
5	72
7	72
4	73
5	73
7	73
1	1
2	1
3	1
4	1
1	2
2	2
3	2
4	2
1	3
2	3
3	3
4	3
1	4
2	4
3	4
4	4
1	5
2	5
3	5
4	5
1	11
2	11
3	11
4	11
1	15
2	15
3	15
2	22
3	22
1	19
2	19
3	19
4	19
1	27
2	27
3	27
4	27
2	28
3	28
4	28
1	23
2	23
3	23
4	23
1	25
2	25
3	25
4	25
1	30
2	30
3	30
4	30
1	31
2	31
3	31
4	31
1	36
2	36
3	36
4	36
2	38
3	38
4	38
\.

TRUNCATE TABLE ptcore.exercise_output_has_exercise;

COPY ptcore.exercise_output_has_exercise (exercise_output_id, exercise_id) FROM stdin;
1	6
2	6
2	7
1	8
2	8
1	9
2	9
1	10
2	10
1	12
2	12
1	13
2	13
1	14
2	14
1	16
2	16
1	17
2	17
1	18
2	18
1	20
2	20
1	21
2	21
1	24
2	24
1	26
2	26
1	29
2	29
1	32
2	32
1	33
2	33
1	34
2	34
1	35
2	35
1	37
2	37
4	39
2	40
4	40
4	41
2	42
4	42
2	43
2	44
2	45
1	47
2	47
1	48
2	48
1	49
2	49
2	50
1	51
2	51
1	52
2	52
1	53
2	53
1	54
2	54
1	55
2	55
2	56
1	57
2	57
1	58
2	58
5	64
6	64
5	65
6	65
5	66
6	66
5	67
6	67
5	68
7	68
5	69
7	69
5	70
7	70
5	71
7	71
5	72
7	72
5	73
7	73
1	1
2	1
4	1
1	2
2	2
4	2
1	3
2	3
4	3
1	4
2	4
4	4
1	5
2	5
4	5
1	11
2	11
4	11
1	15
2	15
4	15
2	22
4	22
1	19
2	19
4	19
2	27
4	27
2	28
4	28
1	23
2	23
4	23
1	25
2	25
4	25
1	30
2	30
4	30
1	31
2	31
4	31
1	36
2	36
4	36
2	38
4	38
\.