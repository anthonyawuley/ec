/*******************************************************************************
 * Copyright (c) 2014, 2015 
 * Anthony Awuley - Brock University Computer Science Department
 * All rights reserved. This program and the accompanying materials
 * are made available under the Academic Free License version 3.0
 * which accompanies this distribution, and is available at
 * https://aawuley@bitbucket.org/aawuley/evolvex.git
 *
 * Contributors:
 *     ECJ                     MersenneTwister & MersenneTwisterFast (https://cs.gmu.edu/~eclab/projects/ecj)
 *     voidException      Tabu Search (http://voidException.weebly.com)
 *     Lucia Blondel       Simulated Anealing 
 *     
 *
 *        
 *******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;


/**
 * This class has all the constants.
 * The names for properties.
 */
public final class Constants {

    //Property file fields. Works as command line args as well
   
	public static final String ALGORITHM_SELECTOR = "algorithm-selector";
	
   //BEGIN ALPS   
   /**
	* describes how fast the ants are going to select their path
	*/
	public static final String ALPS_AGING_SCHEME                          = "alps-aging-scheme";
	/**
	 * describes how fast the ants are going to select their path
	 */
	public static final String ALPS_NUMBER_OF_LAYERS                      = "alps-number-of-layers";
	/**
     * when applying selection pressure to select between two adjacent layers
     * this value determines percentage of selection in tournament individuals 
     * from current layer and lower layer
     */
    public static final String ALPS_SELECTION_PRESSURE                    = "alps-selection-pressure";
    /**
     * when performing sequential selection, use this variable to control percengate
     * of call for stated replacement (e.g worst or nearest individual in tournament) as 
     * opposed to random replacement
     */
    public static final String ALPS_SS_SELECTION_PRESSURE                    = "alps-ss-selection-pressure";
	/**
	 * describes how fast the ants are going to select their path
	 */
	public static final String ALPS_AGE_GAP                               = "alps-age-gap";
	/**
	 * this determines how individual older than their current layer are moved 
	 * to the next higher layer
	 */
	public static final String ALPS_REPLACMENT_STRATEGY                   = "alps-replacement-strategy";
	/**
	 * there is no age-limit to the last layer so as to be able to keep the best 
	 * individuals from the most promising (longest running) search. 
	 * Thus with 6 age layers, a polynomial aging-scheme and an age gap of 20 
	 * the maximum ages for the layers are: 20, 40, 80, 180, 320 and infinity.
	 */
	public static final int ALPS_MAX_AGE_LAST_LAYER                       = 10000;
	//END ALPS
	
	
	
   //BEGIN Tabu
   /**
	* describes how fast the ants are going to select their path
	*/
    public static final int NUMBER_OF_ITERATIONS                          = 10000;
	
   //END Tabu
	
    
   //BEGIN aco params
   /**
	* heuristic parameter describing how greedy the algorithm is in finding its path across the graph
	*/
	public static final double ALPHA                                      = -0.2d;
   /**
    * describes how fast the ants are going to select their path
	*/
    public static final double BETA                                       = 9.6d;

    // heuristic parameters
    public static final double Q                                          = 0.0001d; // somewhere between 0 and 1
   /** 
	* phrromone resistance
	*/
	public static final double PHEROMONE_PERSISTENCE                      = 0.3d; // between 0 and 1
   /**
	* the initial pheromones on the edges
    * starting pheromone
	*/
	public static final double INITIAL_PHEROMONES                         = 0.8d; // can be anything

   /**
	* number of agents - number of workers  to search the best path
	*/
	public static final int NUM_AGENTS                                    = 1024 * 20;
   /**
	* number of processors
	*/
	public static final int POOL_SIZE                                     = Runtime.getRuntime().availableProcessors();
	   
    //END aco
	
	
	
    /**
     * output directory
     */
    public static final String DEFAULT_PARAM_ROOT                        = "params/outputs/";
    /**
     * 
     */
    public static final int MAXIMUM_EVALUATION_TIME                      = 12;
    /**
     * random number generation seed
     */
    public static final String SEED                                      = "seed";
    /**
     * 
     */
    public static final String STATS_FILE                                = "stat-out";
    /**
     * DURING SS evolution the system uses this replacement
     * strategy to replace individuals within evolving population
     */
    public static final String STEADY_STATE_REPLACEMENT                  = "steady-state-replacement";
    /**
     *@deprecated 
     */
    public static final String REPORT_TYPE                               = "tsp";
    /**
     * 
     */
    public static final String DEFAULT_STATS_EXTENSION                   = ".stats";
    /**
     * 
     */
    public static final String NUMBER_INDIVIDUALS_PRINT                  = "number-of-individuals";
    /**
     * 
     */ 
    public static final String CO_ORDINATES                              = "node";
    /**
     * 
     */ 
    public static final String NUMBER_OF_NODES                           = "nodes";    
    /**
     * 
     */ 
    public static final String VEHICLE_CAPACITY                          = "vehicle-capacity";
    
    /** depot node */
    public static final String DEPOT_NODE                                = "depot-node";
    
    /**determines where city node starts */
    public static final String START_NODE                                = "start-node";
    
