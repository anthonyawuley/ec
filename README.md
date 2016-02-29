Welcome Evolutionary Computation in Java!
================
The system contains a number of evolutionary algorithms developed in Java. This includes Genetic Algorithms, Ant Colony Optimization, Simulated Annealing, Tabu Search and Age Layered Population Structure.

____

## Start here for GA
The parameter files are located in the directory  io/params/ and the main class is in ec.main.Run.
Three tutorials have been set up and can be found in the directories

1. io/params/tutorial1-ga-tsp/ 
2. io/params/tutorial1-ant-tsp/ 
3. io/params/tutorial1-alps-tsp/ 

A brief introduction is given to setting up **1** and **2**.

## Setting up GA TSP

#### GENERAL parameters
* number-of-experiments                 = 2
* generations                           = 1000
* population-size                       = 200
* stop-when-solved                      = true
* crossover-probability                 = 0.95
* mutation-probability                  = 0.05
* tournament-selection-pressure         = 0.9
* seed                                  = 20
* stop-when-solved                      = true

#### SELECTION Operator
* selection-operation                   = ec.operator.operations.selection.TournamentSelection
* tournament-size                       = 3
 
#### REPRODUCTION operators

Probability of crossover. State if the point of crossover is fixed on both chromosomes (The size will always be the same) or not

* mutation_operation                    = ec.operator.operations.IntFlipMutation
* elite-size                            = 2
 
 
The chromosomes are fixed length structures and doesnt change to mutation and crossover. The initialiser will random create integer strings in this case of length specified
* initial-chromosome-size                = 280
  
#### FITNESS function - Fitness function requires the full package name
fitness-function                        = ec.fitnessevaluation.eval.TSP
 
### EC Main class. This directs the system to the main evolving class. In this case the GA evolution
* main-class                           = ec.algorithms.ga.Evolve

#### INITIALISATION - Specifies which module to use as an initialiser initial population. 
* initialiser                          = ec.operator.initialiser.Initialise
* gene-representation                  = ec.individuals.representation.VRPTW **deprecated**

#### REPLACEMENT
* replacement-operation                = ec.operator.operations.replacement.Generational

#### CROSSOVER MODULE
* crossover-operation                  = ec.operator.crossover.CycleCrossover
* #or
* crossover-operation                  = ec.operator.crossover.UniformOrderCrossover

#### MUTATION MODULE
* mutation-operation                   = ec.operator.mutation.Inversion **well tested**
* #or
* mutation-operation                   = ec.operator.mutation.ReciprocalExchange

### Specification of output statistics files
* number-of-individuals                = 2
* stats-operation                      = ec.util.statistics.singleobjective.TSPStatistics

### Nodes Information
* nodes                                = 280
* start-node                           = 1
* #statistics prefix
* stat-out                             = a280


### Data File
* #NAME : a280
* #COMMENT : drilling problem (Ludwig)
* #TYPE : TSP
* #DIMENSION: 280
* #EDGE WEIGHT TYPE : EUC 2D
* 1 288 149
* 2 288 129
* 3 270 133
* 4 256 141
* 5 256 157
* 6 246 157
* 7 236 169
* 8 228 169
* - - - 
* 280 280 133

____


## Start here for ALPS GA

This are additional parameters that will be needed for setting up ALPS

### GENERAL parameters [Use number of generations or number of evaluations]
* generations                           = 1000
* evaluations                           = 1000000
* #population size per layer
* population-size                       = 50

### ALPS configuration
* alps-age-gap                          = 10
* alps-number-of-layers                 = 5
* alps-selection-pressure               = 0.8
* alps-ss-selection-pressure            = 1.0
* alps-aging-scheme                    = ec.algorithms.alps.agingscheme.Polynomial
* #or
* alps-aging-scheme                    = ec.algorithms.alps.agingscheme.exponential
* #or
* alps-aging-scheme                     = ec.algorithms.alps.agingscheme.Linear
* #or
* alps-aging-scheme                    = ec.algorithms.alps.agingscheme.Fibonacci

### LAYER Replacement Strategy
* alps-replacement-strategy            = ec.algorithms.alps.replacement.age.Worst
* #or
* alps-replacement-strategy            = ec.algorithms.alps.replacement.age.Nearest
* #or
* alps-replacement-strategy             = ec.algorithms.alps.replacement.age.ReverseTournamentWorst
* #or
* alps-replacement-strategy             = ec.algorithms.alps.replacement.age.ReverseTournamentNearest **prefered**
* #or
* alps-replacement-strategy             = ec.algorithms.alps.replacement.age.Random


The chromosomes are fixed length structures and doesnt change to mutation and crossover. The initialiser will random create integer strings in this case of length specified
initial-chromosome-size                = 280
  
### EC Main Class
* main-class                           = ec.algorithms.alps.system.Engine

### REPLACEMENT Strategy
* replacement-operation                = ec.operator.operations.replacement.Generational
* #or
* replacement-operation                = ec.operator.operations.replacement.SteadyState

When using Steady State Replacement, additional paraemters such as these will be needed

* steady-state-replacement             = ec.operator.operations.replacement.steadystate.ReverseTournamentWorst **prefered**
* #or
* steady-state-replacement              = ec.operator.operations.replacement.steadystate.Worst
* #or
* steady-state-replacement              = ec.operator.operations.replacement.steadystate.Nearest
* #or
* steady-state-replacement              = ec.operator.operations.replacement.steadystate.ReverseTournamentNearest
* #or
* steady-state-replacement              = ec.operator.operations.replacement.steadystate.Random
 

____

## SAMPLE OUTPUT

ALPS using Generational Replacement using 10 layers
<img src="http://greyintel.org/resources/img/works/alps/alps-gen copy.png" height="423" width="719" alt="ALPS using Generational Replacement" />

================

ALPS  Steady State Replacement using 10 layers
<img src="http://greyintel.org/resources/img/works/alps/alps-ss2 copy.png" height="423" width="719" alt="ALPS  Steady State Replacement" />

================
