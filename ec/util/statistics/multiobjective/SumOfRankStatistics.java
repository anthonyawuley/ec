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
package ec.util.statistics.multiobjective;

import ec.fitnessevaluation.eval.SOR;
import ec.fitnessevaluation.eval.WS;
import ec.individuals.populations.Population;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import ec.util.statistics.BasicStatistics;
import ec.util.statistics.StatisticsCollector;

public class SumOfRankStatistics extends BasicStatistics implements StatisticsCollector{

	public SumOfRankStatistics() {
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
	   	String individualsChromosome = "";
  
  		//create population of best individuals for stats
  		Population statpop = new Population();
  		for(int i=0;i<bestIndividuals.size();i++)
  		{
  			statpop.add(pop.get(bestIndividuals.get(i)).clone());
  		}
	   	
  		SOR sr = new SOR();
  		//calculate paretho fitness ranking of individuals
  		//ArrayList<Double> genFit = sr.calcGenerationalFitness(statpop,p);
  		sr.calcGenerationalFitness(statpop,p);
  		
  		
	   	try 
	   	{
	   		//String header = " This content will append to the end of the file\n";
	   		 
	   		File file = getIndFile(run,p,"");
	   		
	   		//if file doesnt exists, then create it
	   		if(!file.exists()  || run==0)
	   			file.createNewFile();
	   		

	   		//true = append file
	   		fw = new FileWriter(file ,true);
	   		bw = new BufferedWriter(fw);
	   		WS ws = new WS();
	   		
	   		for(int i=0;i<numberOfIndividualsToPrint;i++)
	   		{
                ArrayList<Double> vrp = ws.timeWindowEvaluations(pop.get(bestIndividuals.get(i)),p);
	   			individualsChromosome += "Chromosome: "+i+"\n"
	   					              //+  "Rank : " + genFit.get(i) +"\n"
	   					              +  "Rank : " + statpop.get(i).getFitness().getDouble() +"\n"
	   					              +  "Vehicles : "+ vrp.get(0) +"\n"
	   					              +  "Distance : "+ vrp.get(1) +"\n";
	   		
	   		}
	   		 
	   	    bw.write("Generation #" + gen + "\t\n" + individualsChromosome +"\n\n");
	   		bw.close();
	   	} 
	   	catch (IOException ex) 
	   	{
	   		 System.err.println("Error writing file for run "+ run +" generation " +gen+  ex.getMessage());
	   	} 
	   	
	   	
	   }
	


}
