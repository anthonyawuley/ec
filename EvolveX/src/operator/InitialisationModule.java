package operator;

import java.util.Properties;

import individuals.Gene;
import individuals.populations.Population;

public interface InitialisationModule {

	public String toString(int popSize);
	
	/**
	 * 
	 * @param g
	 * @param prop
	 * @param populationSize
	 * @param chromosomeLength
	 * @return initialization for Generational Replacement
	 * @deprecated
	 */
	public  Population generateInitialPopulation(
			Gene g,
			Properties prop, 
			int populationSize, 
			int chromosomeLength);
	
	/**
	 * 
	 * @param g
	 * @param prop
	 * @param populationSize
	 * @param chromosomeLength
	 * @return initialization for Generational Replacement
	 */
	public  Population generateInitialPopulation(
			Properties prop, 
			int populationSize, 
			int chromosomeLength);
	
	/**
	 * 
	 * @param g
	 * @param prop
	 * @param populationSize
	 * @param chromosomeLength
	 * @param evaluations : number of evaluations performed so far
	 * @return initial population
	 * @deprecated
	 */
	public  Population generateInitialPopulation(
			Gene g,
			Properties prop, 
			int populationSize, 
			int chromosomeLength,
			double evaluations);

	Population generateInitialPopulation(Properties prop, int populationSize,
			int chromosomeLength, double evaluations);
	
}
