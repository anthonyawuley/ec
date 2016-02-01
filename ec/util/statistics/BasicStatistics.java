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
package ec.util.statistics;

import ec.fitnessevaluation.eval.WS;
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

/**
 *
 * @author anthony
 */
public class BasicStatistics implements StatisticsCollector{
    
    public void printReportHeader()
    {
    
    }
    
    /**
     * 
     */
    @Override
	public  void printStatsReport(
    		Population pop,
    		Properties p, 
    		ArrayList<Integer> bestIndividuals,
    		final int run,
    		final int generation, 
    		PopulationFitness f)
    {
       System.out.println("Best:      "+ f.getBestFitness() +"\t"
       		            + "Average:   "+ f.getAverageFitness() +"\t"
       		            + "SD:        "+ f.getStandardDeviationFitness() +"\n");
       
       int numberOfIndividualsToPrint = Integer.parseInt(p.getProperty(Constants.NUMBER_INDIVIDUALS_PRINT));
       
      
       if(generation == (Integer.parseInt(p.getProperty(Constants.GENERATIONS))-1) )
       {
    	   /**
    	    * report type indicates if tsp or vrp will be printed
    	    * set value in Constants file
    	    */
    	   
          new Ordinates(pop,bestIndividuals,numberOfIndividualsToPrint,p, 
        		  run, generation,",",Constants.REPORT_TYPE);
          //new Ordinates(pop.get(bestIndividuals.get(0)),p, run, generation,",");
          //new Plot(pop.get(bestIndividuals.get(0)),p);
       }
       
       generateStatFile(p, run, generation, f.getBestFitness(), f.getAverageFitness(), 
    		   f.getStandardDeviationFitness());
       //System.out.println(pop.get(bestIndividuals.get(1)).getChromosome());
       
       writeIndividuals(pop,p, run, generation,bestIndividuals,numberOfIndividualsToPrint);
       
    }
     
  
    /**
     * 
     * @param p
     * @param run
     * @param gen
     * @param bestFitness
     * @param averageFitness
     * @param standardDeviation
     */
    public void generateStatFile(
    		 Properties p,
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
    		File file = getStatsFile(run,p,"");
    		
    		//if file doesnt exists, then create it
    		if(!file.exists() || run==0) 
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
    
    
    
    /**
     * 
     * @param p
     * @param layer
     * @param run
     * @param gen
     * @param bestFitness
     * @param averageFitness
     * @param standardDeviation
     */
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
    		if(!file.exists() || run==0) 
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
    
    
    /**
     * 
     * @param run
     * @param p
     * @return
     */
    public File getStatsFile(int run,Properties p,String offset)
    {
    	return new File(Constants.DEFAULT_PARAM_ROOT+
   				"stat_"+run+"_"+offset+
   				p.getProperty(Constants.STATS_FILE)+
   				Constants.DEFAULT_STATS_EXTENSION);
    }
    
    /**
     * 
     * @param run
     * @param p
     * @param offset
     * @return
     */
    public File getIndFile(int run,Properties p, String offset)
    {
    	return new File(Constants.DEFAULT_PARAM_ROOT+
   				"ind_"+run+"_"+offset+
   				p.getProperty(Constants.STATS_FILE)+
   				Constants.DEFAULT_STATS_EXTENSION);
    }
    
    /**
     * 
     * @param pop
     * @param p
     * @param run
     * @param gen
     * @param bestIndividuals
     * @param numberOfIndividualsToPrint
     */
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

   	try 
   	{
   		//String header = " This content will append to the end of the file\n";
   		 
   		File file = getIndFile(run,p,"");
   		
   		//if file doesnt exists, then create it
   		if(!file.exists() || run==0)
   			file.createNewFile();
   		

   		//true = append file
   		fw = new FileWriter(file ,true);
   		bw = new BufferedWriter(fw);
   		WS ws = new WS();
   		
   		for(int i=0;i<numberOfIndividualsToPrint;i++)
   		{
   			ArrayList<Double> vrp = ws.timeWindowEvaluations(pop.get(bestIndividuals.get(i)),p);
   		
   			individualsChromosome += "Chromosome: "+i+"\n"
                    +  "Vehicles : "+ vrp.get(0) +"\n"
                    +  "Distance : "+ vrp.get(1) +"\n"
                    +"\n";
   			
   		}
   	    bw.write("Generation #" + gen + "\t\n" + individualsChromosome +"\n");
   		bw.close();
   	} 
   	catch (IOException ex) 
   	{
   		 System.err.println("Error writing file for run "+ run +" generation " +gen+  ex.getMessage());
   	} 
   	
   }

    
    /**
     * 
     * @param pop
     * @param p
     * @param layer
     * @param run
     * @param gen
     * @param bestIndividuals
     * @param numberOfIndividualsToPrint
     */
    public void writeIndividuals(
             Population pop,
      		 Properties p,
      		 Layer layer,
      		 int run,
      		 int gen, 
      		 ArrayList<Integer> bestIndividuals,
      		 int numberOfIndividualsToPrint)
      
      {
      	FileWriter fw = null;
      	BufferedWriter bw = null;
      	String individualsChromosome = "";

      	try 
      	{
      		//String header = " This content will append to the end of the file\n";
      		 
      		File file = getIndFile(run,p,"layer_"+layer.getId()+"_");
      		
      		//if file doesnt exists, then create it
      		if(!file.exists() || run==0)
      			file.createNewFile();

      		//true = append file
      		fw = new FileWriter(file ,true);
      		bw = new BufferedWriter(fw);
      		WS ws = new WS();
      		
      		for(int i=0;i<numberOfIndividualsToPrint;i++)
      		{
      			ArrayList<Double> vrp = ws.timeWindowEvaluations(pop.get(bestIndividuals.get(i)),p);
      		
      			individualsChromosome += "Chromosome: "+i+"\n"
                       +  "Vehicles : "+ vrp.get(0) +"\n"
                       +  "Distance : "+ vrp.get(1) +"\n"
                       +"\n";
      			
      		}
      	    bw.write("Generation #" + gen + "\t\n" + individualsChromosome +"\n");
      		bw.close();
      	} 
      	catch (IOException ex) 
      	{
      		 System.err.println("Error writing file for run "+ run +" generation " +gen+  ex.getMessage());
      	} 
      	
      }
    
    
    /**
     * override this method in sub class
     * see e.g. in statistics.singleobjective.alps.TSPStatistics
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
       
      
       if(generation == (layer.getGenerations()-1) )
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

 
    
}
