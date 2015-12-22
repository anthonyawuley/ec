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

package algorithms.alps.system;

import individuals.populations.Population;

import java.util.ArrayList;
import java.util.Properties;

import parameter.Instance;
import exceptions.InitializationException;
import util.Constants;
import algorithms.alps.layers.Layer;
import algorithms.alps.replacement.ALPSReplacementStrategy;

public class Engine {

	private int ageGap;
	private int ageLayers;
	private Properties properties;
	public  static int completeGenerationalCount = 1;
	public  static int completeEvaluationCount   = 0;
	protected Instance instance;

	public Engine(Properties p) 
	{
		this.ageGap      = Integer.parseInt(p.getProperty(Constants.ALPS_AGE_GAP));
		this.ageLayers   = Integer.parseInt(p.getProperty(Constants.ALPS_NUMBER_OF_LAYERS));
		this.properties  = p;
		try 
		{
			start(new Instance());
		} 
		catch (InitializationException e) 
		{
			System.out.println("Parameter instance could not be created \n"+ e.getMessage());
			e.printStackTrace();
		}
	}


	public void start(Instance instance) throws InitializationException
	{
		ArrayList<Layer> layers = instance.agingLayers(this.properties).
				agingScheme(this.ageGap, this.ageLayers);

		/* add a GA to each layer */
		for(Layer l: layers)
		{
			System.out.println("Setting up evolutionary system for layer #"+ l.getId());
			l.setEvolution(new EvolveGA(this.properties));

			if(!l.getIsBottomLayer())
				l.initializerFlag = Boolean.FALSE;

			/**initialize generational count **/
			l.setParameters(l.getEvolution().getParameters());
			/**l.getEvolution().getParameters().setGenerationsEvolved(0) **/
			l.getParameters().setGenerationsEvolved(0); 
			l.getParameters().setSeed(l.getParameters().getSeed());               //set seed
			/**l.getParameters().setSeed(l.getParameters().getSeed()+l.getId()+1); //set seed **/
			/**fill with empty population  **/
			l.getEvolution().setCurrentPopulation(new Population());
		}

		//print replacement strategy
		System.out.println("\n------------------------"+ 
				instance.replacementOperation(this.properties).toString() + 
				" ALPS STARTED" + "------------------------\n");
		layeredEvolutionALPS(layers);
	}


	public void layeredEvolutionALPS(ArrayList<Layer> layers)
	{
		ALPSLayers alps = new ALPSLayers(layers,0);
		/* keep running till stopped */
		while(Engine.completeGenerationalCount <= layers.get(0).getParameters().getEvaluations())
		{
			
			sequentialLoop(alps,layers);
			
			Engine.completeGenerationalCount++;
			Engine.completeEvaluationCount += layers.get(0).getParameters().getPopulationSize(); //all layers have the same default population size
		}

	}


	/**
	 * sequentially loop through all layers
	 */
	public void sequentialLoop(ALPSLayers alps, ArrayList<Layer> layers)
	{
		
		for(int j=layers.size()-1;j>=0;j--)
		{  
			alps.index =  j; //only modify the index

			if(layers.get(j).getIsBottomLayer() ) //set initializer flag to true when bottom layer is called
			{ 
				if( (layers.get(j).layerEvaluationCount == 0) || 
						(layers.get(j).getEvolution().getCurrentPopulation().size()>0) || layers.get(j).initializerFlag  )
					layers.get(j).getEvolution().start(alps); //good

				if((Engine.completeGenerationalCount % layers.get(0).getMaxAge()==0))
				{
					layers.get(j).initializerFlag      = true;
					layers.get(j).layerEvaluationCount = 0;
				}
			}
			else if((layers.get(j).getEvolution().getCurrentPopulation().size()>0) && //remove if problematic
					Engine.completeGenerationalCount > layers.get(j-1).getMaxAge() )
			{  
				/*
				 * Generational worked without this condition - 
				 * was put here because of SteadyState -- remove if problematic
				 */
				layers.get(j).getEvolution().start(alps); //good

				if((layers.get(j).layerEvaluationCount%layers.get(j).getGenerations())==0)
					layers.get(j).layerEvaluationCount=0;
			}
		}
		
	}
	
	
	
	
	
	

	/**
	 * 
	 * @param layers
	 * @param point
	 * @deprecated
	 */
	public void runner(ArrayList<Layer> layers,int point)
	{
		//cycle through the layers starting from the back
		for(int i=point; i>=0;i--)
		{
			System.out.println("---------------------------------------"
					+ "\nEvolution started in layer #"+layers.get(i).getId());
			//assign the most current evaluation of the highest layer to layer#0 
			/*
		     if(i==0)
		     {
		   	    layers.get(0).setEvaluationCounter(layers.get(point).getEvaluationCounter()); 
		     }
			 */
			layers.get(i).getEvolution().start(new ALPSLayers(layers,i)); //good
		}

		if((point+1)<layers.size()) //dont perform movement if evolution has reached the last layer
		{
			try 
			{
				layers = moveLayerUp(layers,point+1);
			} 
			catch (CloneNotSupportedException e)
			{
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
	public ArrayList<Layer> moveLayerUp(
			ArrayList<Layer> layers, int evolvedLayerIncrement) throws CloneNotSupportedException
			{
		ALPSReplacementStrategy ars = new ALPSReplacementStrategy();
		//push upwards to the next higher layer
		for(int i=evolvedLayerIncrement; i>0;i--)
		{
			System.out.println("Moving population from layer #"+ layers.get(i-1).getId() +
					" to layer #"+ layers.get(i).getId());
			layers.get(i).initializerFlag   = true;
			layers.get(i-1).initializerFlag = true;

			//second implementation
			//ars.interLayerMigrations(layers,layers.get(i-1).getEvolution().getCurrentPopulation(),i);

			ars.completeLayerMigrations(layers,i);

			/*
			 * first implementation
			 * layers.get(i).getEvolution().setCurrentPopulation(
			 * 		(Population) layers.get(i-1).getEvolution().getCurrentPopulation());

			 * global value preferred
			 * layers.get(i).setEvaluationCounter(
			 *		layers.get(i-1).getEvaluationCounter());

			 * clear previous layer and prepare for replacement of population
			 * layers.get(i-1).getEvolution().setCurrentPopulation(new Population());
			 * 
			 */
		}

		return layers;
			}


}
