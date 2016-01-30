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
package ec.algorithms.alps.layers;

import ec.individuals.populations.Population;
import ec.algorithms.alps.LayerInterface;
import ec.algorithms.alps.system.EvolveALPS;

public class Layer implements LayerInterface {

	private int maxAgeLayer;
	private boolean isBottomLayer = Boolean.FALSE;
	private boolean isActive = Boolean.FALSE;
	private InitializeParams params;
	private EvolveALPS evolve;
	private int generationalCount, generations;
	private int layerId;
	public int layerEvaluationCount   = 0;
	public int layerCompleteGenerationCount = 0;
	public boolean initializerFlag = true;
	
	public Layer() 
	{
		
	}
	
	

	@Override
	public boolean getIsActive() 
	{
		return this.isActive;
	}

	@Override
	public void setIsActive(boolean status) 
	{
		this.isActive = status;
	}

	@Override
	public boolean getIsBottomLayer() 
	{
		return this.isBottomLayer;
	}

	@Override
	public void setIsBottomLayer(boolean status) 
	{
		this.isBottomLayer = status;
	}

	@Override
	public void tryMoveUp(int layer, Population pop) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxAge() 
	{
		return this.maxAgeLayer;
	}

	@Override
	public void setMaxAgeLayer(int age) 
	{
		this.maxAgeLayer = age;
	}
   
	@Override
	public void setParameters(InitializeParams i) 
	{
		this.params = i;
	}



	@Override
	public InitializeParams getParameters() 
	{
		return this.params;
	}



	@Override
	public void setEvolution(EvolveALPS e) 
	{
		this.evolve = e;
	}



	@Override
	public EvolveALPS getEvolution() 
	{
		return this.evolve;
	}



	@Override
	public int getGenerationalCount() 
	{
		return this.generationalCount;
	}



	@Override
	public void setGenerationalCount(int count) 
	{
		this.generationalCount = count;
	}



	@Override
	public int getGenerations() 
	{
		return this.generations;
	}



	@Override
	public void setGenerations(int count) 
	{
		this.generations = count;
	}



	@Override
	public int getId() 
	{
		return this.layerId;
	}



	@Override
	public void setId(int id) {
		this.layerId = id;
	}


   /*
	@Override
	public double getEvaluationCounter() 
	{
		return this.evaluationCounter;
	}



	@Override
	public void setEvaluationCounter(double evaluationCounter) 
	{
		this.evaluationCounter = evaluationCounter;
	}
	*/

}
