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

package ec.algorithms.alps.system;

import ec.individuals.populations.Population;

import java.util.ArrayList;
import java.util.Properties;

import ec.main.EC;
import ec.parameter.Instance;
import ec.exceptions.InitializationException;
import ec.util.Constants;
import ec.algorithms.alps.layers.Layer;
import ec.algorithms.alps.replacement.ALPSReplacementStrategy;

/**
 * 
 * @author Anthony Awuley
 *
 */
public class Engine extends Instance implements EC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** */
	private int ageGap;
	/** */
	public static int numberOfLayers;
	/** */
	private Properties properties;
	/** */
	public static int completeGenerationalCount = 1;
	/** */
	public static int completeEvaluationCount = 0;
	/** */
	public static int evaluations;
	/** */
	public int number_of_experiments;
	/** */
	public static long seed;

	// protected Instance instance;

	public Engine() {
	}

	public void setup(Properties p) {
		this.properties = p;
		/* */
		this.ageGap = Integer.parseInt(p.getProperty(Constants.ALPS_AGE_GAP));
		/* */
		Engine.numberOfLayers = Integer.parseInt(p
				.getProperty(Constants.ALPS_NUMBER_OF_LAYERS));
    	/* */
    	Engine.seed                  = Long.parseLong(p.getProperty(Constants.SEED));
		
		/*
		 * try { start(p); } catch (InitializationException e) {
		 * System.out.println("Parameter instance could not be created \n"+
		 * e.getMessage()); e.printStackTrace(); }
		 */

	}

	@Override
	public void start(Properties p) throws InitializationException {
		
		/* */
    	this.number_of_experiments = Integer.parseInt(p.getProperty(Constants.NUMBER_OF_EXPERIMENTS));
		
		for (int i = 0; i < number_of_experiments; i++) {
		
			setup(p);
			
			System.out.println("\nInitializing population for Run # " + i
					+ "\n");

			ArrayList<Layer> layers = agingLayers(p).agingScheme(this.ageGap,
					Engine.numberOfLayers);

			System.out
					.println("-----------------CONFIGURING ALPS-----------------");

			/* add a GA to each layer */
			for (Layer l : layers) {
				System.out.println("Setting up evolutionary system for layer #"
						+ l.getId());
				System.out.println("Maximum Age #" + l.getMaxAge());
				System.out.println("Number of Generations #"
						+ l.getGenerations());
				System.out.println("");

				l.setEvolution(new EvolveALPS(p));

				if (!l.getIsBottomLayer())
					l.initializerFlag = Boolean.FALSE;

				/* initialize generational count */
				l.setParameters(l.getEvolution().getParameters());
				/* l.getEvolution().getParameters().setGenerationsEvolved(0) */
				l.getParameters().setGenerationsEvolved(0);
				l.getParameters().setSeed(Engine.seed); // set seed
				/* l.getParameters().setSeed(l.getParameters().getSeed()+l.getId()+1); //set seed*/
				/* fill with empty population */
				l.getEvolution().setCurrentPopulation(new Population());
			}

			/* number of evaluations to observe */
			if(layers.get(0).getEvolution().getParameters().getEvaluations()==layers.get(0).getEvolution().getParameters().getPopulationSize()
					&& layers.get(0).getEvolution().getParameters().getGenerations()==1){
				Engine.evaluations =layers.get(0).getEvolution().getParameters().getPopulationSize();
			}
			else if(layers.get(0).getEvolution().getParameters().getEvaluations()>layers.get(0).getEvolution().getParameters().getPopulationSize()){
				Engine.evaluations = (int) layers.get(0).getEvolution().getParameters().getEvaluations();
			}
			else if((layers.get(0).getEvolution().getParameters().getGenerations() == 1) 
					&& layers.get(0).getEvolution().getParameters().getEvaluations() > layers.get(0).getEvolution().getParameters().getPopulationSize()){
				Engine.evaluations = (int) layers.get(0).getEvolution().getParameters().getEvaluations();
			}
			else {
				Engine.evaluations = numberOfLayers * layers.get(0).getEvolution().getParameters()
						.getGenerations() * layers.get(0).getEvolution().getParameters().getPopulationSize();
			}
            
			// print replacement strategy
			System.out.println("\n------------------------"
					+ replacementOperation(this.properties).toString()
					+ " ALPS STARTED" + "------------------------\n");
			layeredEvolutionALPS(layers,i);
			/* increment seed */
			Engine.seed++;
			
			
			unsetVariables();
			
		}

	}

	/**
	 * 
	 */
	public void unsetVariables(){
		Engine.completeEvaluationCount  = 0;
		Engine.completeGenerationalCount= 1;
	}
	
	/**
	 * 
	 * @param layers
	 * @param run
	 */
	public void layeredEvolutionALPS(ArrayList<Layer> layers,int run) {
		ALPSLayers alps = new ALPSLayers(layers, 0);
		/* keep running till stopped */
		/* Engine.completeGenerationalCount<=layers.get(0).getParameters().getEvaluations ()*/
		while (Engine.completeEvaluationCount <= Engine.evaluations)
		{
			sequentialLayerSelection(alps, layers,run);

			Engine.completeGenerationalCount++;
			// Engine.completeEvaluationCount +=
			// layers.get(0).getParameters().getPopulationSize(); //all layers
			// have the same default population size
		}

	}

	/**
	 * sequentially loop through all layers
	 */
	public void sequentialLayerSelection(ALPSLayers alps,
			ArrayList<Layer> layers,int run) {

		for (int j = layers.size() - 1; j >= 0; j--) {
			alps.index = j; // only modify the index

			if (layers.get(j).getIsBottomLayer()) // set initializer flag to
													// true when bottom layer is
													// called
			{
				if ((layers.get(j).layerEvaluationCount == 0)
						|| (layers.get(j).getEvolution().getCurrentPopulation()
								.size() > 0) || layers.get(j).initializerFlag)
					layers.get(j).getEvolution().start(alps,run); // good

				if ((Engine.completeGenerationalCount
						% layers.get(0).getMaxAge() == 0)) {
					layers.get(j).initializerFlag = true;
					layers.get(j).layerEvaluationCount = 0;
				}
			} else if ((layers.get(j).getEvolution().getCurrentPopulation()
					.size() > 0)
					&& // remove if problematic
					Engine.completeGenerationalCount > layers.get(j - 1)
							.getMaxAge()) {
				/*
				 * Generational worked without this condition - was put here
				 * because of SteadyState -- remove if problematic
				 */
				layers.get(j).getEvolution().start(alps,run); // good

				if ((layers.get(j).layerEvaluationCount % layers.get(j)
						.getGenerations()) == 0)
					layers.get(j).layerEvaluationCount = 0;
			}
		}

	}

	/**
	 * Selects layers randomly using equall probability
	 * 
	 * @param alps
	 * @param layers
	 *            TODO
	 */
	public void randomLayerSelection(ALPSLayers alps, ArrayList<Layer> layers) {

	}

	/**
	 * 
	 * @param layers
	 * @param point
	 * @deprecated
	 */
	@Deprecated
	public void runner(ArrayList<Layer> layers, int point,int run) {
		// cycle through the layers starting from the back
		for (int i = point; i >= 0; i--) {
			System.out.println("---------------------------------------"
					+ "\nEvolution started in layer #" + layers.get(i).getId());
			// assign the most current evaluation of the highest layer to
			// layer#0
			/*
			 * if(i==0) { layers.get(0).setEvaluationCounter(layers.get(point).
			 * getEvaluationCounter()); }
			 */
			layers.get(i).getEvolution().start(new ALPSLayers(layers, i),run); // good
		}

		if ((point + 1) < layers.size()) // dont perform movement if evolution
											// has reached the last layer
		{
			try {
				layers = moveLayerUp(layers, point + 1);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param layers
	 * @param evolvedLayerIncrement
	 * @return
	 * @throws CloneNotSupportedException
	 * @deprecated
	 */
	@Deprecated
	public ArrayList<Layer> moveLayerUp(ArrayList<Layer> layers,
			int evolvedLayerIncrement) throws CloneNotSupportedException {
		ALPSReplacementStrategy ars = new ALPSReplacementStrategy();
		// push upwards to the next higher layer
		for (int i = evolvedLayerIncrement; i > 0; i--) {
			System.out.println("Moving population from layer #"
					+ layers.get(i - 1).getId() + " to layer #"
					+ layers.get(i).getId());
			layers.get(i).initializerFlag = true;
			layers.get(i - 1).initializerFlag = true;

			// second implementation
			// ars.interLayerMigrations(layers,layers.get(i-1).getEvolution().getCurrentPopulation(),i);

			ars.completeLayerMigrations(layers, i);

			/*
			 * first implementation
			 * layers.get(i).getEvolution().setCurrentPopulation( (Population)
			 * layers.get(i-1).getEvolution().getCurrentPopulation());
			 * 
			 * global value preferred layers.get(i).setEvaluationCounter(
			 * layers.get(i-1).getEvaluationCounter());
			 * 
			 * clear previous layer and prepare for replacement of population
			 * layers.get(i-1).getEvolution().setCurrentPopulation(new
			 * Population());
			 */
		}

		return layers;
	}

}
