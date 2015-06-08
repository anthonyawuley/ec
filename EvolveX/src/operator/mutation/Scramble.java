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
package operator.mutation;

import individuals.Chromosome;
import individuals.Individual;
import individuals.populations.Population;

import java.util.ArrayList;

import operator.MutationModule;

/**
 *
 * @author anthony
 */
public class Scramble extends MutationModule {

	public Scramble(String type) 
	{
		super(type);
	}

	@Override
	public ArrayList<Individual> performMutationOperation(Population p,
			Chromosome c1, int parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Individual> performMutationOperation(Population p,
			Chromosome c1, int parentId, ArrayList<Double> ages,String replacementType) {
		// TODO Auto-generated method stub
		return null;
	}

    
}
