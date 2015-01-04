package operator.crossover;

import individuals.Chromosome;
import individuals.Individual;
import individuals.populations.Population;

import java.util.ArrayList;

import operator.CrossoverModule;

public class BestCostRouteCrossover  extends CrossoverModule {


	public BestCostRouteCrossover() 
	{
		super("best route crossover");
	}

	@Override
	public ArrayList<Individual> performCrossoverOperation(Population p,
			Chromosome c1, Chromosome c2,
			ArrayList<Integer> tournamentIndividuals, int numberOfChildrenToAdd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Individual> performCrossoverOperation(
			Population p,
			Chromosome c1, 
			Chromosome c2,
			ArrayList<Integer> tournamentIndividuals,
			int numberOfChildrenToAdd, 
			ArrayList<Double> ages,
			String replacementType) 
			{
		// TODO Auto-generated method stub
		return null;
	}

	

}
