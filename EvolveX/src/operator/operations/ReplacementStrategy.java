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
 *
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
