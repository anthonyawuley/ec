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

import ec.algorithms.ga.Evolve;
import ec.individuals.Chromosome;
import ec.individuals.Gene;
import ec.individuals.Individual;
import ec.individuals.fitnesspackage.BasicFitness;
import ec.individuals.populations.Population;
import ec.operator.CrossoverModule;
import ec.util.random.GenerateMask;

import java.util.ArrayList;

/**
 * 
 * @author Anthony Awuley
 */
public class UniformOrderCrossover extends CrossoverModule {
	/** */
	private String crossoverMask;

	public UniformOrderCrossover() {
		super("uox");
	}

	/**
	 * 
	 * @param mask
	 */
	public void setCrossoverMask(String mask) {
		this.crossoverMask = mask;
	}

	/**
	 * 
	 * @return
	 */
	public String getCrossOverMask() {
		return this.crossoverMask;
	}

	/**
	 * 
	 * @param p
	 *            : population
	 * @param c1
	 *            : first chromosome
	 * @param c2
	 *            : second chromosome
	 * @param parentsId
	 *            : array id's of parents with above chromosome
	 * @param numberOfChildrenToAdd
	 *            : determine number of offspring(2 or 1) to add to new
	 *            population
	 * @return
	 */
	@Override
	public ArrayList<Individual> performCrossoverOperation(
			Evolve e,Population p,
			Chromosome c1, Chromosome c2,
			ArrayList<Integer> tournamentIndividuals, int numberOfChildrenToAdd) {
		Individual id1 = new Individual();
		Individual id2 = new Individual();
		ArrayList<Individual> children = new ArrayList<>();
		ArrayList<ArrayList<Integer>> mask = new ArrayList<>();

		crossoverMask = GenerateMask.getMask(e,p.get(0).getChromosome()
				.getGenes().size());
		// return index position of  1's in mask
		mask = this.getIndex(crossoverMask, "1"); 

		/*
		 * complete replacement of other bit positions without mask flag of "1"
		 * set -1 for values that must be replaced individuals that have -1
		 * after operation are invalid
		 */

		for (int j = 0; j < c1.getGenes().size(); j++) // take size of any of
														// the chromosomes
		{
			if (!GenerateMask.isExistIndex(mask.get(0), j + "")) {
				c1.getGenes().set(j, new Gene(-1));
				c2.getGenes().set(j, new Gene(-1));
			}
		}
		// loop for index  positions to be  replaced by  alternativive parent
		for (int i = 0; i < mask.get(1).size(); i++) { 
			// take size of any  of the  chromosomes
			for (int j = 0; j < c1.getGenes().size(); j++) 
			{
				if ((this.returnAvailableIndex(c1,
						p.get(tournamentIndividuals.get(1)).getChromosome()
								.getGenes().get(j)) == -1)) {
					c1.getGenes().set(
							mask.get(1).get(i),
							p.get(tournamentIndividuals.get(1)).getChromosome()
									.getGenes().get(j)); // swap remaining  chromosomes
					// c2.getChromosome().set(j,
					// p.get(parentsId[0]).getChromosome().get(j));
					break; // to avoid exhaustive but not-required search
				}
			}
			for (int j = 0; j < c2.getGenes().size(); j++) // take size of any of the  chromosomes
			{
				if ((this.returnAvailableIndex(c2,
						p.get(tournamentIndividuals.get(0)).getChromosome()
								.getGenes().get(j)) == -1) ) {
					c2.getGenes().set(
							mask.get(1).get(i),
							p.get(tournamentIndividuals.get(0)).getChromosome()
									.getGenes().get(j)); // swap remaining
															// chromosomes
					// System.out.println("\nRR "+c2.getChromosome().get(mask.get(1).get(i)));
					break;
				}
			}
		}

		// set individual properties for chldren and add to new population

		id1.setChromosome(c1);
		id1.setFitness(new BasicFitness()); // set fitness object

		id2.setChromosome(c2);
		id2.setFitness(new BasicFitness()); // set fitness object
		// add children if they have not duplicates
		switch (numberOfChildrenToAdd) {
		case 1: // add one offspring
			if (!chromosomeHasDuplicateGenes(c1.getGenes()))
				children.add(id1);
			break;
		default: // add two offsprings
			if (!chromosomeHasDuplicateGenes(c1.getGenes())
					&& !chromosomeHasDuplicateGenes(c2.getGenes())) {
				children.add(id1);
				children.add(id2);
			}
			break;
		}

		this.setChildrenAdded(children.size()); // set number of children added
		// System.out.println("This is my crossover mask: "+ crossoverMask + " "
		// + comsk.length +" 0,1: "+ twoParents[0] +"-"+ twoParents[1]);
		setOffsprings(children);
		return children;

	}

