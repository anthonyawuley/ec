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
package algorithms.ga;

import individuals.fitnesspackage.PopulationFitness;
import individuals.populations.Population;
import island.IslandModel;

import java.util.ArrayList;
import java.util.Properties;

import main.Start;
import operator.InitialisationModule;
import operator.operations.ReplacementStrategy;
import operator.operations.StoppingCondition;
import parameter.Instance;
import util.Constants;
import exceptions.InitializationException;
import exceptions.OutOfRangeException;
import fitnessevaluation.FitnessExtension;

/**
 *
 * @author anthony
 */
public class Evolve extends Instance{
    
    private int generationsEvolved;
    private int chromosomeLength;
    private int generations;
    private int populationSize;
    private double crossoverRate;
    private double mutationRate;
    private int tournamentSize;
    private boolean stopFlag;
    private Properties prop;
    private int number_of_experiments;
    private int elitismSize;
    private double selectionPressure;
    @SuppressWarnings("unused")
	private IslandModel im = null;
    
    /**
     * start node for customers or user nodes
     */
    public int startNode = 1;
    /**
     * node used for depot when performing VRP
     */
    public int depotNode = 1; 
    
    
    public Evolve()
    {
    }
    
   /**
    * 
    * @param crossoverRate
    * @param mutationRate
    * @throws OutOfRangeException 
    */
    
    public Evolve(Properties properties) throws InitializationException
    {
    	/*
         * SET SYSTEM PARAMETERS
         */
    	number_of_experiments = Integer.parseInt(properties.getProperty(Constants.NUMBER_OF_EXPERIMENTS));
        populationSize        = Integer.parseInt(properties.getProperty(Constants.POPULATION_SIZE));
        chromosomeLength      = Integer.parseInt(properties.getProperty(Constants.INITIAL_CHROMOSOME_SIZE));
        crossoverRate         = Double.parseDouble(properties.getProperty(Constants.CROSSOVER_PROBABILITY));
        mutationRate          = Double.parseDouble(properties.getProperty(Constants.MUTATION_PROBABILITY));
        generations           = Integer.parseInt(properties.getProperty(Constants.GENERATIONS));
        tournamentSize        = Integer.parseInt(properties.getProperty(Constants.TOURNAMENT_SIZE));
        stopFlag              = Boolean.parseBoolean(properties.getProperty(Constants.STOP_WHEN_SOLVED));
        elitismSize           = Integer.parseInt(properties.getProperty(Constants.ELITE_SIZE));
        selectionPressure     = Double.parseDouble(properties.getProperty(Constants.TOURNAMENT_SELECTION_PRESSURE));
                   
        
        startNode             = Integer.parseInt(properties.getProperty(Constants.START_NODE));
        depotNode             = Integer.parseInt(properties.getProperty(Constants.DEPOT_NODE));
        
        
        prop = properties;
        
        
        if (crossoverRate < 0 || crossoverRate > 1) 
            throw new InitializationException(crossoverRate, 0, 1);
        if (mutationRate < 0 || mutationRate > 1) 
            throw new InitializationException(mutationRate, 0, 1);
        if (tournamentSize == 0 || tournamentSize > 5) 
            throw new InitializationException(mutationRate, 1, 5);
        if (selectionPressure  < 0 || selectionPressure  > 1) 
            throw new InitializationException(selectionPressure, 0, 1);
        
        
        //set other constraints that could run system into fail
        
       
        /*
         * force test pareto & sor
       
        ArrayList<String> ranks = new ArrayList<>();
        ArrayList<Integer> ranksAlt = new ArrayList<>();
        
        ranks.add("9,500");
        ranks.add("2,1000");
        ranks.add("4,600");
        ranks.add("8,400");
        ranks.add("7,800");
        ranks.add("7,800");
        ranksAlt.add(21000);
        ranksAlt.add(4600);
        ranksAlt.add(9500);
        ranksAlt.add(8400);
        ranksAlt.add(7800);
       
        ranks.add("2,4");
        ranks.add("2,10");
        ranks.add("3,4");
        ranks.add("4,3");
        ranks.add("5,10"); 
        ranksAlt.add(24);
        ranksAlt.add(210);
        ranksAlt.add(34);
        ranksAlt.add(43);
        ranksAlt.add(510);
        
        ParetoRanking prank = new ParetoRanking();
        prank.paretoCalculations(ranks,ranks.size());
        //SumOfRanks prank = new SumOfRanks();
        //prank.sorCalculations(ranks,ranks.size());
        
        System.exit(0);
        */
        
        
        
        /**
         * BEGIN Island
         * start Server/Client processes
         
    	if(generationsEvolved == 0)
    	{
    		im = new IslandModel(prop);
    	}
        */
        
        start();
      
        
    }
   
