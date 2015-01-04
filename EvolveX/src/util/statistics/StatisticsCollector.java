package util.statistics;

import individuals.fitnesspackage.PopulationFitness;
import individuals.populations.Population;

import java.util.ArrayList;
import java.util.Properties;

import algorithms.alps.layers.Layer;

public interface StatisticsCollector {

	/**
	 * 
	 * @param pop
	 * @param p
	 * @param bestIndividuals
	 * @param run
	 * @param generation
	 * @param f
	 */
	 public  void printStatsReport(
	    		Population pop,
	    		Properties p, 
	    		ArrayList<Integer> bestIndividuals,
	    		final int run,
	    		final int generation, 
	    		PopulationFitness f);
	 
	 /**
	  * OVER LOADED FOR ALPS
	  * @param pop
	  * @param p
	  * @param bestIndividuals
	  * @param layer
	  * @param generation
	  * @param f
	  */
	 public  void printStatsReport(
	    		Population pop,
	    		Properties p, 
	    		ArrayList<Integer> bestIndividuals,
	    		final Layer layer,
	    		final int generation, 
	    		PopulationFitness f);
	
	
}