    /**
     * Path to default properties file
     */
    public static final
        String DEFAULT_PROPERTIES                                        = DEFAULT_PARAM_ROOT
                                                                             + "Inputs/Tutorial1.params";
    
    public static final String NUMBER_OF_EXPERIMENTS                     =  "number-of-experiments";
    /**
     * 
     */
    public static final String IF_TSP_TRUE_IF_VRP_FALSE                  =  "tsp_vrp";
    /**
     * 
     */
    public static final String REPLACEMENT_OPERATION                     = "replacement-operation";
    /**
     * Class name of crossover operation. Reflection used to load
     */
    public static final String CROSSOVER_OPERATION                       = "crossover-operation";
    /**
     * Class name of main-class
     */
    public static final String MAIN_CLASS                                = "main-class";    
    /**
     * Class name of mutation operation. Reflection used to load
     */
    public static final String MUTATION_OPERATION                        = "mutation-operation";
    /**
     * Class name of mutation operation. Reflection used to load
     */  
    public static final String GENE_REPRESENTATION                       = "gene-representation";
    /**
     * Class name of fitness function. Reflection used to load
     */
    public static final String FITNESS_FUNCTION                          = "fitness-function";
    /**
     * Class name of selection operation. Reflection used to load
     */
    public static final String SELECTION_OPERATION                       = "selection-operation";
    /**
     * Path to properties file
     */
    public static final String PROPERTIES_FILE                           = "properties_file";
    /**
     * Class name of initialiser operation. Reflection used to load
     */
    public static final String INITIALISER                               = "initialiser";
    /**
     * Path for output file. Starts in user.dir
     * Output files are appended a timestamp and *.stats
     * If output is set to false no output is written
     */
    public static final String OUTPUT                                    = "output";
    /**
     * Number of iterations of algorithm
     */
    public static final String GENERATIONS                               = "generations";
    /**
     * Stop algorithm if global optimum is found before max iterations
     */
    public static final String STOP_WHEN_SOLVED                          = "stopWhenSolved";
    /**
     * 
     */
    public static final int DEFAULT_WORSE_FITNESS                        = 10000000;
    /**
     *@deprecated 
     */
    public static final int DEFAULT_WORSE_FITNESS_CARS                   = 1000;
    /**
     * Number of times the input is reread from start.
     */
    public static final String MAX_WRAPS                                 = "max-wraps";

    /**
     * Number of elites.
     */
    public static final String ELITE_SIZE                                = "elite-size";
    /**
     * Number of evaluations for SS Replacement.
     */
    public static final String EVALUATIONS                               = "evaluations";

    /**
     * If the elites should be evaluated each iteration
     */
    public static final String EVALUATE_ELITES                           = "evaluate-elites";

    /**
     * Length of input for random initialisation
     */
    public static final
        String INITIAL_CHROMOSOME_SIZE                                   = "initial-chromosome-size";

    /**
     * Default statistics operation
     */
    public static final
        String DEFAULT_STATS_OPERATION = "util.statistics.BasicStatistics";

    /**
     * stats collector
     */
    public static final
        String STATS_OPERATION = "stats-operation";

    
    /**
     * Selection size proportion of population used selected.
     * E.g 1.0 generates a selection of population size
     */
    public static final String SELECTION_SIZE = "selection-size";

    /**
     * Size of individual solutions
     */
    public static final String POPULATION_SIZE = "population-size";

    /**
     * Generational or steady state replacment. If mor control is needed use
     * the generation gap and selection size parameters.
     */
    public static final String REPLACEMENT_TYPE = "replacement-type";

    /**
     * If the crossover point is fixed
     */
    public static final String FIXED_POINT_CROSSOVER = "fixed-point-crossover";

    /**
     * Size of tournament for tournament selection
     */
    public static final String TOURNAMENT_SIZE = "tournament-size";
     /**
     * Methods for selecting max tree depth for max problem
     */
    public static final String MAXPROBLEM_DEPTH = "maxproblem-depth";

    /**
     * Interval between saved individuals in the population
     */
    public static final
        String INDIVIDUAL_CATCH_INTERVAL = "individual-catch-interval";

    /**
     * Probability of mutating an input
     */
    public static final String MUTATION_PROBABILITY = "mutation-probability";

    /**
     * Probability of crossing over inputs
     */
    public static final String CROSSOVER_PROBABILITY = "crossover-probability";

    /**
     * Probability of duplicating inputs
     */
    public static final
        String DUPLICATION_PROBABILITY = "duplication-probability";

    /**
     * Porportion of new solutions (population) that will be inserted among the
     *  old solutions (population)
     */
    public static final String GENERATION_GAP = "generation-gap";

    /**
     * Default population size
     */
    public static final String DEFAULT_POPULATION_SIZE = "10";
    /**
     * Default chromosome size
     */
    public static final String DEFAULT_CHROMOSOME_SIZE = "10";
  
    /**
     * Selection pressure indicate probability of 
     * selection based on fitness and random selection
     */
    public static final String TOURNAMENT_SELECTION_PRESSURE = "tournament-selection-pressure";
  


}
