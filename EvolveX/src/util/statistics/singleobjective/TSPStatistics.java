package util.statistics.singleobjective;

import fitnessevaluation.eval.TSP;
import individuals.populations.Population;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import util.statistics.BasicStatistics;
import util.statistics.StatisticsCollector;

public class TSPStatistics extends BasicStatistics implements StatisticsCollector{

	public TSPStatistics() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 
	 * @param pop
	 * @param p
	 * @param run
	 * @param gen
	 * @param bestIndividuals
	 */
	@Override
	 public void writeIndividuals(
	         Population pop,
	   		 Properties p,
	   		 int run,
	   		 int gen, 
	   		 ArrayList<Integer> bestIndividuals,
	   		 int numberOfIndividualsToPrint)
	   
	   {
		 
	   	FileWriter fw = null;
	   	BufferedWriter bw = null;
	   	TSP tsp = new TSP();
   		
	   	
	   	String individualsChromosome = "";

	   	
	   	//System.out.println("#hahahaha"+bestIndividuals);
   		//create population of best individuals for stats
   		Population statpop = new Population();
   		for(int i=0;i<numberOfIndividualsToPrint;i++)
   		{
   			statpop.add(pop.get(bestIndividuals.get(i)));
   			//System.out.println("#hahahaha"+pop.get(bestIndividuals.get(i)).getChromosome().get(279));
   		}
	   	
   		
   		//ArrayList<Double> genFit = tsp.calcGenerationalFitness(statpop,p);
   		tsp.calcGenerationalFitness(statpop,p);
	   	
	   	try 
	   	{
	   		//String header = " This content will append to the end of the file\n";
	   		File file = getIndFile(run,p);
	   		
	   		//if file doesn't exists, then create it
	   		if(!file.exists())
	   		{
	   			file.createNewFile();
	   		}

	   		//true = append file
	   		fw = new FileWriter(file ,true);
	   		bw = new BufferedWriter(fw);
	   		
	   		//for(int i=0;i<bestIndividuals.size();i++)
	   		for(int i=0;i<numberOfIndividualsToPrint;i++)
	   		{
	   		  /** chrom 1  distance  chrom 2 distance .....*/
	   		  individualsChromosome += i+"\t" + statpop.get(i).getFitness().getDouble() +"\t";
	   		  //individualsChromosome += i+"\t" + genFit.get(i) +"\t";
	   		}
	   		//append generation #
	   	    bw.write(gen + "\t" + individualsChromosome +"\n");
	   		bw.close();
	   	} 
	   	catch (IOException ex) 
	   	{
	   		 System.err.println("Error writing file for run "+ run +" generation " +gen+  ex.getMessage());
	   	} 
	   	
	   	
	   }
	

}
