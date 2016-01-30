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
package ec.operator.crossover;

import ec.individuals.Chromosome;
import ec.individuals.Individual;
import ec.individuals.populations.Population;

import java.util.ArrayList;

import ec.operator.CrossoverModule;

/**
 *
 * @author anthony
 */
public class TwoPointCrossover  extends CrossoverModule {

	public TwoPointCrossover(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Individual> performCrossoverOperation(Population p,
			Chromosome c1, Chromosome c2,
			ArrayList<Integer> tournamentIndividuals, int numberOfChildrenToAdd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Individual> performCrossoverOperation(Population p,
			Chromosome c1, Chromosome c2,
			ArrayList<Integer> tournamentIndividuals,
			int numberOfChildrenToAdd, ArrayList<Double> ages,
			String replacementType) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
