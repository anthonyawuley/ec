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
package ec.util.statistics.singleobjective.alps;

import ec.fitnessevaluation.eval.TSP;
import ec.graphing.Ordinates;
import ec.individuals.fitnesspackage.PopulationFitness;
import ec.individuals.populations.Population;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import ec.algorithms.alps.layers.Layer;
import ec.algorithms.alps.system.Engine;
import ec.util.Constants;
import ec.util.statistics.BasicStatistics;
import ec.util.statistics.StatisticsCollector;

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
	   		 Layer layer,
	   		 int run,
	   		 int evals, 
	   		 ArrayList<Integer> bestIndividuals,
	   		 int numberOfIndividualsToPrint)
	   
	   {
		 
	   	FileWriter fw = null;
	   	BufferedWriter bw = null;
	   	TSP tsp = new TSP();
	   	String individualsChromosome = "";

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
	   		File file = getIndFile(run,p,"layer_"+layer.getId()+"_");
	   		
	   		
	   		//if file doesn't exists, then create it
	   		if(!file.exists()  || run==0)
	   			file.createNewFile();
	   		

	   		//true = append file
	   		fw = new FileWriter(file ,true);
	   		bw = new BufferedWriter(fw);
	   		
	   		//for(int i=0;i<bestIndividuals.size();i++)
	   		for(int i=0;i<numberOfIndividualsToPrint;i++)
	   		{
	   		  /* chrom 1  distance  chrom 2 distance .....*/
	   		  individualsChromosome += i+"\t" + statpop.get(i).getFitness().getDouble() +"\t";
	   		  //individualsChromosome += i+"\t" + genFit.get(i) +"\t";
	   		}
	   		//append generation #
	   	    bw.write(evals + "\t" + individualsChromosome +"\n");
	   		bw.close();
	   	} 
	   	catch (IOException ex) 
	   	{
	   		 System.err.println("Error writing file for run "+ run +" evaluation " +evals+  ex.getMessage());
	   	} 
	   	
	   	
	   }
	

    /**
     * ALPS
     */
	 @Override
	public  void printStatsReport(
	    		Population pop,
	    		Properties p, 
	    		ArrayList<Integer> bestIndividuals,
	    		final Layer layer,
	    		final int generation, 
	    		final int run,
	    		PopulationFitness f)
	    {
	       System.out.println("Best:      "+ f.getBestFitness() +"\t"
	       		            + "Average:   "+ f.getAverageFitness() +"\t"
	       		            + "SD:        "+ f.getStandardDeviationFitness() +"\n");
	       
	       int numberOfIndividualsToPrint = Integer.parseInt(p.getProperty(Constants.NUMBER_INDIVIDUALS_PRINT));
	       
	       //when in last layer print stat file
	       if(layer.getId() == (Engine.numberOfLayers-1) && 
	    		   (Engine.completeEvaluationCount+layer.getParameters().getPopulationSize()*(Engine.numberOfLayers-1))>Engine.evaluations )
	       {
	    	   /*
	    	    * report type indicates if tsp or vrp will be printed
	    	    * set value in Constants file
	    	    */
	    	   
	          new Ordinates(pop,bestIndividuals,numberOfIndividualsToPrint,p, 
	        		  layer, run,generation,",",Constants.REPORT_TYPE);
	          //new Ordinates(pop.get(bestIndividuals.get(0)),p, run, generation,",");
	          //new Plot(pop.get(bestIndividuals.get(0)),p);
	       }
	       
	       generateStatFile(p, layer, run,Engine.completeEvaluationCount, f.getBestFitness(), 
	    		   f.getAverageFitness(), f.getStandardDeviationFitness());
	       //System.out.println(pop.get(bestIndividuals.get(1)).getChromosome());
	       
	       writeIndividuals(pop,p, layer,run, Engine.completeEvaluationCount,bestIndividuals,numberOfIndividualsToPrint);
	       
	    }
    
    
	
	/**
	 * 
	 */
	 @Override
	public void generateStatFile(
    		 Properties p,
    		 Layer layer,
    		 int run,
    		 int gen, 
    		 double bestFitness, 
    		 double averageFitness, 
    		 double standardDeviation)
    {
    	FileWriter fw = null;
    	BufferedWriter bw = null;
       
    	try 
    	{
    		//String header = " This content will append to the end of the file\n";
    		File file = getStatsFile(run,p,"layer_"+layer.getId()+"_");
    		
    		//if file doesnt exists, then create it
    		if(!file.exists()  || run==0) 
    			file.createNewFile();
    		
    		
    		//true = append file
    		fw = new FileWriter(file ,true);
    		bw = new BufferedWriter(fw);
    	    bw.write(gen + "\t" + bestFitness + "\t" + averageFitness + "\t" + standardDeviation +"\n");
    		bw.close();
    	} 
    	catch (IOException ex) 
    	{
    		 System.out.println("Error writing file "+  ex.getMessage());
    	} 
    	
    }
	

}
