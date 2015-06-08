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

import individuals.fitnesspackage.PopulationFitness;
import individuals.populations.Population;

import java.util.ArrayList;
import java.util.Properties;

import algorithms.alps.ALPSReplacement;
import algorithms.alps.system.ALPSLayers;
import operator.CrossoverModule;
import operator.MutationModule;
import util.statistics.StatisticsCollector;

/**
 *  This interface must be implemented by any replacement strategy. since the GA system fully supports ALPS, its
 *  It is expected that any such class must implement a method that supports replacement for ALPS as well as
 *  regular GA
 * @author anthony
 */
public interface ReplacementStrategy {
   
	
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
    public  Population nextGeneration(PopulationFitness f,
    		                         CrossoverModule crx,
                                     MutationModule mtx,
                                     SelectionOperation so,
                                     StatisticsCollector stats,
                                     Properties p,
    		                         final ArrayList<Population> current,
    		                         int generation,
                                     int run,
                                     double crossoverRate, 
                                     double mutationRate,
                                     int elitismSize,
                                     int tournamentSize,
                                     double selectionPressure);
    
  
	
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
    		                          PopulationFitness f,
     		                          CrossoverModule crx,
                                      MutationModule mtx,
                                      SelectionOperation so,
                                      StatisticsCollector stats,
                                      ALPSReplacement alpsReplacement,
                                      Properties p,
     		                          final ArrayList<Population> current,
     		                          int generation,
     		                          ALPSLayers alpsLayers,
                                      double crossoverRate, 
                                      double mutationRate,
                                      int elitismSize,
                                      int tournamentSize,
                                      double selectionPressure);
    
 
     
}
