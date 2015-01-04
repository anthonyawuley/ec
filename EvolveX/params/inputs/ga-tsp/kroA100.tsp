# Copyright 2014 - Anthony Awuley
# Brock University
# Computer Science Department

# GENERAL parameters
number-of-experiments                 = 15
generations                           = 200
population-size                       = 2000
stop-when-solved                      = true
crossover-probability                 = 0.9
mutation-probability                  = 0.1
selection-pressure                    = 0.9

# How many times the genotype is allowed to wrap
max-repairs                           = 1

# SELECTION
selection-operation                   = operator.operations.selection.TournamentSelection
tournament-size                       = 3
 
# REPRODUCTION operators
# Probability of crossover. State if the point of crossover is fixed on both chromosomes (The size will always be the same) or not
mutation_operation                    = Operator.Operations.IntFlipMutation
elite-size                            = 5
 
 
# The chromosomes are fixed length structures and doesnt change to mutation and crossover. 
# The initialiser will random create integer strings in this case of length specified
initial-chromosome-size                = 280
  
# FITNESS function - Fitness function requires the full package name
#fitness-function                       = fitnessevaluation.singleObjective.WeightedSum
#fitness-function                       = fitnessevaluation.simplefitness.FitnessExperimental
#fitness-function                        = fitnessevaluation.vrptw.WS
#fitness-function                       = fitnessevaluation.vrptw.SOR
#fitness-function                        = fitnessevaluation.vrptw.PR
fitness-function                        = fitnessevaluation.eval.TSP
 
# EC
main-class                           = main.Run

# INITIALISATION - Specifies which module to use as an initialiser initial population. 
initialiser                          = operator.initialiser.Initialise
gene-representation                  = individuals.representation.VRPTW

# REPLACEMENT
replacement-operation                = operator.operations.replacement.Generational

# CROSSOVER MODULE
#crossover-operation                  = operator.crossover.CycleCrossover
crossover-operation                  = operator.crossover.UniformOrderCrossover

# MUTATION MODULE
mutation-operation                   = operator.mutation.Inversion
 

# Specification of output statistics files
number-of-individuals                = 2
#stats-operation                      = util.statistics.mop.ParethoStatistics
#stats-operation                      = util.statistics.mop.SumOfRankStatistics
#stats-operation                      = util.statistics.BasicStatistics
stats-operation                      = util.statistics.sob.TSPStatistics

nodes                                = 100
stat-out                             = kroA100

NAME: kroA100
TYPE: TSP
COMMENT: 100-city problem A (Krolak/Felts/Nelson)
DIMENSION: 100
EDGE_WEIGHT_TYPE : EUC_2D
NODE_COORD_SECTION
1 1380 939
2 2848 96
3 3510 1671
4 457 334
5 3888 666
6 984 965
7 2721 1482
8 1286 525
9 2716 1432
10 738 1325
11 1251 1832
12 2728 1698
13 3815 169
14 3683 1533
15 1247 1945
16 123 862
17 1234 1946
18 252 1240
19 611 673
20 2576 1676
21 928 1700
22 53 857
23 1807 1711
24 274 1420
25 2574 946
26 178 24
27 2678 1825
28 1795 962
29 3384 1498
30 3520 1079
31 1256 61
32 1424 1728
33 3913 192
34 3085 1528
35 2573 1969
36 463 1670
37 3875 598
38 298 1513
39 3479 821
40 2542 236
41 3955 1743
42 1323 280
43 3447 1830
44 2936 337
45 1621 1830
46 3373 1646
47 1393 1368
48 3874 1318
49 938 955
50 3022 474
51 2482 1183
52 3854 923
53 376 825
54 2519 135
55 2945 1622
56 953 268
57 2628 1479
58 2097 981
59 890 1846
60 2139 1806
61 2421 1007
62 2290 1810
63 1115 1052
64 2588 302
65 327 265
66 241 341
67 1917 687
68 2991 792
69 2573 599
70 19 674
71 3911 1673
72 872 1559
73 2863 558
74 929 1766
75 839 620
76 3893 102
77 2178 1619
78 3822 899
79 378 1048
80 1178 100
81 2599 901
82 3416 143
83 2961 1605
84 611 1384
85 3113 885
86 2597 1830
87 2586 1286
88 161 906
89 1429 134
90 742 1025
91 1625 1651
92 1187 706
93 1787 1009
94 22 987
95 3640 43
96 3756 882
97 776 392
98 1724 1642
99 198 1810
100 3950 1558
EOF
