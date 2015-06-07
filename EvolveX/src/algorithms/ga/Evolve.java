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
    	this.number_of_experiments = Integer.parseInt(properties.getProperty(Constants.NUMBER_OF_EXPERIMENTS));
        this.populationSize        = Integer.parseInt(properties.getProperty(Constants.POPULATION_SIZE));
        this.chromosomeLength      = Integer.parseInt(properties.getProperty(Constants.INITIAL_CHROMOSOME_SIZE));
        this.crossoverRate         = Double.parseDouble(properties.getProperty(Constants.CROSSOVER_PROBABILITY));
        this.mutationRate          = Double.parseDouble(properties.getProperty(Constants.MUTATION_PROBABILITY));
        this.generations           = Integer.parseInt(properties.getProperty(Constants.GENERATIONS));
        this.tournamentSize        = Integer.parseInt(properties.getProperty(Constants.TOURNAMENT_SIZE));
        this.stopFlag              = Boolean.parseBoolean(properties.getProperty(Constants.STOP_WHEN_SOLVED));
        this.elitismSize           = Integer.parseInt(properties.getProperty(Constants.ELITE_SIZE));
        this.selectionPressure     = Double.parseDouble(properties.getProperty(Constants.TOURNAMENT_SELECTION_PRESSURE));
        this.prop = properties;
        
        
        if (this.crossoverRate < 0 || this.crossoverRate > 1) 
        {
            throw new InitializationException(this.crossoverRate, 0, 1);
        }
        if (this.mutationRate < 0 || this.mutationRate > 1) 
        {
            throw new InitializationException(this.mutationRate, 0, 1);
        }
        if (this.tournamentSize == 0 || this.tournamentSize > 5) 
        {
            throw new InitializationException(this.mutationRate, 1, 5);
        }
        if (this.selectionPressure  < 0 || this.selectionPressure  > 1) 
        {
            throw new InitializationException(this.selectionPressure, 0, 1);
        }
        
        
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
         
    	if(this.generationsEvolved == 0)
    	{
    		im = new IslandModel(this.prop);
    	}
        */
        
        start();
      
        
    }
   
    /*
     * begin evolutionary process, by initiation number of experiments (runs)
     */
    public void start()
    {
    	for(int i=0;i<this.number_of_experiments;i++)
        {
          System.out.println("\nInitializing population for Run # "+i +"\n");
         /*
       	  * set initialiser module
       	  */
       	  InitialisationModule init = initialiserModule(this.prop);
       	/*
       	  this.evolve(
       			init.generateInitialPopulation(
       					geneRepresentation(this.prop),
       					this.prop,this.populationSize,
       					this.chromosomeLength), 
       			new StoppingCondition(this.stopFlag),
       			i);
       	 */
       	  
       	 this.evolve(
        			init.generateInitialPopulation(
        					this.prop,this.populationSize,
        					this.chromosomeLength), 
        			new StoppingCondition(this.stopFlag),
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
    	InitialisationModule init = initialiserModule(this.prop);
    	
       /*
        * begin evolve
        * return last population
        */
        //this.evolve(pop,new StoppingCondition(this.stopFlag),run);
    	//new Initialise().generateInitialPopulation(run, run)
    	this.evolve(
    			init.generateInitialPopulation(
    					geneRepresentation(this.prop),this.prop,
    					this.populationSize,this.chromosomeLength),
    			new StoppingCondition(this.stopFlag),
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
        this.generationsEvolved = 0;
        
        PopulationFitness fitnessFunction = fitnessEvaluator(this.prop);
        ((FitnessExtension) fitnessFunction).setProperties(this.prop); //set properties file for report generation
        
        //set initial population
        //Pop 0
        generationalPopulation.add(this.generationsEvolved,initial); 
        //System.out.println(initial.getAll().size()); System.exit(0);
        while (!condition.generationCount(this.generationsEvolved, this.generations)) //false is returned when generations are not equal 
        {
            /**
             * BEGIN Island
             * start Server/Client processes
             
        	
        	if(im.getIsServer())
        	{  
        		//System.out.println("I am a server!!! Hurayy!!!"+im.getServers().get(0).priorityKey);
        		//im.getServers().get(1).priorityKey = 1;
        		im.serverMigratePacketToClient(
        				im.getServers(),this.generationsEvolved);
        	}
        	if(im.getIsClient())
        	{ 
        		//System.out.println("I am a client!!! Hurayy!!!");
        		 Client client = im.getClient();
                 if((this.generationsEvolved % client.getUpdateFrequecy())==0)
                 {
                   im.txClientPackets(client,generationalPopulation.get(this.generationsEvolved));
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
            
            System.out.println("Generation #"+ this.generationsEvolved);
            this.generationsEvolved++;
            
            //replacement strategy 
            ReplacementStrategy replacment = replacementOperation(this.prop);
           
            //perform generational replacement
            current = replacment.nextGeneration(
            		                  fitnessFunction,  
            		                  crossoverOperation(this.prop),
            		                  mutationOperation(this.prop),
            		                  selectionOperator(this.prop),
            		                  statisticsOperation(this.prop),
            		                  this.prop,
                                      generationalPopulation,
                                      this.generationsEvolved,
                                      run,
                                      this.crossoverRate, 
                                      this.mutationRate,
                                      this.elitismSize,
                                      this.tournamentSize,
                                      this.selectionPressure); 
            
            /*
             * unset previous 2 generations to free memory
             * keep only previous and current
             */
            if(this.generationsEvolved > 1)
            {
               generationalPopulation.set(this.generationsEvolved-2, new Population());
            }
            //System.out.println("#SIZE"+current.size());
            generationalPopulation.add(this.generationsEvolved,current);
        }
        return generationalPopulation;
    }

}
