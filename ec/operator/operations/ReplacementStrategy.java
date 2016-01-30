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

import ec.individuals.fitnesspackage.PopulationFitness;
import ec.individuals.populations.Population;

import java.util.ArrayList;

import ec.algorithms.alps.ALPSReplacement;
import ec.algorithms.alps.system.ALPSLayers;
import ec.algorithms.ga.Evolve;
import ec.operator.CrossoverModule;
import ec.operator.MutationModule;
import ec.util.statistics.StatisticsCollector;

/**
 *  This interface must be implemented by any replacement strategy. since the GA system fully supports ALPS, its
 *  It is expected that any such class must implement a method that supports replacement for ALPS as well as
 *  regular GA
 * @author anthony
 */
public interface ReplacementStrategy {
   
	
	@Override
	public abstract String toString();
	
	
   /**
    * GENERATE next generation
    * @param f - basic fitness
    * @param crx - crossover module
    * @param mtx - mutation module
    * @param so - selection operator
    * @param current - population <previous+current>
    * @param generation - current generation count
    * @param run - current run count
    * @param crossoverRate - crossover rate
    * @param mutationRate - mutation rate
    * @param tournamentSize - tournament size
    * @return
    */
    public  Population nextGeneration(Evolve e,
    		                         PopulationFitness f,
    		                         CrossoverModule crx,
                                     MutationModule mtx,
                                     SelectionOperation so,
                                     StatisticsCollector stats,
    		                         final ArrayList<Population> current,
    		                         int generation,
                                     int run
                                     );
    
  
	
    /**
     * GENERATE next generation
     * @param f - basic fitness
     * @param crx - crossover module
     * @param mtx - mutation module
     * @param so - selection operator
     * @param current - population <previous+current>
     * @param generation - current generation count
     * @param run - current run count
     * @param crossoverRate - crossover rate
     * @param mutationRate - mutation rate
     * @param tournamentSize - tournament size
     * @return
     */
     public Population nextGenerationALPS(
    		                          Evolve e,
    		                          PopulationFitness f,
     		                          CrossoverModule crx,
                                      MutationModule mtx,
                                      SelectionOperation so,
                                      StatisticsCollector stats,
                                      ALPSReplacement alpsReplacement,
     		                          final ArrayList<Population> current,
     		                          ALPSLayers alpsLayers,
     		                          int generation,
     		                          int run
     		                          );
    
 
     
}
