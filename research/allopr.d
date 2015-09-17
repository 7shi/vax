00          	halt
01          	nop
02          	rei
03          	bpt
04          	ret
05          	rsb
06          	ldpctx
07          	svpctx
08 8f 50 50 	cvtps $0x5050,r0,r0,r0
08 50 8f 50 	cvtps r0,$0x50,r0,r0
08 50 50 8f 	cvtps r0,r0,$0x5050,r0
08 50 50 50 	cvtps r0,r0,r0,$0x50
09 8f 50 50 	cvtsp $0x5050,r0,r0,r0
09 50 8f 50 	cvtsp r0,$0x50,r0,r0
09 50 50 8f 	cvtsp r0,r0,$0x5050,r0
09 50 50 50 	cvtsp r0,r0,r0,$0x50
0a 8f 50 50 	index $0x50505050,r0,r0,r0,r0,r0
0a 50 8f 50 	index r0,$0x50505050,r0,r0,r0,r0
0a 50 50 8f 	index r0,r0,$0x50505050,r0,r0,r0
0a 50 50 50 	index r0,r0,r0,$0x50505050,r0,r0
0a 50 50 50 	index r0,r0,r0,r0,$0x50505050,r0
0a 50 50 50 	index r0,r0,r0,r0,r0,$0x50505050
0b 8f 50 50 	crc $0x50,r0,r0,r0
0b 50 8f 50 	crc r0,$0x50505050,r0,r0
0b 50 50 8f 	crc r0,r0,$0x5050,r0
0b 50 50 50 	crc r0,r0,r0,$0x50
0c 8f 50 50 	prober $0x50,r0,r0
0c 50 8f 50 	prober r0,$0x5050,r0
0c 50 50 8f 	prober r0,r0,$0x50
0d 8f 50 50 	probew $0x50,r0,r0
0d 50 8f 50 	probew r0,$0x5050,r0
0d 50 50 8f 	probew r0,r0,$0x50
0e 8f 50 50 	insque $0x50,r0
0e 50 8f 50 	insque r0,$0x50
0f 8f 50 50 	remque $0x50,r0
0f 50 8f 50 	remque r0,$0x50505050
10 50       	bsbb 0x52
11 50       	brb 0x52
12 50       	bneq 0x52
13 50       	beql 0x52
14 50       	bgtr 0x52
15 50       	bleq 0x52
16 8f 50    	jsb $0x50
17 8f 50    	jmp $0x50
18 50       	bgeq 0x52
19 50       	blss 0x52
1a 50       	bgtru 0x52
1b 50       	blequ 0x52
1c 50       	bvc 0x52
1d 50       	bvs 0x52
1e 50       	bcc 0x52
1f 50       	blssu 0x52
20 8f 50 50 	addp4 $0x5050,r0,r0,r0
20 50 8f 50 	addp4 r0,$0x50,r0,r0
20 50 50 8f 	addp4 r0,r0,$0x5050,r0
20 50 50 50 	addp4 r0,r0,r0,$0x50
21 8f 50 50 	addp6 $0x5050,r0,r0,r0,r0,r0
21 50 8f 50 	addp6 r0,$0x50,r0,r0,r0,r0
21 50 50 8f 	addp6 r0,r0,$0x5050,r0,r0,r0
21 50 50 50 	addp6 r0,r0,r0,$0x50,r0,r0
21 50 50 50 	addp6 r0,r0,r0,r0,$0x5050,r0
21 50 50 50 	addp6 r0,r0,r0,r0,r0,$0x50
22 8f 50 50 	subp4 $0x5050,r0,r0,r0
22 50 8f 50 	subp4 r0,$0x50,r0,r0
22 50 50 8f 	subp4 r0,r0,$0x5050,r0
22 50 50 50 	subp4 r0,r0,r0,$0x50
23 8f 50 50 	subp6 $0x5050,r0,r0,r0,r0,r0
23 50 8f 50 	subp6 r0,$0x50,r0,r0,r0,r0
23 50 50 8f 	subp6 r0,r0,$0x5050,r0,r0,r0
23 50 50 50 	subp6 r0,r0,r0,$0x50,r0,r0
23 50 50 50 	subp6 r0,r0,r0,r0,$0x5050,r0
23 50 50 50 	subp6 r0,r0,r0,r0,r0,$0x50
24 8f 50 50 	cvtpt $0x5050,r0,r0,r0,r0
24 50 8f 50 	cvtpt r0,$0x50,r0,r0,r0
24 50 50 8f 	cvtpt r0,r0,$0x50,r0,r0
24 50 50 50 	cvtpt r0,r0,r0,$0x5050,r0
24 50 50 50 	cvtpt r0,r0,r0,r0,$0x50
25 8f 50 50 	mulp $0x5050,r0,r0,r0,r0,r0
25 50 8f 50 	mulp r0,$0x50,r0,r0,r0,r0
25 50 50 8f 	mulp r0,r0,$0x5050,r0,r0,r0
25 50 50 50 	mulp r0,r0,r0,$0x50,r0,r0
25 50 50 50 	mulp r0,r0,r0,r0,$0x5050,r0
25 50 50 50 	mulp r0,r0,r0,r0,r0,$0x50
26 8f 50 50 	cvttp $0x5050,r0,r0,r0,r0
26 50 8f 50 	cvttp r0,$0x50,r0,r0,r0
26 50 50 8f 	cvttp r0,r0,$0x50,r0,r0
26 50 50 50 	cvttp r0,r0,r0,$0x5050,r0
26 50 50 50 	cvttp r0,r0,r0,r0,$0x50
27 8f 50 50 	divp $0x5050,r0,r0,r0,r0,r0
27 50 8f 50 	divp r0,$0x50,r0,r0,r0,r0
27 50 50 8f 	divp r0,r0,$0x5050,r0,r0,r0
27 50 50 50 	divp r0,r0,r0,$0x50,r0,r0
27 50 50 50 	divp r0,r0,r0,r0,$0x5050,r0
27 50 50 50 	divp r0,r0,r0,r0,r0,$0x50
28 8f 50 50 	movc3 $0x5050,r0,r0
28 50 8f 50 	movc3 r0,$0x50,r0
28 50 50 8f 	movc3 r0,r0,$0x50
29 8f 50 50 	cmpc3 $0x5050,r0,r0
29 50 8f 50 	cmpc3 r0,$0x50,r0
29 50 50 8f 	cmpc3 r0,r0,$0x50
2a 8f 50 50 	scanc $0x5050,r0,r0,r0
2a 50 8f 50 	scanc r0,$0x50,r0,r0
2a 50 50 8f 	scanc r0,r0,$0x50,r0
2a 50 50 50 	scanc r0,r0,r0,$0x50
2b 8f 50 50 	spanc $0x5050,r0,r0,r0
2b 50 8f 50 	spanc r0,$0x50,r0,r0
2b 50 50 8f 	spanc r0,r0,$0x50,r0
2b 50 50 50 	spanc r0,r0,r0,$0x50
2c 8f 50 50 	movc5 $0x5050,r0,r0,r0,r0
2c 50 8f 50 	movc5 r0,$0x50,r0,r0,r0
2c 50 50 8f 	movc5 r0,r0,$0x50,r0,r0
2c 50 50 50 	movc5 r0,r0,r0,$0x5050,r0
2c 50 50 50 	movc5 r0,r0,r0,r0,$0x50
2d 8f 50 50 	cmpc5 $0x5050,r0,r0,r0,r0
2d 50 8f 50 	cmpc5 r0,$0x50,r0,r0,r0
2d 50 50 8f 	cmpc5 r0,r0,$0x50,r0,r0
2d 50 50 50 	cmpc5 r0,r0,r0,$0x5050,r0
2d 50 50 50 	cmpc5 r0,r0,r0,r0,$0x50
2e 8f 50 50 	movtc $0x5050,r0,r0,r0,r0,r0
2e 50 8f 50 	movtc r0,$0x50,r0,r0,r0,r0
2e 50 50 8f 	movtc r0,r0,$0x50,r0,r0,r0
2e 50 50 50 	movtc r0,r0,r0,$0x50,r0,r0
2e 50 50 50 	movtc r0,r0,r0,r0,$0x5050,r0
2e 50 50 50 	movtc r0,r0,r0,r0,r0,$0x50
2f 8f 50 50 	movtuc $0x5050,r0,r0,r0,r0,r0
2f 50 8f 50 	movtuc r0,$0x50,r0,r0,r0,r0
2f 50 50 8f 	movtuc r0,r0,$0x50,r0,r0,r0
2f 50 50 50 	movtuc r0,r0,r0,$0x50,r0,r0
2f 50 50 50 	movtuc r0,r0,r0,r0,$0x5050,r0
2f 50 50 50 	movtuc r0,r0,r0,r0,r0,$0x50
30 50 50    	bsbw 0x5053
31 50 50    	brw 0x5053
32 8f 50 50 	cvtwl $0x5050,r0
32 50 8f 50 	cvtwl r0,$0x50505050
33 8f 50 50 	cvtwb $0x5050,r0
33 50 8f 50 	cvtwb r0,$0x50
34 8f 50 50 	movp $0x5050,r0,r0
34 50 8f 50 	movp r0,$0x50,r0
34 50 50 8f 	movp r0,r0,$0x50
35 8f 50 50 	cmpp3 $0x5050,r0,r0
35 50 8f 50 	cmpp3 r0,$0x50,r0
35 50 50 8f 	cmpp3 r0,r0,$0x50
36 8f 50 50 	cvtpl $0x5050,r0,r0
36 50 8f 50 	cvtpl r0,$0x50,r0
36 50 50 8f 	cvtpl r0,r0,$0x50505050
37 8f 50 50 	cmpp4 $0x5050,r0,r0,r0
37 50 8f 50 	cmpp4 r0,$0x50,r0,r0
37 50 50 8f 	cmpp4 r0,r0,$0x5050,r0
37 50 50 50 	cmpp4 r0,r0,r0,$0x50
38 8f 50 50 	editpc $0x5050,r0,r0,r0
38 50 8f 50 	editpc r0,$0x50,r0,r0
38 50 50 8f 	editpc r0,r0,$0x50,r0
38 50 50 50 	editpc r0,r0,r0,$0x50
39 8f 50 50 	matchc $0x5050,r0,r0,r0
39 50 8f 50 	matchc r0,$0x50,r0,r0
39 50 50 8f 	matchc r0,r0,$0x5050,r0
39 50 50 50 	matchc r0,r0,r0,$0x50
3a 8f 50 50 	locc $0x50,r0,r0
3a 50 8f 50 	locc r0,$0x5050,r0
3a 50 50 8f 	locc r0,r0,$0x50
3b 8f 50 50 	skpc $0x50,r0,r0
3b 50 8f 50 	skpc r0,$0x5050,r0
3b 50 50 8f 	skpc r0,r0,$0x50
3c 8f 50 50 	movzwl $0x5050,r0
3c 50 8f 50 	movzwl r0,$0x50505050
3d 8f 50 50 	acbw $0x5050,r0,r0,0x5058
3d 50 8f 50 	acbw r0,$0x5050,r0,0x5058
3d 50 50 8f 	acbw r0,r0,$0x5050,0x5058
3e 8f 50 50 	movaw $0x5050,r0
3e 50 8f 50 	movaw r0,$0x50505050
3f 8f 50 50 	pushaw $0x5050
40 8f 50 50 	addf2 $0x50505050 [f-float],r0
40 50 8f 50 	addf2 r0,$0x50505050 [f-float]
41 8f 50 50 	addf3 $0x50505050 [f-float],r0,r0
41 50 8f 50 	addf3 r0,$0x50505050 [f-float],r0
41 50 50 8f 	addf3 r0,r0,$0x50505050 [f-float]
42 8f 50 50 	subf2 $0x50505050 [f-float],r0
42 50 8f 50 	subf2 r0,$0x50505050 [f-float]
43 8f 50 50 	subf3 $0x50505050 [f-float],r0,r0
43 50 8f 50 	subf3 r0,$0x50505050 [f-float],r0
43 50 50 8f 	subf3 r0,r0,$0x50505050 [f-float]
44 8f 50 50 	mulf2 $0x50505050 [f-float],r0
44 50 8f 50 	mulf2 r0,$0x50505050 [f-float]
45 8f 50 50 	mulf3 $0x50505050 [f-float],r0,r0
45 50 8f 50 	mulf3 r0,$0x50505050 [f-float],r0
45 50 50 8f 	mulf3 r0,r0,$0x50505050 [f-float]
46 8f 50 50 	divf2 $0x50505050 [f-float],r0
46 50 8f 50 	divf2 r0,$0x50505050 [f-float]
47 8f 50 50 	divf3 $0x50505050 [f-float],r0,r0
47 50 8f 50 	divf3 r0,$0x50505050 [f-float],r0
47 50 50 8f 	divf3 r0,r0,$0x50505050 [f-float]
48 8f 50 50 	cvtfb $0x50505050 [f-float],r0
48 50 8f 50 	cvtfb r0,$0x50
49 8f 50 50 	cvtfw $0x50505050 [f-float],r0
49 50 8f 50 	cvtfw r0,$0x5050
4a 8f 50 50 	cvtfl $0x50505050 [f-float],r0
4a 50 8f 50 	cvtfl r0,$0x50505050
4b 8f 50 50 	cvtrfl $0x50505050 [f-float],r0
4b 50 8f 50 	cvtrfl r0,$0x50505050
4c 8f 50 50 	cvtbf $0x50,r0
4c 50 8f 50 	cvtbf r0,$0x50505050 [f-float]
4d 8f 50 50 	cvtwf $0x5050,r0
4d 50 8f 50 	cvtwf r0,$0x50505050 [f-float]
4e 8f 50 50 	cvtlf $0x50505050,r0
4e 50 8f 50 	cvtlf r0,$0x50505050 [f-float]
4f 8f 50 50 	acbf $0x50505050 [f-float],r0,r0,0x505a
4f 50 8f 50 	acbf r0,$0x50505050 [f-float],r0,0x505a
4f 50 50 8f 	acbf r0,r0,$0x50505050 [f-float],0x505a
50 8f 50 50 	movf $0x50505050 [f-float],r0
50 50 8f 50 	movf r0,$0x50505050 [f-float]
51 8f 50 50 	cmpf $0x50505050 [f-float],r0
51 50 8f 50 	cmpf r0,$0x50505050 [f-float]
52 8f 50 50 	mnegf $0x50505050 [f-float],r0
52 50 8f 50 	mnegf r0,$0x50505050 [f-float]
53 8f 50 50 	tstf $0x50505050 [f-float]
54 8f 50 50 	emodf $0x50505050 [f-float],r0,r0,r0,r0
54 50 8f 50 	emodf r0,$0x50,r0,r0,r0
54 50 50 8f 	emodf r0,r0,$0x50505050 [f-float],r0,r0
54 50 50 50 	emodf r0,r0,r0,$0x50505050,r0
54 50 50 50 	emodf r0,r0,r0,r0,$0x50505050 [f-float]
55 8f 50 50 	polyf $0x50505050 [f-float],r0,r0
55 50 8f 50 	polyf r0,$0x5050,r0
55 50 50 8f 	polyf r0,r0,$0x50
56 8f 50 50 	cvtfd $0x50505050 [f-float],r0
56 50 8f 50 	cvtfd r0,$0x5050505050505050 [d-float]
58 8f 50 50 	adawi $0x5050,r0
58 50 8f 50 	adawi r0,$0x5050
5c 8f 50 50 	insqhi $0x50,r0
5c 50 8f 50 	insqhi r0,$0x5050505050505050
5d 8f 50 50 	insqti $0x50,r0
5d 50 8f 50 	insqti r0,$0x5050505050505050
5e 8f 50 50 	remqhi $0x5050505050505050,r0
5e 50 8f 50 	remqhi r0,$0x50505050
5f 8f 50 50 	remqti $0x5050505050505050,r0
5f 50 8f 50 	remqti r0,$0x50505050
60 8f 50 50 	addd2 $0x5050505050505050 [d-float],r0
60 50 8f 50 	addd2 r0,$0x5050505050505050 [d-float]
61 8f 50 50 	addd3 $0x5050505050505050 [d-float],r0,r0
61 50 8f 50 	addd3 r0,$0x5050505050505050 [d-float],r0
61 50 50 8f 	addd3 r0,r0,$0x5050505050505050 [d-float]
62 8f 50 50 	subd2 $0x5050505050505050 [d-float],r0
62 50 8f 50 	subd2 r0,$0x5050505050505050 [d-float]
63 8f 50 50 	subd3 $0x5050505050505050 [d-float],r0,r0
63 50 8f 50 	subd3 r0,$0x5050505050505050 [d-float],r0
63 50 50 8f 	subd3 r0,r0,$0x5050505050505050 [d-float]
64 8f 50 50 	muld2 $0x5050505050505050 [d-float],r0
64 50 8f 50 	muld2 r0,$0x5050505050505050 [d-float]
65 8f 50 50 	muld3 $0x5050505050505050 [d-float],r0,r0
65 50 8f 50 	muld3 r0,$0x5050505050505050 [d-float],r0
65 50 50 8f 	muld3 r0,r0,$0x5050505050505050 [d-float]
66 8f 50 50 	divd2 $0x5050505050505050 [d-float],r0
66 50 8f 50 	divd2 r0,$0x5050505050505050 [d-float]
67 8f 50 50 	divd3 $0x5050505050505050 [d-float],r0,r0
67 50 8f 50 	divd3 r0,$0x5050505050505050 [d-float],r0
67 50 50 8f 	divd3 r0,r0,$0x5050505050505050 [d-float]
68 8f 50 50 	cvtdb $0x5050505050505050 [d-float],r0
68 50 8f 50 	cvtdb r0,$0x50
69 8f 50 50 	cvtdw $0x5050505050505050 [d-float],r0
69 50 8f 50 	cvtdw r0,$0x5050
6a 8f 50 50 	cvtdl $0x5050505050505050 [d-float],r0
6a 50 8f 50 	cvtdl r0,$0x50505050
6b 8f 50 50 	cvtrdl $0x5050505050505050 [d-float],r0
6b 50 8f 50 	cvtrdl r0,$0x50505050
6c 8f 50 50 	cvtbd $0x50,r0
6c 50 8f 50 	cvtbd r0,$0x5050505050505050 [d-float]
6d 8f 50 50 	cvtwd $0x5050,r0
6d 50 8f 50 	cvtwd r0,$0x5050505050505050 [d-float]
6e 8f 50 50 	cvtld $0x50505050,r0
6e 50 8f 50 	cvtld r0,$0x5050505050505050 [d-float]
6f 8f 50 50 	acbd $0x5050505050505050 [d-float],r0,r0,0x505e
6f 50 8f 50 	acbd r0,$0x5050505050505050 [d-float],r0,0x505e
6f 50 50 8f 	acbd r0,r0,$0x5050505050505050 [d-float],0x505e
70 8f 50 50 	movd $0x5050505050505050 [d-float],r0
70 50 8f 50 	movd r0,$0x5050505050505050 [d-float]
71 8f 50 50 	cmpd $0x5050505050505050 [d-float],r0
71 50 8f 50 	cmpd r0,$0x5050505050505050 [d-float]
72 8f 50 50 	mnegd $0x5050505050505050 [d-float],r0
72 50 8f 50 	mnegd r0,$0x5050505050505050 [d-float]
73 8f 50 50 	tstd $0x5050505050505050 [d-float]
74 8f 50 50 	emodd $0x5050505050505050 [d-float],r0,r0,r0,r0
74 50 8f 50 	emodd r0,$0x50,r0,r0,r0
74 50 50 8f 	emodd r0,r0,$0x5050505050505050 [d-float],r0,r0
74 50 50 50 	emodd r0,r0,r0,$0x50505050,r0
74 50 50 50 	emodd r0,r0,r0,r0,$0x5050505050505050 [d-float]
75 8f 50 50 	polyd $0x5050505050505050 [d-float],r0,r0
75 50 8f 50 	polyd r0,$0x5050,r0
75 50 50 8f 	polyd r0,r0,$0x50
76 8f 50 50 	cvtdf $0x5050505050505050 [d-float],r0
76 50 8f 50 	cvtdf r0,$0x50505050 [f-float]
78 8f 50 50 	ashl $0x50,r0,r0
78 50 8f 50 	ashl r0,$0x50505050,r0
78 50 50 8f 	ashl r0,r0,$0x50505050
79 8f 50 50 	ashq $0x50,r0,r0
79 50 8f 50 	ashq r0,$0x5050505050505050,r0
79 50 50 8f 	ashq r0,r0,$0x5050505050505050
7a 8f 50 50 	emul $0x50505050,r0,r0,r0
7a 50 8f 50 	emul r0,$0x50505050,r0,r0
7a 50 50 8f 	emul r0,r0,$0x50505050,r0
7a 50 50 50 	emul r0,r0,r0,$0x5050505050505050
7b 8f 50 50 	ediv $0x50505050,r0,r0,r0
7b 50 8f 50 	ediv r0,$0x5050505050505050,r0,r0
7b 50 50 8f 	ediv r0,r0,$0x50505050,r0
7b 50 50 50 	ediv r0,r0,r0,$0x50505050
7c 8f 50 50 	clrd $0x5050505050505050 [d-float]
7d 8f 50 50 	movq $0x5050505050505050,r0
7d 50 8f 50 	movq r0,$0x5050505050505050
7e 8f 50 50 	movaq $0x5050505050505050,r0
7e 50 8f 50 	movaq r0,$0x50505050
7f 8f 50 50 	pushaq $0x5050505050505050
80 8f 50 50 	addb2 $0x50,r0
80 50 8f 50 	addb2 r0,$0x50
81 8f 50 50 	addb3 $0x50,r0,r0
81 50 8f 50 	addb3 r0,$0x50,r0
81 50 50 8f 	addb3 r0,r0,$0x50
82 8f 50 50 	subb2 $0x50,r0
82 50 8f 50 	subb2 r0,$0x50
83 8f 50 50 	subb3 $0x50,r0,r0
83 50 8f 50 	subb3 r0,$0x50,r0
83 50 50 8f 	subb3 r0,r0,$0x50
84 8f 50 50 	mulb2 $0x50,r0
84 50 8f 50 	mulb2 r0,$0x50
85 8f 50 50 	mulb3 $0x50,r0,r0
85 50 8f 50 	mulb3 r0,$0x50,r0
85 50 50 8f 	mulb3 r0,r0,$0x50
86 8f 50 50 	divb2 $0x50,r0
86 50 8f 50 	divb2 r0,$0x50
87 8f 50 50 	divb3 $0x50,r0,r0
87 50 8f 50 	divb3 r0,$0x50,r0
87 50 50 8f 	divb3 r0,r0,$0x50
88 8f 50 50 	bisb2 $0x50,r0
88 50 8f 50 	bisb2 r0,$0x50
89 8f 50 50 	bisb3 $0x50,r0,r0
89 50 8f 50 	bisb3 r0,$0x50,r0
89 50 50 8f 	bisb3 r0,r0,$0x50
8a 8f 50 50 	bicb2 $0x50,r0
8a 50 8f 50 	bicb2 r0,$0x50
8b 8f 50 50 	bicb3 $0x50,r0,r0
8b 50 8f 50 	bicb3 r0,$0x50,r0
8b 50 50 8f 	bicb3 r0,r0,$0x50
8c 8f 50 50 	xorb2 $0x50,r0
8c 50 8f 50 	xorb2 r0,$0x50
8d 8f 50 50 	xorb3 $0x50,r0,r0
8d 50 8f 50 	xorb3 r0,$0x50,r0
8d 50 50 8f 	xorb3 r0,r0,$0x50
8e 8f 50 50 	mnegb $0x50,r0
8e 50 8f 50 	mnegb r0,$0x50
8f 8f 50 50 	caseb $0x50,r0,r0
8f 50 8f 50 	caseb r0,$0x50,r0
8f 50 50 8f 	caseb r0,r0,$0x50
90 8f 50 50 	movb $0x50,r0
90 50 8f 50 	movb r0,$0x50
91 8f 50 50 	cmpb $0x50,r0
91 50 8f 50 	cmpb r0,$0x50
92 8f 50 50 	mcomb $0x50,r0
92 50 8f 50 	mcomb r0,$0x50
93 8f 50 50 	bitb $0x50,r0
93 50 8f 50 	bitb r0,$0x50
94 8f 50    	clrb $0x50
95 8f 50    	tstb $0x50
96 8f 50    	incb $0x50
97 8f 50    	decb $0x50
98 8f 50 50 	cvtbl $0x50,r0
98 50 8f 50 	cvtbl r0,$0x50505050
99 8f 50 50 	cvtbw $0x50,r0
99 50 8f 50 	cvtbw r0,$0x5050
9a 8f 50 50 	movzbl $0x50,r0
9a 50 8f 50 	movzbl r0,$0x50505050
9b 8f 50 50 	movzbw $0x50,r0
9b 50 8f 50 	movzbw r0,$0x5050
9c 8f 50 50 	rotl $0x50,r0,r0
9c 50 8f 50 	rotl r0,$0x50505050,r0
9c 50 50 8f 	rotl r0,r0,$0x50505050
9d 8f 50 50 	acbb $0x50,r0,r0,0x5057
9d 50 8f 50 	acbb r0,$0x50,r0,0x5057
9d 50 50 8f 	acbb r0,r0,$0x50,0x5057
9e 8f 50 50 	movab $0x50,r0
9e 50 8f 50 	movab r0,$0x50505050
9f 8f 50    	pushab $0x50
a0 8f 50 50 	addw2 $0x5050,r0
a0 50 8f 50 	addw2 r0,$0x5050
a1 8f 50 50 	addw3 $0x5050,r0,r0
a1 50 8f 50 	addw3 r0,$0x5050,r0
a1 50 50 8f 	addw3 r0,r0,$0x5050
a2 8f 50 50 	subw2 $0x5050,r0
a2 50 8f 50 	subw2 r0,$0x5050
a3 8f 50 50 	subw3 $0x5050,r0,r0
a3 50 8f 50 	subw3 r0,$0x5050,r0
a3 50 50 8f 	subw3 r0,r0,$0x5050
a4 8f 50 50 	mulw2 $0x5050,r0
a4 50 8f 50 	mulw2 r0,$0x5050
a5 8f 50 50 	mulw3 $0x5050,r0,r0
a5 50 8f 50 	mulw3 r0,$0x5050,r0
a5 50 50 8f 	mulw3 r0,r0,$0x5050
a6 8f 50 50 	divw2 $0x5050,r0
a6 50 8f 50 	divw2 r0,$0x5050
a7 8f 50 50 	divw3 $0x5050,r0,r0
a7 50 8f 50 	divw3 r0,$0x5050,r0
a7 50 50 8f 	divw3 r0,r0,$0x5050
a8 8f 50 50 	bisw2 $0x5050,r0
a8 50 8f 50 	bisw2 r0,$0x5050
a9 8f 50 50 	bisw3 $0x5050,r0,r0
a9 50 8f 50 	bisw3 r0,$0x5050,r0
a9 50 50 8f 	bisw3 r0,r0,$0x5050
aa 8f 50 50 	bicw2 $0x5050,r0
aa 50 8f 50 	bicw2 r0,$0x5050
ab 8f 50 50 	bicw3 $0x5050,r0,r0
ab 50 8f 50 	bicw3 r0,$0x5050,r0
ab 50 50 8f 	bicw3 r0,r0,$0x5050
ac 8f 50 50 	xorw2 $0x5050,r0
ac 50 8f 50 	xorw2 r0,$0x5050
ad 8f 50 50 	xorw3 $0x5050,r0,r0
ad 50 8f 50 	xorw3 r0,$0x5050,r0
ad 50 50 8f 	xorw3 r0,r0,$0x5050
ae 8f 50 50 	mnegw $0x5050,r0
ae 50 8f 50 	mnegw r0,$0x5050
af 8f 50 50 	casew $0x5050,r0,r0
af 50 8f 50 	casew r0,$0x5050,r0
af 50 50 8f 	casew r0,r0,$0x5050
b0 8f 50 50 	movw $0x5050,r0
b0 50 8f 50 	movw r0,$0x5050
b1 8f 50 50 	cmpw $0x5050,r0
b1 50 8f 50 	cmpw r0,$0x5050
b2 8f 50 50 	mcomw $0x5050,r0
b2 50 8f 50 	mcomw r0,$0x5050
b3 8f 50 50 	bitw $0x5050,r0
b3 50 8f 50 	bitw r0,$0x5050
b4 8f 50 50 	clrw $0x5050
b5 8f 50 50 	tstw $0x5050
b6 8f 50 50 	incw $0x5050
b7 8f 50 50 	decw $0x5050
b8 8f 50 50 	bispsw $0x5050
b9 8f 50 50 	bicpsw $0x5050
ba 8f 50 50 	popr $0x5050
bb 8f 50 50 	pushr $0x5050
bc 8f 50 50 	chmk $0x5050
bd 8f 50 50 	chme $0x5050
be 8f 50 50 	chms $0x5050
bf 8f 50 50 	chmu $0x5050
c0 8f 50 50 	addl2 $0x50505050,r0
c0 50 8f 50 	addl2 r0,$0x50505050
c1 8f 50 50 	addl3 $0x50505050,r0,r0
c1 50 8f 50 	addl3 r0,$0x50505050,r0
c1 50 50 8f 	addl3 r0,r0,$0x50505050
c2 8f 50 50 	subl2 $0x50505050,r0
c2 50 8f 50 	subl2 r0,$0x50505050
c3 8f 50 50 	subl3 $0x50505050,r0,r0
c3 50 8f 50 	subl3 r0,$0x50505050,r0
c3 50 50 8f 	subl3 r0,r0,$0x50505050
c4 8f 50 50 	mull2 $0x50505050,r0
c4 50 8f 50 	mull2 r0,$0x50505050
c5 8f 50 50 	mull3 $0x50505050,r0,r0
c5 50 8f 50 	mull3 r0,$0x50505050,r0
c5 50 50 8f 	mull3 r0,r0,$0x50505050
c6 8f 50 50 	divl2 $0x50505050,r0
c6 50 8f 50 	divl2 r0,$0x50505050
c7 8f 50 50 	divl3 $0x50505050,r0,r0
c7 50 8f 50 	divl3 r0,$0x50505050,r0
c7 50 50 8f 	divl3 r0,r0,$0x50505050
c8 8f 50 50 	bisl2 $0x50505050,r0
c8 50 8f 50 	bisl2 r0,$0x50505050
c9 8f 50 50 	bisl3 $0x50505050,r0,r0
c9 50 8f 50 	bisl3 r0,$0x50505050,r0
c9 50 50 8f 	bisl3 r0,r0,$0x50505050
ca 8f 50 50 	bicl2 $0x50505050,r0
ca 50 8f 50 	bicl2 r0,$0x50505050
cb 8f 50 50 	bicl3 $0x50505050,r0,r0
cb 50 8f 50 	bicl3 r0,$0x50505050,r0
cb 50 50 8f 	bicl3 r0,r0,$0x50505050
cc 8f 50 50 	xorl2 $0x50505050,r0
cc 50 8f 50 	xorl2 r0,$0x50505050
cd 8f 50 50 	xorl3 $0x50505050,r0,r0
cd 50 8f 50 	xorl3 r0,$0x50505050,r0
cd 50 50 8f 	xorl3 r0,r0,$0x50505050
ce 8f 50 50 	mnegl $0x50505050,r0
ce 50 8f 50 	mnegl r0,$0x50505050
cf 8f 50 50 	casel $0x50505050,r0,r0
cf 50 8f 50 	casel r0,$0x50505050,r0
cf 50 50 8f 	casel r0,r0,$0x50505050
d0 8f 50 50 	movl $0x50505050,r0
d0 50 8f 50 	movl r0,$0x50505050
d1 8f 50 50 	cmpl $0x50505050,r0
d1 50 8f 50 	cmpl r0,$0x50505050
d2 8f 50 50 	mcoml $0x50505050,r0
d2 50 8f 50 	mcoml r0,$0x50505050
d3 8f 50 50 	bitl $0x50505050,r0
d3 50 8f 50 	bitl r0,$0x50505050
d4 8f 50 50 	clrf $0x50505050 [f-float]
d5 8f 50 50 	tstl $0x50505050
d6 8f 50 50 	incl $0x50505050
d7 8f 50 50 	decl $0x50505050
d8 8f 50 50 	adwc $0x50505050,r0
d8 50 8f 50 	adwc r0,$0x50505050
d9 8f 50 50 	sbwc $0x50505050,r0
d9 50 8f 50 	sbwc r0,$0x50505050
da 8f 50 50 	mtpr $0x50505050,r0
da 50 8f 50 	mtpr r0,$0x50505050
db 8f 50 50 	mfpr $0x50505050,r0
db 50 8f 50 	mfpr r0,$0x50505050
dc 8f 50 50 	movpsl $0x50505050
dd 8f 50 50 	pushl $0x50505050
de 8f 50 50 	moval $0x50505050,r0
de 50 8f 50 	moval r0,$0x50505050
df 8f 50 50 	pushal $0x50505050
e0 8f 50 50 	bbs $0x50505050,r0,0x58
e0 50 8f 50 	bbs r0,$0x50,0x55
e1 8f 50 50 	bbc $0x50505050,r0,0x58
e1 50 8f 50 	bbc r0,$0x50,0x55
e2 8f 50 50 	bbss $0x50505050,r0,0x58
e2 50 8f 50 	bbss r0,$0x50,0x55
e3 8f 50 50 	bbcs $0x50505050,r0,0x58
e3 50 8f 50 	bbcs r0,$0x50,0x55
e4 8f 50 50 	bbsc $0x50505050,r0,0x58
e4 50 8f 50 	bbsc r0,$0x50,0x55
e5 8f 50 50 	bbcc $0x50505050,r0,0x58
e5 50 8f 50 	bbcc r0,$0x50,0x55
e6 8f 50 50 	bbssi $0x50505050,r0,0x58
e6 50 8f 50 	bbssi r0,$0x50,0x55
e7 8f 50 50 	bbcci $0x50505050,r0,0x58
e7 50 8f 50 	bbcci r0,$0x50,0x55
e8 8f 50 50 	blbs $0x50505050,0x57
e9 8f 50 50 	blbc $0x50505050,0x57
ea 8f 50 50 	ffs $0x50505050,r0,r0,r0
ea 50 8f 50 	ffs r0,$0x50,r0,r0
ea 50 50 8f 	ffs r0,r0,$0x50,r0
ea 50 50 50 	ffs r0,r0,r0,$0x50505050
eb 8f 50 50 	ffc $0x50505050,r0,r0,r0
eb 50 8f 50 	ffc r0,$0x50,r0,r0
eb 50 50 8f 	ffc r0,r0,$0x50,r0
eb 50 50 50 	ffc r0,r0,r0,$0x50505050
ec 8f 50 50 	cmpv $0x50505050,r0,r0,r0
ec 50 8f 50 	cmpv r0,$0x50,r0,r0
ec 50 50 8f 	cmpv r0,r0,$0x50,r0
ec 50 50 50 	cmpv r0,r0,r0,$0x50505050
ed 8f 50 50 	cmpzv $0x50505050,r0,r0,r0
ed 50 8f 50 	cmpzv r0,$0x50,r0,r0
ed 50 50 8f 	cmpzv r0,r0,$0x50,r0
ed 50 50 50 	cmpzv r0,r0,r0,$0x50505050
ee 8f 50 50 	extv $0x50505050,r0,r0,r0
ee 50 8f 50 	extv r0,$0x50,r0,r0
ee 50 50 8f 	extv r0,r0,$0x50,r0
ee 50 50 50 	extv r0,r0,r0,$0x50505050
ef 8f 50 50 	extzv $0x50505050,r0,r0,r0
ef 50 8f 50 	extzv r0,$0x50,r0,r0
ef 50 50 8f 	extzv r0,r0,$0x50,r0
ef 50 50 50 	extzv r0,r0,r0,$0x50505050
f0 8f 50 50 	insv $0x50505050,r0,r0,r0
f0 50 8f 50 	insv r0,$0x50505050,r0,r0
f0 50 50 8f 	insv r0,r0,$0x50,r0
f0 50 50 50 	insv r0,r0,r0,$0x50
f1 8f 50 50 	acbl $0x50505050,r0,r0,0x505a
f1 50 8f 50 	acbl r0,$0x50505050,r0,0x505a
f1 50 50 8f 	acbl r0,r0,$0x50505050,0x505a
f2 8f 50 50 	aoblss $0x50505050,r0,0x58
f2 50 8f 50 	aoblss r0,$0x50505050,0x58
f3 8f 50 50 	aobleq $0x50505050,r0,0x58
f3 50 8f 50 	aobleq r0,$0x50505050,0x58
f4 8f 50 50 	sobgeq $0x50505050,0x57
f5 8f 50 50 	sobgtr $0x50505050,0x57
f6 8f 50 50 	cvtlb $0x50505050,r0
f6 50 8f 50 	cvtlb r0,$0x50
f7 8f 50 50 	cvtlw $0x50505050,r0
f7 50 8f 50 	cvtlw r0,$0x5050
f8 8f 50 50 	ashp $0x50,r0,r0,r0,r0,r0
f8 50 8f 50 	ashp r0,$0x5050,r0,r0,r0,r0
f8 50 50 8f 	ashp r0,r0,$0x50,r0,r0,r0
f8 50 50 50 	ashp r0,r0,r0,$0x50,r0,r0
f8 50 50 50 	ashp r0,r0,r0,r0,$0x5050,r0
f8 50 50 50 	ashp r0,r0,r0,r0,r0,$0x50
f9 8f 50 50 	cvtlp $0x50505050,r0,r0
f9 50 8f 50 	cvtlp r0,$0x5050,r0
f9 50 50 8f 	cvtlp r0,r0,$0x50
fa 8f 50 50 	callg $0x50,r0
fa 50 8f 50 	callg r0,$0x50
fb 8f 50 50 	calls $0x50505050,r0
fb 50 8f 50 	calls r0,$0x50
fc          	xfc
fd 32 8f 50 	cvtdh $0x5050505050505050 [d-float],r0
fd 32 50 8f 	cvtdh r0,$0x50505050505050505050505050505050 [h-float]
fd 33 8f 50 	cvtgf $0x5050505050505050 [g-float],r0
fd 33 50 8f 	cvtgf r0,$0x50505050505050505050505050505050 [h-float]
fd 40 8f 50 	addg2 $0x5050505050505050 [g-float],r0
fd 40 50 8f 	addg2 r0,$0x5050505050505050 [g-float]
fd 41 8f 50 	addg3 $0x5050505050505050 [g-float],r0,r0
fd 41 50 8f 	addg3 r0,$0x5050505050505050 [g-float],r0
fd 41 50 50 	addg3 r0,r0,$0x5050505050505050 [g-float]
fd 42 8f 50 	subg2 $0x5050505050505050 [g-float],r0
fd 42 50 8f 	subg2 r0,$0x5050505050505050 [g-float]
fd 43 8f 50 	subg3 $0x5050505050505050 [g-float],r0,r0
fd 43 50 8f 	subg3 r0,$0x5050505050505050 [g-float],r0
fd 43 50 50 	subg3 r0,r0,$0x5050505050505050 [g-float]
fd 44 8f 50 	mulg2 $0x5050505050505050 [g-float],r0
fd 44 50 8f 	mulg2 r0,$0x5050505050505050 [g-float]
fd 45 8f 50 	mulg3 $0x5050505050505050 [g-float],r0,r0
fd 45 50 8f 	mulg3 r0,$0x5050505050505050 [g-float],r0
fd 45 50 50 	mulg3 r0,r0,$0x5050505050505050 [g-float]
fd 46 8f 50 	divg2 $0x5050505050505050 [g-float],r0
fd 46 50 8f 	divg2 r0,$0x5050505050505050 [g-float]
fd 47 8f 50 	divg3 $0x5050505050505050 [g-float],r0,r0
fd 47 50 8f 	divg3 r0,$0x5050505050505050 [g-float],r0
fd 47 50 50 	divg3 r0,r0,$0x5050505050505050 [g-float]
fd 48 8f 50 	cvtgb $0x5050505050505050 [g-float],r0
fd 48 50 8f 	cvtgb r0,$0x50
fd 49 8f 50 	cvtgw $0x5050505050505050 [g-float],r0
fd 49 50 8f 	cvtgw r0,$0x5050
fd 4a 8f 50 	cvtgl $0x5050505050505050 [g-float],r0
fd 4a 50 8f 	cvtgl r0,$0x50505050
fd 4b 8f 50 	cvtrgl $0x5050505050505050 [g-float],r0
fd 4b 50 8f 	cvtrgl r0,$0x50505050
fd 4c 8f 50 	cvtbg $0x50,r0
fd 4c 50 8f 	cvtbg r0,$0x5050505050505050 [g-float]
fd 4d 8f 50 	cvtwg $0x5050,r0
fd 4d 50 8f 	cvtwg r0,$0x5050505050505050 [g-float]
fd 4e 8f 50 	cvtlg $0x50505050,r0
fd 4e 50 8f 	cvtlg r0,$0x5050505050505050 [g-float]
fd 4f 8f 50 	acbg $0x5050505050505050 [g-float],r0,r0,0x505f
fd 4f 50 8f 	acbg r0,$0x5050505050505050 [g-float],r0,0x505f
fd 4f 50 50 	acbg r0,r0,$0x5050505050505050 [g-float],0x505f
fd 50 8f 50 	movg $0x5050505050505050 [g-float],r0
fd 50 50 8f 	movg r0,$0x5050505050505050 [g-float]
fd 51 8f 50 	cmpg $0x5050505050505050 [g-float],r0
fd 51 50 8f 	cmpg r0,$0x5050505050505050 [g-float]
fd 52 8f 50 	mnegg $0x5050505050505050 [g-float],r0
fd 52 50 8f 	mnegg r0,$0x5050505050505050 [g-float]
fd 53 8f 50 	tstg $0x5050505050505050 [g-float]
fd 54 8f 50 	emodg $0x5050505050505050 [g-float],r0,r0,r0,r0
fd 54 50 8f 	emodg r0,$0x5050,r0,r0,r0
fd 54 50 50 	emodg r0,r0,$0x5050505050505050 [g-float],r0,r0
fd 54 50 50 	emodg r0,r0,r0,$0x50505050,r0
fd 54 50 50 	emodg r0,r0,r0,r0,$0x5050505050505050 [g-float]
fd 55 8f 50 	polyg $0x5050505050505050 [g-float],r0,r0
fd 55 50 8f 	polyg r0,$0x5050,r0
fd 55 50 50 	polyg r0,r0,$0x50
fd 56 8f 50 	cvtgh $0x5050505050505050 [g-float],r0
fd 56 50 8f 	cvtgh r0,$0x50505050505050505050505050505050 [h-float]
fd 60 8f 50 	addh2 $0x50505050505050505050505050505050 [h-float],r0
fd 60 50 8f 	addh2 r0,$0x50505050505050505050505050505050 [h-float]
fd 61 8f 50 	addh3 $0x50505050505050505050505050505050 [h-float],r0,r0
fd 61 50 8f 	addh3 r0,$0x50505050505050505050505050505050 [h-float],r0
fd 61 50 50 	addh3 r0,r0,$0x50505050505050505050505050505050 [h-float]
fd 62 8f 50 	subh2 $0x50505050505050505050505050505050 [h-float],r0
fd 62 50 8f 	subh2 r0,$0x50505050505050505050505050505050 [h-float]
fd 63 8f 50 	subh3 $0x50505050505050505050505050505050 [h-float],r0,r0
fd 63 50 8f 	subh3 r0,$0x50505050505050505050505050505050 [h-float],r0
fd 63 50 50 	subh3 r0,r0,$0x50505050505050505050505050505050 [h-float]
fd 64 8f 50 	mulh2 $0x50505050505050505050505050505050 [h-float],r0
fd 64 50 8f 	mulh2 r0,$0x50505050505050505050505050505050 [h-float]
fd 65 8f 50 	mulh3 $0x50505050505050505050505050505050 [h-float],r0,r0
fd 65 50 8f 	mulh3 r0,$0x50505050505050505050505050505050 [h-float],r0
fd 65 50 50 	mulh3 r0,r0,$0x50505050505050505050505050505050 [h-float]
fd 66 8f 50 	divh2 $0x50505050505050505050505050505050 [h-float],r0
fd 66 50 8f 	divh2 r0,$0x50505050505050505050505050505050 [h-float]
fd 67 8f 50 	divh3 $0x50505050505050505050505050505050 [h-float],r0,r0
fd 67 50 8f 	divh3 r0,$0x50505050505050505050505050505050 [h-float],r0
fd 67 50 50 	divh3 r0,r0,$0x50505050505050505050505050505050 [h-float]
fd 68 8f 50 	cvthb $0x50505050505050505050505050505050 [h-float],r0
fd 68 50 8f 	cvthb r0,$0x50
fd 69 8f 50 	cvthw $0x50505050505050505050505050505050 [h-float],r0
fd 69 50 8f 	cvthw r0,$0x5050
fd 6a 8f 50 	cvthl $0x50505050505050505050505050505050 [h-float],r0
fd 6a 50 8f 	cvthl r0,$0x50505050
fd 6b 8f 50 	cvtrhl $0x50505050505050505050505050505050 [h-float],r0
fd 6b 50 8f 	cvtrhl r0,$0x50505050
fd 6c 8f 50 	cvtbh $0x50,r0
fd 6c 50 8f 	cvtbh r0,$0x50505050505050505050505050505050 [h-float]
fd 6d 8f 50 	cvtwh $0x5050,r0
fd 6d 50 8f 	cvtwh r0,$0x50505050505050505050505050505050 [h-float]
fd 6e 8f 50 	cvtlh $0x50505050,r0
fd 6e 50 8f 	cvtlh r0,$0x50505050505050505050505050505050 [h-float]
fd 6f 8f 50 	acbh $0x50505050505050505050505050505050 [h-float],r0,r0,0x5067
fd 6f 50 8f 	acbh r0,$0x50505050505050505050505050505050 [h-float],r0,0x5067
fd 6f 50 50 	acbh r0,r0,$0x50505050505050505050505050505050 [h-float],0x5067
fd 70 8f 50 	movh $0x50505050505050505050505050505050 [h-float],r0
fd 70 50 8f 	movh r0,$0x50505050505050505050505050505050 [h-float]
fd 71 8f 50 	cmph $0x50505050505050505050505050505050 [h-float],r0
fd 71 50 8f 	cmph r0,$0x50505050505050505050505050505050 [h-float]
fd 72 8f 50 	mnegh $0x50505050505050505050505050505050 [h-float],r0
fd 72 50 8f 	mnegh r0,$0x50505050505050505050505050505050 [h-float]
fd 73 8f 50 	tsth $0x50505050505050505050505050505050 [h-float]
fd 74 8f 50 	emodh $0x50505050505050505050505050505050 [h-float],r0,r0,r0,r0
fd 74 50 8f 	emodh r0,$0x5050,r0,r0,r0
fd 74 50 50 	emodh r0,r0,$0x50505050505050505050505050505050 [h-float],r0,r0
fd 74 50 50 	emodh r0,r0,r0,$0x50505050,r0
fd 74 50 50 	emodh r0,r0,r0,r0,$0x50505050505050505050505050505050 [h-float]
fd 75 8f 50 	polyh $0x50505050505050505050505050505050 [h-float],r0,r0
fd 75 50 8f 	polyh r0,$0x5050,r0
fd 75 50 50 	polyh r0,r0,$0x50
fd 76 8f 50 	cvthg $0x50505050505050505050505050505050 [h-float],r0
fd 76 50 8f 	cvthg r0,$0x5050505050505050 [g-float]
fd 7c 8f 50 	clrh $0x50505050505050505050505050505050 [h-float]
fd 7d 8f 50 	movo $0x50505050505050505050505050505050,r0
fd 7d 50 8f 	movo r0,$0x50505050505050505050505050505050
fd 7e 8f 50 	movah $0x50505050505050505050505050505050 [h-float],r0
fd 7e 50 8f 	movah r0,$0x50505050
fd 7f 8f 50 	pushah $0x50505050505050505050505050505050 [h-float]
fd 98 8f 50 	cvtfh $0x50505050 [f-float],r0
fd 98 50 8f 	cvtfh r0,$0x50505050505050505050505050505050 [h-float]
fd 99 8f 50 	cvtfg $0x50505050 [f-float],r0
fd 99 50 8f 	cvtfg r0,$0x5050505050505050 [g-float]
fd f6 8f 50 	cvthf $0x50505050505050505050505050505050 [h-float],r0
fd f6 50 8f 	cvthf r0,$0x50505050 [f-float]
fd f7 8f 50 	cvthd $0x50505050505050505050505050505050 [h-float],r0
fd f7 50 8f 	cvthd r0,$0x5050505050505050 [d-float]
ff fd 8f 50 	bugl $0x50505050
ff fe 8f 50 	bugw $0x5050
