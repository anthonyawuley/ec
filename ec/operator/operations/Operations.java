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
package ec.operator.operations;

import ec.algorithms.ga.Evolve;
import ec.fitnessevaluation.FitnessExtension;
import ec.individuals.IndividualInterface;
import ec.individuals.fitnesspackage.PopulationFitness;
import ec.individuals.populations.Population;

import java.util.List;

/**
 * 
 * @author Anthony Awuley
 */
public class Operations {

	/**
	 * 
	 * @param operands
	 * @deprecated
	 */
	public void doOperation(List<IndividualInterface> operands){};

	/**
	 * Performs the operation on an operand
	 * 
	 * @param operand
	 *            operand to perform operation on
	 * @deprecated
	 */
	 public void doOperation(IndividualInterface operand){};

	 
	/**
	 * 
	 * @param Evolve e
	 * @param Population pop
	 * @param PopulationFitness f
	 * @return
	 */
	public Population performElitism(Evolve e, Population pop,
			PopulationFitness f) {
		Population elitePop = new Population();

		// ignore elitism if steady state replacement is used
		if (e.elitismSize > 0) 
		{ // create elite individuals to be added to next generation
			for (int i = 0; i < e.elitismSize; i++) {
				try 
				{
					elitePop.add(pop.get(
							((FitnessExtension) f).getBestIndividualsOfGeneration().get(i)).clone());
					/* increment age of best individuals by 1 for elitism */
					elitePop.get(i).setAge(elitePop.get(i).getAge() + 1);
					/* set evaluations for steady state - not necessary :) */
					elitePop.get(i).setBirthEvaluations(elitePop.get(i).getBirthEvaluations());
					/* set parent flag on */
					elitePop.get(i).parentFlag = true;
					
				} catch (IndexOutOfBoundsException err) 
				{
					continue;
				}
			}
		}

		return elitePop;

	}

}
