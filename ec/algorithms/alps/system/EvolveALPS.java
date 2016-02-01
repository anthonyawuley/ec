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
package ec.algorithms.alps.system;

import ec.individuals.fitnesspackage.PopulationFitness;
import ec.individuals.populations.Population;

import java.util.ArrayList;
import java.util.Properties;

import ec.algorithms.alps.layers.InitializeParams;
import ec.algorithms.alps.layers.Layer;
import ec.algorithms.ga.Evolve;
import ec.operator.InitialisationModule;
import ec.operator.operations.ReplacementStrategy;
import ec.operator.operations.StoppingCondition;
import ec.util.Constants;
import ec.util.random.MersenneTwisterFast;
import ec.exceptions.InitializationException;
import ec.exceptions.OutOfRangeException;
import ec.fitnessevaluation.FitnessExtension;

/**
 *
 * @author Anthony Awuley
 */
public class EvolveALPS extends Evolve{

	/** */
	private int ageGap, ageLayers;
	/** */
	private InitializeParams init;
	/** */
	private Population currentPopulation;
	/** */
	private double layerSelectionPressure;
	/** */
	private double evaluations;
	/** */
	private double alpsSSSelectionPressure;
	/** */
	private String replacementOperator;


	/**
	 * 
	 * @param crossoverRate
	 * @param mutationRate
	 * @throws OutOfRangeException 
	 */

	public EvolveALPS(Properties properties) throws InitializationException
	{
		/*
		 * SET SYSTEM PARAMETERS
		 */
		this.number_of_experiments = Integer.parseInt(properties.getProperty(Constants.NUMBER_OF_EXPERIMENTS));
		this.populationSize        = Integer.parseInt(properties.getProperty(Constants.POPULATION_SIZE));
		this.chromosomeLength      = Integer.parseInt(properties.getProperty(Constants.INITIAL_CHROMOSOME_SIZE));
		this.crossoverRate         = Double.parseDouble(properties.getProperty(Constants.CROSSOVER_PROBABILITY));
		this.mutationRate          = Double.parseDouble(properties.getProperty(Constants.MUTATION_PROBABILITY));
		/*set generations to popsize when its null*/
		this.generations           = Integer.parseInt(properties.getProperty(Constants.GENERATIONS,1+""));
		this.tournamentSize        = Integer.parseInt(properties.getProperty(Constants.TOURNAMENT_SIZE));
		this.stopFlag              = Boolean.parseBoolean(properties.getProperty(Constants.STOP_WHEN_SOLVED));
		this.elitismSize           = Integer.parseInt(properties.getProperty(Constants.ELITE_SIZE));
		this.selectionPressure     = Double.parseDouble(properties.getProperty(Constants.TOURNAMENT_SELECTION_PRESSURE));
		this.replacementOperator   = properties.getProperty(Constants.REPLACEMENT_OPERATION);
		this.layerSelectionPressure= Double.parseDouble(properties.getProperty(Constants.ALPS_SELECTION_PRESSURE));

		this.ageGap                = Integer.parseInt(properties.getProperty(Constants.ALPS_AGE_GAP));
		this.ageLayers             = Integer.parseInt(properties.getProperty(Constants.ALPS_NUMBER_OF_LAYERS));
		
		/*default evaluations pop size when its empty */
		this.evaluations           = Long.parseLong(properties.getProperty(Constants.EVALUATIONS,this.populationSize+""));
		this.alpsSSSelectionPressure          = Double.parseDouble(properties.getProperty(Constants.ALPS_SS_SELECTION_PRESSURE));
		
		random                = new MersenneTwisterFast();
        random.setSeed(Engine.seed); //set seed
        
		this.properties = properties;
		init = new InitializeParams();
		init.setNumberOfExperiments(number_of_experiments);
		init.setReplacementOperator(replacementOperator);
		init.setPopulationSize(populationSize);
		init.setChromosomeLength(chromosomeLength);
		init.setCrossoverRate(crossoverRate);
		init.setMutationRate(mutationRate);
		init.setGenerations(generations);
		init.setTournamentSize(tournamentSize);
		init.setStopFlag(stopFlag);
		init.setElitismSize(elitismSize);
		init.setSelectionPressure(selectionPressure);
		init.setMaxAgeLayers(ageLayers);
		init.setAgeGap(ageGap);
		init.setSeed(seed);
		init.setLayerSelectionPressure(layerSelectionPressure);
		init.setSSSelectionPressure(this.alpsSSSelectionPressure);
		init.setEvaluations(evaluations);

	}

	public InitializeParams getParameters()
	{
		return this.init;
	}


	/**
	 * Returns the number of generations evolved so far in the last run.
	 *
	 * @return number of generations evolved
	 */
	@Override
	public int getGenerationsEvolved() 
	{
		return generationsEvolved;
	}

	/**
	 * 
	 * @return
	 */
	public Population generateRandomPopulation(Layer layer)
	{
		InitialisationModule init = initialiserModule(this.properties);
		layer.layerEvaluationCount =0; //reinitialize counter when evolution starts
		
		return init.generateInitialPopulation(
				this,Engine.completeEvaluationCount
				); //set evaluations as this.populationSize * getGenerationsEvolved()
		/*
		return init.generateInitialPopulation(
				this,
				geneRepresentation(this.prop),
				//layer.layerEvaluationCount 
				Engine.completeEvaluationCount
				); //set evaluations as this.populationSize * getGenerationsEvolved()
				*/
	}