	@Override
	public ArrayList<Individual> performCrossoverOperation(
			Evolve e,Population p,
			Chromosome c1, Chromosome c2,
			ArrayList<Integer> tournamentIndividuals,
			int numberOfChildrenToAdd, ArrayList<Double> ages) {
		Individual id1 = new Individual();
		Individual id2 = new Individual();
		ArrayList<Individual> children = new ArrayList<>();
		ArrayList<ArrayList<Integer>> mask = new ArrayList<>();

		crossoverMask = GenerateMask.getMask(e,p.get(0).getChromosome()
				.getGenes().size());
		mask = this.getIndex(crossoverMask, "1"); // return index position of
													// 1's in mask

		/*
		 * complete replacement of other bit positions without mask flag of "1"
		 * set -1 for values that must be replaced individuals that have -1
		 * after operation are invalid
		 */
		for (int j = 0; j < c1.getGenes().size(); j++) // take size of any of
														// the chromosomes
		{
			if (!GenerateMask.isExistIndex(mask.get(0), j + "")) {
				c1.getGenes().set(j, new Gene(-1));
				c2.getGenes().set(j, new Gene(-1));
			}
		}

		// loop for index  positions to be  replaced by  alternativive parent
		for (int i = 0; i < mask.get(1).size(); i++) { 
			// take size of any  of the  chromosomes
			for (int j = 0; j < c1.getGenes().size(); j++) 
			{
				if ((this.returnAvailableIndex(c1,
						p.get(tournamentIndividuals.get(1)).getChromosome()
								.getGenes().get(j)) == -1)) {
					c1.getGenes().set(
							mask.get(1).get(i),
							p.get(tournamentIndividuals.get(1)).getChromosome()
									.getGenes().get(j)); // swap remaining  chromosomes
					// c2.getChromosome().set(j,
					// p.get(parentsId[0]).getChromosome().get(j));
					break; // to avoid exhaustive but not-required search
				}
			}
			// take size of any  of the  chromosomes
			for (int j = 0; j < c2.getGenes().size(); j++) 
			{
				if ((this.returnAvailableIndex(c2,
						p.get(tournamentIndividuals.get(0)).getChromosome()
								.getGenes().get(j)) == -1)) {
					c2.getGenes().set(
							mask.get(1).get(i),
							p.get(tournamentIndividuals.get(0)).getChromosome()
									.getGenes().get(j)); // swap remaining  chromosomes
					// System.out.println("\nRR "+c2.getChromosome().get(mask.get(1).get(i)));
					break;
				}
			}
		}

		id1.setChromosome(c1);
		id1.setFitness(new BasicFitness()); // set fitness object
		id2.setChromosome(c2);
		id2.setFitness(new BasicFitness()); // set fitness object

		/*
		 * Randomly generated individuals store the number of evaluations that
		 * have been performed so far, and individuals created through mutation
		 * and recombination store the smallest (which is equivalent to oldest)
		 * value of their parents
		 * TODO use different properties for age and eval
		 */
		double eval = Math.max(ages.get(0), ages.get(1));
		id1.setBirthEvaluations(eval);
		id2.setBirthEvaluations(eval);

		double age = Math.max(ages.get(0), ages.get(1));
		id1.setAge(age); // add age
		id2.setAge(age); // add age

		// add children if they have not duplicates
		switch (numberOfChildrenToAdd) {
		case 1: // add one offspring
			if (!chromosomeHasDuplicateGenes(c1.getGenes()))
				children.add(id1);
			break;
		default: // add two offsprings
			if (!chromosomeHasDuplicateGenes(c1.getGenes())
					&& !chromosomeHasDuplicateGenes(c2.getGenes())) {
				children.add(id1);
				children.add(id2);
			}
			break;
		}

		this.setChildrenAdded(children.size()); // set number of children added
		// convert offspring to population.
		setOffsprings(children);

		return children;

	}

}
