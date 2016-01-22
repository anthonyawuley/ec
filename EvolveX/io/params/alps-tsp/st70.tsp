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

nodes                                = 70
stat-out                             = st70

NAME: st70
TYPE: TSP
COMMENT: 70-city problem (Smith/Thompson)
DIMENSION: 70
EDGE_WEIGHT_TYPE : EUC_2D
NODE_COORD_SECTION
1 64 96
2 80 39
3 69 23
4 72 42
5 48 67
6 58 43
7 81 34
8 79 17
9 30 23
10 42 67
11 7 76
12 29 51
13 78 92
14 64 8
15 95 57
16 57 91
17 40 35
18 68 40
19 92 34
20 62 1
21 28 43
22 76 73
23 67 88
24 93 54
25 6 8
26 87 18
27 30 9
28 77 13
29 78 94
30 55 3
31 82 88
32 73 28
33 20 55
34 27 43
35 95 86
36 67 99
37 48 83
38 75 81
39 8 19
40 20 18
41 54 38
42 63 36
43 44 33
44 52 18
45 12 13
46 25 5
47 58 85
48 5 67
49 90 9
50 41 76
51 25 76
52 37 64
53 56 63
54 10 55
55 98 7
56 16 74
57 89 60
58 48 82
59 81 76
60 29 60
61 17 22
62 5 45
63 79 70
64 9 100
65 17 82
66 74 67
67 10 68
68 48 19
69 83 86
70 84 94
EOF