	/**
	 * 
	 * @param alpsLayers
	 * @param run
	 */
	public  void start(ALPSLayers alpsLayers, int run) 
	{
         
		//set parameters for layer
		alpsLayers.layers.get(alpsLayers.index).setParameters(this.getParameters());

		//System.out.println("generations... getting parameters"+ this.getParameters().getAgeGap());
		if(alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer() && 
				alpsLayers.layers.get(alpsLayers.index).initializerFlag) //bottom layer - new individuals will be generated
		{
			alpsLayers.layers.get(0).
			getEvolution().setCurrentPopulation(
					generateRandomPopulation(alpsLayers.layers.get(alpsLayers.index)));
			
			this.evolve(
					alpsLayers.layers.get(0).getEvolution().getCurrentPopulation(),
					new StoppingCondition(this.stopFlag),
					alpsLayers,run);   
		
		}
		else
		{
			//get population of current layer
			//Population layerPop = alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation();

			/* 
			 * add previous and current population
			 * if its layer 1, do not bother adding layer#0 because its empty
			 * if this is uncommented, layer one will have incorrect individuals (age)


    		layerPop.addAll(alpsLayers.layers.get(alpsLayers.index-1).getEvolution().getCurrentPopulation().getAll());
    		System.out.println("my size "+ layerPop.size());
    		this.evolve(layerPop,
        			    new StoppingCondition(this.stopFlag),
        			    alpsLayers);
			 */

			this.evolve(
					alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation(),
					new StoppingCondition(this.stopFlag),
					alpsLayers,run);  

		}
	}


	/**
	 * 
	 * @return currentPopulation
	 */
	public Population getCurrentPopulation()
	{
		return this.currentPopulation;
	}

	/**
	 * 
	 * @param pop
	 */
	public void setCurrentPopulation(Population pop)
	{   
		this.currentPopulation = pop;
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
			final ALPSLayers alpsLayers, final int run) 
			{

		Population current;             // = initial;
		ArrayList<Population> generationalPopulation = new ArrayList<>();
		this.generationsEvolved = 0;

		PopulationFitness fitnessFunction = fitnessEvaluator(this.properties);
		((FitnessExtension) fitnessFunction).setProperties(this.properties); //set properties file for report generation

		//set initial population - Pop 0
		generationalPopulation.add(this.generationsEvolved,initial); 

		/* 
		 * System.out.println("Layer #"+ alpsLayers.layers.get(alpsLayers.index).getId()+
		 *		             " Generation #"+ this.generationsEvolved);
		 */
		this.generationsEvolved++;
		alpsLayers.layers.get(alpsLayers.index).layerEvaluationCount++;
		alpsLayers.layers.get(alpsLayers.index).layerCompleteGenerationCount++;
		//Engine.completeGenerationalCount++ ;
		//Engine.completeEvaluationCount += alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize();

		//increment number of generations evolved so far in a layer
		alpsLayers.layers.get(alpsLayers.index).getParameters().setGenerationsEvolved(
				alpsLayers.layers.get(alpsLayers.index).getParameters().getGenerationsEvolved()+1);
		//replacement strategy 
		ReplacementStrategy replacment = replacementOperation(this.properties);
		current = replacment.nextGenerationALPS(
				this,
				fitnessFunction,  
				crossoverOperation(this.properties),
				mutationOperation(this.properties),
				selectionOperator(this.properties),
				statisticsOperation(this.properties),
				replacementStrategyALPS(this.properties),
				generationalPopulation,
				alpsLayers,
				alpsLayers.layers.get(alpsLayers.index).layerEvaluationCount,
				run
				); 

		/*
		 * set an alternate stopping flag based on aged individuals in a population
		 * prevent reinitialization when total number of observable generations have not been completed
		 * in the bottom layer
		 * 
		 * Set system into reinitialization mode if bottom layer has fewer than 1 individual
		 * 
		 */
		if(alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer() || current.size()<1 )
			alpsLayers.layers.get(alpsLayers.index).initializerFlag = false;
		if(current.size()==0 )
			alpsLayers.layers.get(alpsLayers.index).initializerFlag = true;

		/*
		 * Onset previous 2 generations to free memory
		 * keep only previous and current
		 */
		if(this.generationsEvolved > 1){
			generationalPopulation.set(this.generationsEvolved-2, new Population());
			generationalPopulation.get(this.generationsEvolved-2).clear();
		}

		//System.out.println("#SIZE"+current.size());
		generationalPopulation.add(this.generationsEvolved,current);
		alpsLayers.layers.get(alpsLayers.index).setGenerationalCount(this.generationsEvolved); //set generation count for this layer
		/*
		 * keep record of current population
		 * layer.setPopulation(current);//set population for layer
		 */
		this.setCurrentPopulation(current);
		
		Engine.completeEvaluationCount += current.size(); //increment evaluations

		return generationalPopulation;
     }

}
