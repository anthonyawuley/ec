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
package operator.operations.selection;

import java.util.ArrayList;

import algorithms.alps.system.ALPSLayers;
import operator.operations.SelectionOperation;

/**
 *
 * @author anthony
 */
public class RouletteWheel implements SelectionOperation{
    

    private final String selectionOperation = "Roulette Wheel selection";
    

    
 	public String toString() 
 	{
 		return this.selectionOperation;
 	}
	
	public ArrayList<Integer> getTournamentSelection()
	{
		return new ArrayList<Integer>();
	}

	
	@Override
	public ArrayList<Integer> performTournamentSelection(int populationSize,
			int tournamentSize) 
	{
		return null;
	}

	@Override
	public ArrayList<Integer> performTournamentSelection(ALPSLayers alpsLayers,
			int populationSize, int tournamentSize) 
	{
		return null;
	}

	@Override
	public ArrayList<Integer> performTournamentSelectionWithLimits(
			int tournamentSize, int min, int max) {
		// TODO Auto-generated method stub
		return null;
	}
}
