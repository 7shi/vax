00          	halt
01          	nop
02          	rei
03          	bpt
04          	ret
05          	rsb
06          	ldpctx
07          	svpctx
08 50 51 52 	cvtps r0,r1,r2,r3
09 50 51 52 	cvtsp r0,r1,r2,r3
0a 50 51 52 	index r0,r1,r2,r3,r4,r5
0b 50 51 52 	crc r0,r1,r2,r3
0c 50 51 52 	prober r0,r1,r2
0d 50 51 52 	probew r0,r1,r2
0e 50 51    	insque r0,r1
0f 50 51    	remque r0,r1
10 50       	bsbb 0x52
11 50       	brb 0x52
12 50       	bneq 0x52
13 50       	beql 0x52
14 50       	bgtr 0x52
15 50       	bleq 0x52
16 50       	jsb r0
17 50       	jmp r0
18 50       	bgeq 0x52
19 50       	blss 0x52
1a 50       	bgtru 0x52
1b 50       	blequ 0x52
1c 50       	bvc 0x52
1d 50       	bvs 0x52
1e 50       	bcc 0x52
1f 50       	blssu 0x52
20 50 51 52 	addp4 r0,r1,r2,r3
21 50 51 52 	addp6 r0,r1,r2,r3,r4,r5
22 50 51 52 	subp4 r0,r1,r2,r3
23 50 51 52 	subp6 r0,r1,r2,r3,r4,r5
24 50 51 52 	cvtpt r0,r1,r2,r3,r4
25 50 51 52 	mulp r0,r1,r2,r3,r4,r5
26 50 51 52 	cvttp r0,r1,r2,r3,r4
27 50 51 52 	divp r0,r1,r2,r3,r4,r5
28 50 51 52 	movc3 r0,r1,r2
29 50 51 52 	cmpc3 r0,r1,r2
2a 50 51 52 	scanc r0,r1,r2,r3
2b 50 51 52 	spanc r0,r1,r2,r3
2c 50 51 52 	movc5 r0,r1,r2,r3,r4
2d 50 51 52 	cmpc5 r0,r1,r2,r3,r4
2e 50 51 52 	movtc r0,r1,r2,r3,r4,r5
2f 50 51 52 	movtuc r0,r1,r2,r3,r4,r5
30 50 51    	bsbw 0x5153
31 50 51    	brw 0x5153
32 50 51    	cvtwl r0,r1
33 50 51    	cvtwb r0,r1
34 50 51 52 	movp r0,r1,r2
35 50 51 52 	cmpp3 r0,r1,r2
36 50 51 52 	cvtpl r0,r1,r2
37 50 51 52 	cmpp4 r0,r1,r2,r3
38 50 51 52 	editpc r0,r1,r2,r3
39 50 51 52 	matchc r0,r1,r2,r3
3a 50 51 52 	locc r0,r1,r2
3b 50 51 52 	skpc r0,r1,r2
3c 50 51    	movzwl r0,r1
3d 50 51 52 	acbw r0,r1,r2,0x5459
3e 50 51    	movaw r0,r1
3f 50       	pushaw r0
40 50 51    	addf2 r0,r1
41 50 51 52 	addf3 r0,r1,r2
42 50 51    	subf2 r0,r1
43 50 51 52 	subf3 r0,r1,r2
44 50 51    	mulf2 r0,r1
45 50 51 52 	mulf3 r0,r1,r2
46 50 51    	divf2 r0,r1
47 50 51 52 	divf3 r0,r1,r2
48 50 51    	cvtfb r0,r1
49 50 51    	cvtfw r0,r1
4a 50 51    	cvtfl r0,r1
4b 50 51    	cvtrfl r0,r1
4c 50 51    	cvtbf r0,r1
4d 50 51    	cvtwf r0,r1
4e 50 51    	cvtlf r0,r1
4f 50 51 52 	acbf r0,r1,r2,0x5459
50 50 51    	movf r0,r1
51 50 51    	cmpf r0,r1
52 50 51    	mnegf r0,r1
53 50       	tstf r0
54 50 51 52 	emodf r0,r1,r2,r3,r4
55 50 51 52 	polyf r0,r1,r2
56 50 51    	cvtfd r0,r1
57 50       	.word 0x5750
58 50 51    	adawi r0,r1
59 50       	.word 0x5950
5a 50       	.word 0x5a50
5b 50       	.word 0x5b50
5c 50 51    	insqhi r0,r1
5d 50 51    	insqti r0,r1
5e 50 51    	remqhi r0,r1
5f 50 51    	remqti r0,r1
60 50 51    	addd2 r0,r1
61 50 51 52 	addd3 r0,r1,r2
62 50 51    	subd2 r0,r1
63 50 51 52 	subd3 r0,r1,r2
64 50 51    	muld2 r0,r1
65 50 51 52 	muld3 r0,r1,r2
66 50 51    	divd2 r0,r1
67 50 51 52 	divd3 r0,r1,r2
68 50 51    	cvtdb r0,r1
69 50 51    	cvtdw r0,r1
6a 50 51    	cvtdl r0,r1
6b 50 51    	cvtrdl r0,r1
6c 50 51    	cvtbd r0,r1
6d 50 51    	cvtwd r0,r1
6e 50 51    	cvtld r0,r1
6f 50 51 52 	acbd r0,r1,r2,0x5459
70 50 51    	movd r0,r1
71 50 51    	cmpd r0,r1
72 50 51    	mnegd r0,r1
73 50       	tstd r0
74 50 51 52 	emodd r0,r1,r2,r3,r4
75 50 51 52 	polyd r0,r1,r2
76 50 51    	cvtdf r0,r1
77 50       	.word 0x7750
78 50 51 52 	ashl r0,r1,r2
79 50 51 52 	ashq r0,r1,r2
7a 50 51 52 	emul r0,r1,r2,r3
7b 50 51 52 	ediv r0,r1,r2,r3
7c 50       	clrd r0
7d 50 51    	movq r0,r1
7e 50 51    	movaq r0,r1
7f 50       	pushaq r0
80 50 51    	addb2 r0,r1
81 50 51 52 	addb3 r0,r1,r2
82 50 51    	subb2 r0,r1
83 50 51 52 	subb3 r0,r1,r2
84 50 51    	mulb2 r0,r1
85 50 51 52 	mulb3 r0,r1,r2
86 50 51    	divb2 r0,r1
87 50 51 52 	divb3 r0,r1,r2
88 50 51    	bisb2 r0,r1
89 50 51 52 	bisb3 r0,r1,r2
8a 50 51    	bicb2 r0,r1
8b 50 51 52 	bicb3 r0,r1,r2
8c 50 51    	xorb2 r0,r1
8d 50 51 52 	xorb3 r0,r1,r2
8e 50 51    	mnegb r0,r1
8f 50 51 52 	caseb r0,r1,r2
90 50 51    	movb r0,r1
91 50 51    	cmpb r0,r1
92 50 51    	mcomb r0,r1
93 50 51    	bitb r0,r1
94 50       	clrb r0
95 50       	tstb r0
96 50       	incb r0
97 50       	decb r0
98 50 51    	cvtbl r0,r1
99 50 51    	cvtbw r0,r1
9a 50 51    	movzbl r0,r1
9b 50 51    	movzbw r0,r1
9c 50 51 52 	rotl r0,r1,r2
9d 50 51 52 	acbb r0,r1,r2,0x5459
9e 50 51    	movab r0,r1
9f 50       	pushab r0
a0 50 51    	addw2 r0,r1
a1 50 51 52 	addw3 r0,r1,r2
a2 50 51    	subw2 r0,r1
a3 50 51 52 	subw3 r0,r1,r2
a4 50 51    	mulw2 r0,r1
a5 50 51 52 	mulw3 r0,r1,r2
a6 50 51    	divw2 r0,r1
a7 50 51 52 	divw3 r0,r1,r2
a8 50 51    	bisw2 r0,r1
a9 50 51 52 	bisw3 r0,r1,r2
aa 50 51    	bicw2 r0,r1
ab 50 51 52 	bicw3 r0,r1,r2
ac 50 51    	xorw2 r0,r1
ad 50 51 52 	xorw3 r0,r1,r2
ae 50 51    	mnegw r0,r1
af 50 51 52 	casew r0,r1,r2
b0 50 51    	movw r0,r1
b1 50 51    	cmpw r0,r1
b2 50 51    	mcomw r0,r1
b3 50 51    	bitw r0,r1
b4 50       	clrw r0
b5 50       	tstw r0
b6 50       	incw r0
b7 50       	decw r0
b8 50       	bispsw r0
b9 50       	bicpsw r0
ba 50       	popr r0
bb 50       	pushr r0
bc 50       	chmk r0
bd 50       	chme r0
be 50       	chms r0
bf 50       	chmu r0
c0 50 51    	addl2 r0,r1
c1 50 51 52 	addl3 r0,r1,r2
c2 50 51    	subl2 r0,r1
c3 50 51 52 	subl3 r0,r1,r2
c4 50 51    	mull2 r0,r1
c5 50 51 52 	mull3 r0,r1,r2
c6 50 51    	divl2 r0,r1
c7 50 51 52 	divl3 r0,r1,r2
c8 50 51    	bisl2 r0,r1
c9 50 51 52 	bisl3 r0,r1,r2
ca 50 51    	bicl2 r0,r1
cb 50 51 52 	bicl3 r0,r1,r2
cc 50 51    	xorl2 r0,r1
cd 50 51 52 	xorl3 r0,r1,r2
ce 50 51    	mnegl r0,r1
cf 50 51 52 	casel r0,r1,r2
d0 50 51    	movl r0,r1
d1 50 51    	cmpl r0,r1
d2 50 51    	mcoml r0,r1
d3 50 51    	bitl r0,r1
d4 50       	clrf r0
d5 50       	tstl r0
d6 50       	incl r0
d7 50       	decl r0
d8 50 51    	adwc r0,r1
d9 50 51    	sbwc r0,r1
da 50 51    	mtpr r0,r1
db 50 51    	mfpr r0,r1
dc 50       	movpsl r0
dd 50       	pushl r0
de 50 51    	moval r0,r1
df 50       	pushal r0
e0 50 51 52 	bbs r0,r1,0x56
e1 50 51 52 	bbc r0,r1,0x56
e2 50 51 52 	bbss r0,r1,0x56
e3 50 51 52 	bbcs r0,r1,0x56
e4 50 51 52 	bbsc r0,r1,0x56
e5 50 51 52 	bbcc r0,r1,0x56
e6 50 51 52 	bbssi r0,r1,0x56
e7 50 51 52 	bbcci r0,r1,0x56
e8 50 51    	blbs r0,0x54
e9 50 51    	blbc r0,0x54
ea 50 51 52 	ffs r0,r1,r2,r3
eb 50 51 52 	ffc r0,r1,r2,r3
ec 50 51 52 	cmpv r0,r1,r2,r3
ed 50 51 52 	cmpzv r0,r1,r2,r3
ee 50 51 52 	extv r0,r1,r2,r3
ef 50 51 52 	extzv r0,r1,r2,r3
f0 50 51 52 	insv r0,r1,r2,r3
f1 50 51 52 	acbl r0,r1,r2,0x5459
f2 50 51 52 	aoblss r0,r1,0x56
f3 50 51 52 	aobleq r0,r1,0x56
f4 50 51    	sobgeq r0,0x54
f5 50 51    	sobgtr r0,0x54
f6 50 51    	cvtlb r0,r1
f7 50 51    	cvtlw r0,r1
f8 50 51 52 	ashp r0,r1,r2,r3,r4,r5
f9 50 51 52 	cvtlp r0,r1,r2
fa 50 51    	callg r0,r1
fb 50 51    	calls r0,r1
fc          	xfc
fd 00       	.word 0xfd00
fd 01       	.word 0xfd01
fd 02       	.word 0xfd02
fd 03       	.word 0xfd03
fd 04       	.word 0xfd04
fd 05       	.word 0xfd05
fd 06       	.word 0xfd06
fd 07       	.word 0xfd07
fd 08       	.word 0xfd08
fd 09       	.word 0xfd09
fd 0a       	.word 0xfd0a
fd 0b       	.word 0xfd0b
fd 0c       	.word 0xfd0c
fd 0d       	.word 0xfd0d
fd 0e       	.word 0xfd0e
fd 0f       	.word 0xfd0f
fd 10       	.word 0xfd10
fd 11       	.word 0xfd11
fd 12       	.word 0xfd12
fd 13       	.word 0xfd13
fd 14       	.word 0xfd14
fd 15       	.word 0xfd15
fd 16       	.word 0xfd16
fd 17       	.word 0xfd17
fd 18       	.word 0xfd18
fd 19       	.word 0xfd19
fd 1a       	.word 0xfd1a
fd 1b       	.word 0xfd1b
fd 1c       	.word 0xfd1c
fd 1d       	.word 0xfd1d
fd 1e       	.word 0xfd1e
fd 1f       	.word 0xfd1f
fd 20       	.word 0xfd20
fd 21       	.word 0xfd21
fd 22       	.word 0xfd22
fd 23       	.word 0xfd23
fd 24       	.word 0xfd24
fd 25       	.word 0xfd25
fd 26       	.word 0xfd26
fd 27       	.word 0xfd27
fd 28       	.word 0xfd28
fd 29       	.word 0xfd29
fd 2a       	.word 0xfd2a
fd 2b       	.word 0xfd2b
fd 2c       	.word 0xfd2c
fd 2d       	.word 0xfd2d
fd 2e       	.word 0xfd2e
fd 2f       	.word 0xfd2f
fd 30       	.word 0xfd30
fd 31       	.word 0xfd31
fd 32 50 51 	cvtdh r0,r1
fd 33 50 51 	cvtgf r0,r1
fd 34       	.word 0xfd34
fd 35       	.word 0xfd35
fd 36       	.word 0xfd36
fd 37       	.word 0xfd37
fd 38       	.word 0xfd38
fd 39       	.word 0xfd39
fd 3a       	.word 0xfd3a
fd 3b       	.word 0xfd3b
fd 3c       	.word 0xfd3c
fd 3d       	.word 0xfd3d
fd 3e       	.word 0xfd3e
fd 3f       	.word 0xfd3f
fd 40 50 51 	addg2 r0,r1
fd 41 50 51 	addg3 r0,r1,r2
fd 42 50 51 	subg2 r0,r1
fd 43 50 51 	subg3 r0,r1,r2
fd 44 50 51 	mulg2 r0,r1
fd 45 50 51 	mulg3 r0,r1,r2
fd 46 50 51 	divg2 r0,r1
fd 47 50 51 	divg3 r0,r1,r2
fd 48 50 51 	cvtgb r0,r1
fd 49 50 51 	cvtgw r0,r1
fd 4a 50 51 	cvtgl r0,r1
fd 4b 50 51 	cvtrgl r0,r1
fd 4c 50 51 	cvtbg r0,r1
fd 4d 50 51 	cvtwg r0,r1
fd 4e 50 51 	cvtlg r0,r1
fd 4f 50 51 	acbg r0,r1,r2,0x545a
fd 50 50 51 	movg r0,r1
fd 51 50 51 	cmpg r0,r1
fd 52 50 51 	mnegg r0,r1
fd 53 50    	tstg r0
fd 54 50 51 	emodg r0,r1,r2,r3,r4
fd 55 50 51 	polyg r0,r1,r2
fd 56 50 51 	cvtgh r0,r1
fd 57       	.word 0xfd57
fd 58       	.word 0xfd58
fd 59       	.word 0xfd59
fd 5a       	.word 0xfd5a
fd 5b       	.word 0xfd5b
fd 5c       	.word 0xfd5c
fd 5d       	.word 0xfd5d
fd 5e       	.word 0xfd5e
fd 5f       	.word 0xfd5f
fd 60 50 51 	addh2 r0,r1
fd 61 50 51 	addh3 r0,r1,r2
fd 62 50 51 	subh2 r0,r1
fd 63 50 51 	subh3 r0,r1,r2
fd 64 50 51 	mulh2 r0,r1
fd 65 50 51 	mulh3 r0,r1,r2
fd 66 50 51 	divh2 r0,r1
fd 67 50 51 	divh3 r0,r1,r2
fd 68 50 51 	cvthb r0,r1
fd 69 50 51 	cvthw r0,r1
fd 6a 50 51 	cvthl r0,r1
fd 6b 50 51 	cvtrhl r0,r1
fd 6c 50 51 	cvtbh r0,r1
fd 6d 50 51 	cvtwh r0,r1
fd 6e 50 51 	cvtlh r0,r1
fd 6f 50 51 	acbh r0,r1,r2,0x545a
fd 70 50 51 	movh r0,r1
fd 71 50 51 	cmph r0,r1
fd 72 50 51 	mnegh r0,r1
fd 73 50    	tsth r0
fd 74 50 51 	emodh r0,r1,r2,r3,r4
fd 75 50 51 	polyh r0,r1,r2
fd 76 50 51 	cvthg r0,r1
fd 77       	.word 0xfd77
fd 78       	.word 0xfd78
fd 79       	.word 0xfd79
fd 7a       	.word 0xfd7a
fd 7b       	.word 0xfd7b
fd 7c 50    	clrh r0
fd 7d 50 51 	movo r0,r1
fd 7e 50 51 	movah r0,r1
fd 7f 50    	pushah r0
fd 80       	.word 0xfd80
fd 81       	.word 0xfd81
fd 82       	.word 0xfd82
fd 83       	.word 0xfd83
fd 84       	.word 0xfd84
fd 85       	.word 0xfd85
fd 86       	.word 0xfd86
fd 87       	.word 0xfd87
fd 88       	.word 0xfd88
fd 89       	.word 0xfd89
fd 8a       	.word 0xfd8a
fd 8b       	.word 0xfd8b
fd 8c       	.word 0xfd8c
fd 8d       	.word 0xfd8d
fd 8e       	.word 0xfd8e
fd 8f       	.word 0xfd8f
fd 90       	.word 0xfd90
fd 91       	.word 0xfd91
fd 92       	.word 0xfd92
fd 93       	.word 0xfd93
fd 94       	.word 0xfd94
fd 95       	.word 0xfd95
fd 96       	.word 0xfd96
fd 97       	.word 0xfd97
fd 98 50 51 	cvtfh r0,r1
fd 99 50 51 	cvtfg r0,r1
fd 9a       	.word 0xfd9a
fd 9b       	.word 0xfd9b
fd 9c       	.word 0xfd9c
fd 9d       	.word 0xfd9d
fd 9e       	.word 0xfd9e
fd 9f       	.word 0xfd9f
fd a0       	.word 0xfda0
fd a1       	.word 0xfda1
fd a2       	.word 0xfda2
fd a3       	.word 0xfda3
fd a4       	.word 0xfda4
fd a5       	.word 0xfda5
fd a6       	.word 0xfda6
fd a7       	.word 0xfda7
fd a8       	.word 0xfda8
fd a9       	.word 0xfda9
fd aa       	.word 0xfdaa
fd ab       	.word 0xfdab
fd ac       	.word 0xfdac
fd ad       	.word 0xfdad
fd ae       	.word 0xfdae
fd af       	.word 0xfdaf
fd b0       	.word 0xfdb0
fd b1       	.word 0xfdb1
fd b2       	.word 0xfdb2
fd b3       	.word 0xfdb3
fd b4       	.word 0xfdb4
fd b5       	.word 0xfdb5
fd b6       	.word 0xfdb6
fd b7       	.word 0xfdb7
fd b8       	.word 0xfdb8
fd b9       	.word 0xfdb9
fd ba       	.word 0xfdba
fd bb       	.word 0xfdbb
fd bc       	.word 0xfdbc
fd bd       	.word 0xfdbd
fd be       	.word 0xfdbe
fd bf       	.word 0xfdbf
fd c0       	.word 0xfdc0
fd c1       	.word 0xfdc1
fd c2       	.word 0xfdc2
fd c3       	.word 0xfdc3
fd c4       	.word 0xfdc4
fd c5       	.word 0xfdc5
fd c6       	.word 0xfdc6
fd c7       	.word 0xfdc7
fd c8       	.word 0xfdc8
fd c9       	.word 0xfdc9
fd ca       	.word 0xfdca
fd cb       	.word 0xfdcb
fd cc       	.word 0xfdcc
fd cd       	.word 0xfdcd
fd ce       	.word 0xfdce
fd cf       	.word 0xfdcf
fd d0       	.word 0xfdd0
fd d1       	.word 0xfdd1
fd d2       	.word 0xfdd2
fd d3       	.word 0xfdd3
fd d4       	.word 0xfdd4
fd d5       	.word 0xfdd5
fd d6       	.word 0xfdd6
fd d7       	.word 0xfdd7
fd d8       	.word 0xfdd8
fd d9       	.word 0xfdd9
fd da       	.word 0xfdda
fd db       	.word 0xfddb
fd dc       	.word 0xfddc
fd dd       	.word 0xfddd
fd de       	.word 0xfdde
fd df       	.word 0xfddf
fd e0       	.word 0xfde0
fd e1       	.word 0xfde1
fd e2       	.word 0xfde2
fd e3       	.word 0xfde3
fd e4       	.word 0xfde4
fd e5       	.word 0xfde5
fd e6       	.word 0xfde6
fd e7       	.word 0xfde7
fd e8       	.word 0xfde8
fd e9       	.word 0xfde9
fd ea       	.word 0xfdea
fd eb       	.word 0xfdeb
fd ec       	.word 0xfdec
fd ed       	.word 0xfded
fd ee       	.word 0xfdee
fd ef       	.word 0xfdef
fd f0       	.word 0xfdf0
fd f1       	.word 0xfdf1
fd f2       	.word 0xfdf2
fd f3       	.word 0xfdf3
fd f4       	.word 0xfdf4
fd f5       	.word 0xfdf5
fd f6 50 51 	cvthf r0,r1
fd f7 50 51 	cvthd r0,r1
fd f8       	.word 0xfdf8
fd f9       	.word 0xfdf9
fd fa       	.word 0xfdfa
fd fb       	.word 0xfdfb
fd fc       	.word 0xfdfc
fd fd       	.word 0xfdfd
fd fe       	.word 0xfdfe
fd ff       	.word 0xfdff
fe 00       	.word 0xfe00
fe 01       	.word 0xfe01
fe 02       	.word 0xfe02
fe 03       	.word 0xfe03
fe 04       	.word 0xfe04
fe 05       	.word 0xfe05
fe 06       	.word 0xfe06
fe 07       	.word 0xfe07
fe 08       	.word 0xfe08
fe 09       	.word 0xfe09
fe 0a       	.word 0xfe0a
fe 0b       	.word 0xfe0b
fe 0c       	.word 0xfe0c
fe 0d       	.word 0xfe0d
fe 0e       	.word 0xfe0e
fe 0f       	.word 0xfe0f
fe 10       	.word 0xfe10
fe 11       	.word 0xfe11
fe 12       	.word 0xfe12
fe 13       	.word 0xfe13
fe 14       	.word 0xfe14
fe 15       	.word 0xfe15
fe 16       	.word 0xfe16
fe 17       	.word 0xfe17
fe 18       	.word 0xfe18
fe 19       	.word 0xfe19
fe 1a       	.word 0xfe1a
fe 1b       	.word 0xfe1b
fe 1c       	.word 0xfe1c
fe 1d       	.word 0xfe1d
fe 1e       	.word 0xfe1e
fe 1f       	.word 0xfe1f
fe 20       	.word 0xfe20
fe 21       	.word 0xfe21
fe 22       	.word 0xfe22
fe 23       	.word 0xfe23
fe 24       	.word 0xfe24
fe 25       	.word 0xfe25
fe 26       	.word 0xfe26
fe 27       	.word 0xfe27
fe 28       	.word 0xfe28
fe 29       	.word 0xfe29
fe 2a       	.word 0xfe2a
fe 2b       	.word 0xfe2b
fe 2c       	.word 0xfe2c
fe 2d       	.word 0xfe2d
fe 2e       	.word 0xfe2e
fe 2f       	.word 0xfe2f
fe 30       	.word 0xfe30
fe 31       	.word 0xfe31
fe 32       	.word 0xfe32
fe 33       	.word 0xfe33
fe 34       	.word 0xfe34
fe 35       	.word 0xfe35
fe 36       	.word 0xfe36
fe 37       	.word 0xfe37
fe 38       	.word 0xfe38
fe 39       	.word 0xfe39
fe 3a       	.word 0xfe3a
fe 3b       	.word 0xfe3b
fe 3c       	.word 0xfe3c
fe 3d       	.word 0xfe3d
fe 3e       	.word 0xfe3e
fe 3f       	.word 0xfe3f
fe 40       	.word 0xfe40
fe 41       	.word 0xfe41
fe 42       	.word 0xfe42
fe 43       	.word 0xfe43
fe 44       	.word 0xfe44
fe 45       	.word 0xfe45
fe 46       	.word 0xfe46
fe 47       	.word 0xfe47
fe 48       	.word 0xfe48
fe 49       	.word 0xfe49
fe 4a       	.word 0xfe4a
fe 4b       	.word 0xfe4b
fe 4c       	.word 0xfe4c
fe 4d       	.word 0xfe4d
fe 4e       	.word 0xfe4e
fe 4f       	.word 0xfe4f
fe 50       	.word 0xfe50
fe 51       	.word 0xfe51
fe 52       	.word 0xfe52
fe 53       	.word 0xfe53
fe 54       	.word 0xfe54
fe 55       	.word 0xfe55
fe 56       	.word 0xfe56
fe 57       	.word 0xfe57
fe 58       	.word 0xfe58
fe 59       	.word 0xfe59
fe 5a       	.word 0xfe5a
fe 5b       	.word 0xfe5b
fe 5c       	.word 0xfe5c
fe 5d       	.word 0xfe5d
fe 5e       	.word 0xfe5e
fe 5f       	.word 0xfe5f
fe 60       	.word 0xfe60
fe 61       	.word 0xfe61
fe 62       	.word 0xfe62
fe 63       	.word 0xfe63
fe 64       	.word 0xfe64
fe 65       	.word 0xfe65
fe 66       	.word 0xfe66
fe 67       	.word 0xfe67
fe 68       	.word 0xfe68
fe 69       	.word 0xfe69
fe 6a       	.word 0xfe6a
fe 6b       	.word 0xfe6b
fe 6c       	.word 0xfe6c
fe 6d       	.word 0xfe6d
fe 6e       	.word 0xfe6e
fe 6f       	.word 0xfe6f
fe 70       	.word 0xfe70
fe 71       	.word 0xfe71
fe 72       	.word 0xfe72
fe 73       	.word 0xfe73
fe 74       	.word 0xfe74
fe 75       	.word 0xfe75
fe 76       	.word 0xfe76
fe 77       	.word 0xfe77
fe 78       	.word 0xfe78
fe 79       	.word 0xfe79
fe 7a       	.word 0xfe7a
fe 7b       	.word 0xfe7b
fe 7c       	.word 0xfe7c
fe 7d       	.word 0xfe7d
fe 7e       	.word 0xfe7e
fe 7f       	.word 0xfe7f
fe 80       	.word 0xfe80
fe 81       	.word 0xfe81
fe 82       	.word 0xfe82
fe 83       	.word 0xfe83
fe 84       	.word 0xfe84
fe 85       	.word 0xfe85
fe 86       	.word 0xfe86
fe 87       	.word 0xfe87
fe 88       	.word 0xfe88
fe 89       	.word 0xfe89
fe 8a       	.word 0xfe8a
fe 8b       	.word 0xfe8b
fe 8c       	.word 0xfe8c
fe 8d       	.word 0xfe8d
fe 8e       	.word 0xfe8e
fe 8f       	.word 0xfe8f
fe 90       	.word 0xfe90
fe 91       	.word 0xfe91
fe 92       	.word 0xfe92
fe 93       	.word 0xfe93
fe 94       	.word 0xfe94
fe 95       	.word 0xfe95
fe 96       	.word 0xfe96
fe 97       	.word 0xfe97
fe 98       	.word 0xfe98
fe 99       	.word 0xfe99
fe 9a       	.word 0xfe9a
fe 9b       	.word 0xfe9b
fe 9c       	.word 0xfe9c
fe 9d       	.word 0xfe9d
fe 9e       	.word 0xfe9e
fe 9f       	.word 0xfe9f
fe a0       	.word 0xfea0
fe a1       	.word 0xfea1
fe a2       	.word 0xfea2
fe a3       	.word 0xfea3
fe a4       	.word 0xfea4
fe a5       	.word 0xfea5
fe a6       	.word 0xfea6
fe a7       	.word 0xfea7
fe a8       	.word 0xfea8
fe a9       	.word 0xfea9
fe aa       	.word 0xfeaa
fe ab       	.word 0xfeab
fe ac       	.word 0xfeac
fe ad       	.word 0xfead
fe ae       	.word 0xfeae
fe af       	.word 0xfeaf
fe b0       	.word 0xfeb0
fe b1       	.word 0xfeb1
fe b2       	.word 0xfeb2
fe b3       	.word 0xfeb3
fe b4       	.word 0xfeb4
fe b5       	.word 0xfeb5
fe b6       	.word 0xfeb6
fe b7       	.word 0xfeb7
fe b8       	.word 0xfeb8
fe b9       	.word 0xfeb9
fe ba       	.word 0xfeba
fe bb       	.word 0xfebb
fe bc       	.word 0xfebc
fe bd       	.word 0xfebd
fe be       	.word 0xfebe
fe bf       	.word 0xfebf
fe c0       	.word 0xfec0
fe c1       	.word 0xfec1
fe c2       	.word 0xfec2
fe c3       	.word 0xfec3
fe c4       	.word 0xfec4
fe c5       	.word 0xfec5
fe c6       	.word 0xfec6
fe c7       	.word 0xfec7
fe c8       	.word 0xfec8
fe c9       	.word 0xfec9
fe ca       	.word 0xfeca
fe cb       	.word 0xfecb
fe cc       	.word 0xfecc
fe cd       	.word 0xfecd
fe ce       	.word 0xfece
fe cf       	.word 0xfecf
fe d0       	.word 0xfed0
fe d1       	.word 0xfed1
fe d2       	.word 0xfed2
fe d3       	.word 0xfed3
fe d4       	.word 0xfed4
fe d5       	.word 0xfed5
fe d6       	.word 0xfed6
fe d7       	.word 0xfed7
fe d8       	.word 0xfed8
fe d9       	.word 0xfed9
fe da       	.word 0xfeda
fe db       	.word 0xfedb
fe dc       	.word 0xfedc
fe dd       	.word 0xfedd
fe de       	.word 0xfede
fe df       	.word 0xfedf
fe e0       	.word 0xfee0
fe e1       	.word 0xfee1
fe e2       	.word 0xfee2
fe e3       	.word 0xfee3
fe e4       	.word 0xfee4
fe e5       	.word 0xfee5
fe e6       	.word 0xfee6
fe e7       	.word 0xfee7
fe e8       	.word 0xfee8
fe e9       	.word 0xfee9
fe ea       	.word 0xfeea
fe eb       	.word 0xfeeb
fe ec       	.word 0xfeec
fe ed       	.word 0xfeed
fe ee       	.word 0xfeee
fe ef       	.word 0xfeef
fe f0       	.word 0xfef0
fe f1       	.word 0xfef1
fe f2       	.word 0xfef2
fe f3       	.word 0xfef3
fe f4       	.word 0xfef4
fe f5       	.word 0xfef5
fe f6       	.word 0xfef6
fe f7       	.word 0xfef7
fe f8       	.word 0xfef8
fe f9       	.word 0xfef9
fe fa       	.word 0xfefa
fe fb       	.word 0xfefb
fe fc       	.word 0xfefc
fe fd       	.word 0xfefd
fe fe       	.word 0xfefe
fe ff       	.word 0xfeff
ff 00       	.word 0xff00
ff 01       	.word 0xff01
ff 02       	.word 0xff02
ff 03       	.word 0xff03
ff 04       	.word 0xff04
ff 05       	.word 0xff05
ff 06       	.word 0xff06
ff 07       	.word 0xff07
ff 08       	.word 0xff08
ff 09       	.word 0xff09
ff 0a       	.word 0xff0a
ff 0b       	.word 0xff0b
ff 0c       	.word 0xff0c
ff 0d       	.word 0xff0d
ff 0e       	.word 0xff0e
ff 0f       	.word 0xff0f
ff 10       	.word 0xff10
ff 11       	.word 0xff11
ff 12       	.word 0xff12
ff 13       	.word 0xff13
ff 14       	.word 0xff14
ff 15       	.word 0xff15
ff 16       	.word 0xff16
ff 17       	.word 0xff17
ff 18       	.word 0xff18
ff 19       	.word 0xff19
ff 1a       	.word 0xff1a
ff 1b       	.word 0xff1b
ff 1c       	.word 0xff1c
ff 1d       	.word 0xff1d
ff 1e       	.word 0xff1e
ff 1f       	.word 0xff1f
ff 20       	.word 0xff20
ff 21       	.word 0xff21
ff 22       	.word 0xff22
ff 23       	.word 0xff23
ff 24       	.word 0xff24
ff 25       	.word 0xff25
ff 26       	.word 0xff26
ff 27       	.word 0xff27
ff 28       	.word 0xff28
ff 29       	.word 0xff29
ff 2a       	.word 0xff2a
ff 2b       	.word 0xff2b
ff 2c       	.word 0xff2c
ff 2d       	.word 0xff2d
ff 2e       	.word 0xff2e
ff 2f       	.word 0xff2f
ff 30       	.word 0xff30
ff 31       	.word 0xff31
ff 32       	.word 0xff32
ff 33       	.word 0xff33
ff 34       	.word 0xff34
ff 35       	.word 0xff35
ff 36       	.word 0xff36
ff 37       	.word 0xff37
ff 38       	.word 0xff38
ff 39       	.word 0xff39
ff 3a       	.word 0xff3a
ff 3b       	.word 0xff3b
ff 3c       	.word 0xff3c
ff 3d       	.word 0xff3d
ff 3e       	.word 0xff3e
ff 3f       	.word 0xff3f
ff 40       	.word 0xff40
ff 41       	.word 0xff41
ff 42       	.word 0xff42
ff 43       	.word 0xff43
ff 44       	.word 0xff44
ff 45       	.word 0xff45
ff 46       	.word 0xff46
ff 47       	.word 0xff47
ff 48       	.word 0xff48
ff 49       	.word 0xff49
ff 4a       	.word 0xff4a
ff 4b       	.word 0xff4b
ff 4c       	.word 0xff4c
ff 4d       	.word 0xff4d
ff 4e       	.word 0xff4e
ff 4f       	.word 0xff4f
ff 50       	.word 0xff50
ff 51       	.word 0xff51
ff 52       	.word 0xff52
ff 53       	.word 0xff53
ff 54       	.word 0xff54
ff 55       	.word 0xff55
ff 56       	.word 0xff56
ff 57       	.word 0xff57
ff 58       	.word 0xff58
ff 59       	.word 0xff59
ff 5a       	.word 0xff5a
ff 5b       	.word 0xff5b
ff 5c       	.word 0xff5c
ff 5d       	.word 0xff5d
ff 5e       	.word 0xff5e
ff 5f       	.word 0xff5f
ff 60       	.word 0xff60
ff 61       	.word 0xff61
ff 62       	.word 0xff62
ff 63       	.word 0xff63
ff 64       	.word 0xff64
ff 65       	.word 0xff65
ff 66       	.word 0xff66
ff 67       	.word 0xff67
ff 68       	.word 0xff68
ff 69       	.word 0xff69
ff 6a       	.word 0xff6a
ff 6b       	.word 0xff6b
ff 6c       	.word 0xff6c
ff 6d       	.word 0xff6d
ff 6e       	.word 0xff6e
ff 6f       	.word 0xff6f
ff 70       	.word 0xff70
ff 71       	.word 0xff71
ff 72       	.word 0xff72
ff 73       	.word 0xff73
ff 74       	.word 0xff74
ff 75       	.word 0xff75
ff 76       	.word 0xff76
ff 77       	.word 0xff77
ff 78       	.word 0xff78
ff 79       	.word 0xff79
ff 7a       	.word 0xff7a
ff 7b       	.word 0xff7b
ff 7c       	.word 0xff7c
ff 7d       	.word 0xff7d
ff 7e       	.word 0xff7e
ff 7f       	.word 0xff7f
ff 80       	.word 0xff80
ff 81       	.word 0xff81
ff 82       	.word 0xff82
ff 83       	.word 0xff83
ff 84       	.word 0xff84
ff 85       	.word 0xff85
ff 86       	.word 0xff86
ff 87       	.word 0xff87
ff 88       	.word 0xff88
ff 89       	.word 0xff89
ff 8a       	.word 0xff8a
ff 8b       	.word 0xff8b
ff 8c       	.word 0xff8c
ff 8d       	.word 0xff8d
ff 8e       	.word 0xff8e
ff 8f       	.word 0xff8f
ff 90       	.word 0xff90
ff 91       	.word 0xff91
ff 92       	.word 0xff92
ff 93       	.word 0xff93
ff 94       	.word 0xff94
ff 95       	.word 0xff95
ff 96       	.word 0xff96
ff 97       	.word 0xff97
ff 98       	.word 0xff98
ff 99       	.word 0xff99
ff 9a       	.word 0xff9a
ff 9b       	.word 0xff9b
ff 9c       	.word 0xff9c
ff 9d       	.word 0xff9d
ff 9e       	.word 0xff9e
ff 9f       	.word 0xff9f
ff a0       	.word 0xffa0
ff a1       	.word 0xffa1
ff a2       	.word 0xffa2
ff a3       	.word 0xffa3
ff a4       	.word 0xffa4
ff a5       	.word 0xffa5
ff a6       	.word 0xffa6
ff a7       	.word 0xffa7
ff a8       	.word 0xffa8
ff a9       	.word 0xffa9
ff aa       	.word 0xffaa
ff ab       	.word 0xffab
ff ac       	.word 0xffac
ff ad       	.word 0xffad
ff ae       	.word 0xffae
ff af       	.word 0xffaf
ff b0       	.word 0xffb0
ff b1       	.word 0xffb1
ff b2       	.word 0xffb2
ff b3       	.word 0xffb3
ff b4       	.word 0xffb4
ff b5       	.word 0xffb5
ff b6       	.word 0xffb6
ff b7       	.word 0xffb7
ff b8       	.word 0xffb8
ff b9       	.word 0xffb9
ff ba       	.word 0xffba
ff bb       	.word 0xffbb
ff bc       	.word 0xffbc
ff bd       	.word 0xffbd
ff be       	.word 0xffbe
ff bf       	.word 0xffbf
ff c0       	.word 0xffc0
ff c1       	.word 0xffc1
ff c2       	.word 0xffc2
ff c3       	.word 0xffc3
ff c4       	.word 0xffc4
ff c5       	.word 0xffc5
ff c6       	.word 0xffc6
ff c7       	.word 0xffc7
ff c8       	.word 0xffc8
ff c9       	.word 0xffc9
ff ca       	.word 0xffca
ff cb       	.word 0xffcb
ff cc       	.word 0xffcc
ff cd       	.word 0xffcd
ff ce       	.word 0xffce
ff cf       	.word 0xffcf
ff d0       	.word 0xffd0
ff d1       	.word 0xffd1
ff d2       	.word 0xffd2
ff d3       	.word 0xffd3
ff d4       	.word 0xffd4
ff d5       	.word 0xffd5
ff d6       	.word 0xffd6
ff d7       	.word 0xffd7
ff d8       	.word 0xffd8
ff d9       	.word 0xffd9
ff da       	.word 0xffda
ff db       	.word 0xffdb
ff dc       	.word 0xffdc
ff dd       	.word 0xffdd
ff de       	.word 0xffde
ff df       	.word 0xffdf
ff e0       	.word 0xffe0
ff e1       	.word 0xffe1
ff e2       	.word 0xffe2
ff e3       	.word 0xffe3
ff e4       	.word 0xffe4
ff e5       	.word 0xffe5
ff e6       	.word 0xffe6
ff e7       	.word 0xffe7
ff e8       	.word 0xffe8
ff e9       	.word 0xffe9
ff ea       	.word 0xffea
ff eb       	.word 0xffeb
ff ec       	.word 0xffec
ff ed       	.word 0xffed
ff ee       	.word 0xffee
ff ef       	.word 0xffef
ff f0       	.word 0xfff0
ff f1       	.word 0xfff1
ff f2       	.word 0xfff2
ff f3       	.word 0xfff3
ff f4       	.word 0xfff4
ff f5       	.word 0xfff5
ff f6       	.word 0xfff6
ff f7       	.word 0xfff7
ff f8       	.word 0xfff8
ff f9       	.word 0xfff9
ff fa       	.word 0xfffa
ff fb       	.word 0xfffb
ff fc       	.word 0xfffc
ff fd 50    	bugl r0
ff fe 50    	bugw r0
ff ff       	.word 0xffff
