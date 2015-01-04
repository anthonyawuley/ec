package algorithms.simulatedannealing;

import java.util.Properties;

import algorithms.simulatedannealing.solution.Writer;
import util.Constants;
import fitnessevaluation.eval.CVRP;
import individuals.Chromosome;
import individuals.representation.hybrid.TSP;

public class StartCVRP {

	public StartCVRP(Properties p,int run) 
	{
		//initialize a chrmosome
		Chromosome ch = new Chromosome();
		ch.setChromosomeSize(Integer.parseInt(p.getProperty(Constants.INITIAL_CHROMOSOME_SIZE)));
		ch.createChromosome(ch, new TSP(), p);
		
		CVRP  eval = new CVRP();           //initialize capacitated VRP
		eval.CVRPEvaluations(ch,p);        //evaluate chromosome
		new Writer().writeIndividuals(p, run, eval.hybrid); //perform simulated annealing and write results
		
		System.out.println(eval.hybrid.size());
	}
	
	
	
	
	//public void
	

}
