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
package ec.algorithms.alps;

import ec.algorithms.alps.layers.InitializeParams;
import ec.algorithms.alps.system.EvolveALPS;
import ec.individuals.populations.Population;

    public interface LayerInterface {

    /**
     * 	
     * @return
     */
	@Override
	public String toString();
	/**
	 * 
	 * @return
	 */
	public int getId();
	/**
	 * @return gets a count on number of evaluations completed for current layer
	
	public double getEvaluationCounter();
   
     * @param sets number of evaluations completed for current layer
     
	public void setEvaluationCounter(double evaluationCounter);
	*/
	/**
	 * 
	 * @return
	   public void  setMTF(MersenneTwisterFast rng);
	
	   public MersenneTwisterFast getMTF(long seed);
	 * 
	 */
	
    /**
     * s
     * @param count the number of generations for which a layer must evolve
     * this is given as maxCountOfCurrentLayer - MaximumCountOfPreviousLayer
     */
	public void setId(int id);
	/**
	 * 
	 * @param e
	 */
	public void setEvolution(EvolveALPS e);
	/**
	 * 
	 * @return
	 */
	public EvolveALPS getEvolution();
	/**
	 * 
	 * @param i
	 */
    public void setParameters(InitializeParams i);
	/**
	 * 
	 * @return
	 */
	public InitializeParams getParameters();
	/**
	 * 
	 * @return
	 */
	public boolean getIsActive();
    /**
     * 
     * @param active
     */
	public void setIsActive(boolean active);
    /**
     * 
     * @return
     */
	public boolean getIsBottomLayer();
    /**
     * 
     * @param active
     */
	public void setIsBottomLayer(boolean active);
    /**
     * 
     * @param pop
     */
	public void tryMoveUp(int layer, Population pop);
	/**
	 * 
	 * @return
	 */
	public int getMaxAge();
    /**
     * 
     * @param age
     */
	public void setMaxAgeLayer(int age);
	/**
	 * 
	 * @return
	 */
	public int getGenerationalCount();
    /**
     * 
     * @param count
     */
	public void setGenerationalCount(int count);
	/**
	 * 
	 * @return
	 */
	public int getGenerations();
    /**
     * s
     * @param count the number of generations for which a layer must evolve
     * this is given as maxCountOfCurrentLayer - MaximumCountOfPreviousLayer
     */
	public void setGenerations(int count);
	
	

}
