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
package ec.algorithms.simulatedannealing;

import java.util.Properties;

import ec.algorithms.simulatedannealing.solution.Writer;
import ec.util.Constants;
import ec.util.random.MersenneTwisterFast;
import ec.fitnessevaluation.eval.CVRP;
import ec.individuals.Chromosome;

public class StartCVRP {

	MersenneTwisterFast rng = new MersenneTwisterFast();;
	
	public StartCVRP(Properties p,int run) 
	{
		//initialize a chrmosome
		Chromosome ch = new Chromosome();
		ch.setChromosomeSize(Integer.parseInt(p.getProperty(Constants.INITIAL_CHROMOSOME_SIZE)));
		//ch.createChromosome(ch, new TSP(), p);
		ch.createChromosome(ch, p,rng);
		
		CVRP  eval = new CVRP();           //initialize capacitated VRP
		eval.CVRPEvaluations(ch,p);        //evaluate chromosome
		new Writer().writeIndividuals(p, run, eval.hybrid); //perform simulated annealing and write results
		
		System.out.println(eval.hybrid.size());
	}
	
	
	
	
	//public void
	

}
