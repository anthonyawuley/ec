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
package operator.operations;

import java.util.ArrayList;

import algorithms.alps.system.ALPSLayers;
import algorithms.ga.Evolve;

/**
 *
 * @author anthony
 */
public interface SelectionOperation {
    
	/**
	 * 
	 * @return
	 */
	public ArrayList<Integer> getTournamentSelection();
	
	/**
	 * 
	 * @param selop
	 * @return
	 */
	public String toString();
	
	/**
	 * 
	 * @param populationSize dynamic pop size
	 * @param tournamentSize dynamic tournament size
	 * @return ArrayList<Integer> of tournament individuals
	 */
	public ArrayList<Integer> performTournamentSelection(Evolve e, int popSize);
	
	
	/**
	 * 
	 * @param layers
	 * @param populationSize
	 * @param tournamentSize
	 * @return
	 */
	public ArrayList<Integer> performTournamentSelection(Evolve e,
			ALPSLayers alpsLayers);
	
	
	/**
	 * 
	 * @param tournamentSize
	 * @param min
	 * @param max
	 * @return
	 */
	public ArrayList<Integer> performTournamentSelectionWithLimits(Evolve e,int min, int max);
	
}