    /*
     * begin evolutionary process, by initiation number of experiments (runs)
     */
    public void start()
    {
    	for(int i=0;i<number_of_experiments;i++)
        {
          System.out.println("\nInitializing population for Run # "+i +"\n");
         /*
       	  * set initialiser module
       	  */
       	  InitialisationModule init = initialiserModule(prop);
       	/*
       	  evolve(
       			init.generateInitialPopulation(
       					geneRepresentation(prop),
       					prop,populationSize,
       					chromosomeLength), 
       			new StoppingCondition(stopFlag),
       			i);
       	 */
       	  
       	 evolve(
       			 init.generateInitialPopulation(
       					    prop,
        					populationSize,
        					chromosomeLength), 
        			        new StoppingCondition(stopFlag),
        			        i);
       	 
       	 //previous generateInitialPopulation(i);
        }
    }
    
    
    /**
     * Returns the number of generations evolved so far in the last run.
     *
     * @return number of generations evolved
     */
    public int getGenerationsEvolved() 
    {
        return generationsEvolved;
    }
    
    
    /**
     * 
     * @param properties
     * @param run
     * @deprecated
     */
    public  void generateInitialPopulation(int run) 
    {
    	/*
    	 * set initialiser module
    	 */
    	InitialisationModule init = initialiserModule(prop);
    	
       /*
        * begin evolve
        * return last population
        */
        //evolve(pop,new StoppingCondition(stopFlag),run);
    	//new Initialise().generateInitialPopulation(run, run)
    	evolve(
    			init.generateInitialPopulation(
    					geneRepresentation(prop),prop,
    					populationSize,chromosomeLength),
    			new StoppingCondition(stopFlag),
    			run);
    	
    }
    
    
    
    /**
     * 
     * @param initial
     * @param condition
     * @param run keep count of run number
     * @return - this returns last two generations. could be configured to return entire population of all generations
     */
    public ArrayList<Population> evolve(
    		final Population initial, 
    		final StoppingCondition condition, 
    		final int run) 
    {
        
        Population current;  // = initial;
        
        ArrayList<Population> generationalPopulation = new ArrayList<>();
        generationsEvolved = 0;
        
        PopulationFitness fitnessFunction = fitnessEvaluator(prop);
        ((FitnessExtension) fitnessFunction).setProperties(prop); //set properties file for report generation
        
        //set initial population
        //Pop 0
        generationalPopulation.add(generationsEvolved,initial); 
        //System.out.println(initial.getAll().size()); System.exit(0);
        while (!condition.generationCount(generationsEvolved, generations)) //false is returned when generations are not equal 
        {
            /**
             * BEGIN Island
             * start Server/Client processes
             
        	
        	if(im.getIsServer())
        	{  
        		//System.out.println("I am a server!!! Hurayy!!!"+im.getServers().get(0).priorityKey);
        		//im.getServers().get(1).priorityKey = 1;
        		im.serverMigratePacketToClient(
        				im.getServers(),generationsEvolved);
        	}
        	if(im.getIsClient())
        	{ 
        		//System.out.println("I am a client!!! Hurayy!!!");
        		 Client client = im.getClient();
                 if((generationsEvolved % client.getUpdateFrequecy())==0)
                 {
                   im.txClientPackets(client,generationalPopulation.get(generationsEvolved));
                 }
                 
                 //try to recieve
                 client.priorityKey=2; //listen
                 if(im.rxClientPopulation(client).size()>0)
                 {
                   Population rx = new Population();
                   rx = im.rxClientPopulation(client);
                   System.out.println("Anthony awuley came here to do a couple of things");
                   System.exit(0);
                 }
        		 
        	}
        	*/
        	//END island
            
            System.out.println("Generation #"+ generationsEvolved);
            generationsEvolved++;
            
            //replacement strategy 
            ReplacementStrategy replacment = replacementOperation(prop);
           
            //perform generational replacement
            current = replacment.nextGeneration(
            		                  fitnessFunction,  
            		                  crossoverOperation(prop),
            		                  mutationOperation(prop),
            		                  selectionOperator(prop),
            		                  statisticsOperation(prop),
            		                  prop,
                                      generationalPopulation,
                                      generationsEvolved,
                                      run,
                                      crossoverRate, 
                                      mutationRate,
                                      elitismSize,
                                      tournamentSize,
                                      selectionPressure); 
            
            /*
             * unset previous 2 generations to free memory
             * keep only previous and current
             */
            if(generationsEvolved > 1)
            {
               generationalPopulation.set(generationsEvolved-2, new Population());
            }
            //System.out.println("#SIZE"+current.size());
            generationalPopulation.add(generationsEvolved,current);
        }
        return generationalPopulation;
    }

}
