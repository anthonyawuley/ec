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
package util.statistics;

import fitnessevaluation.eval.WS;
import graphing.Ordinates;
import individuals.fitnesspackage.PopulationFitness;
import individuals.populations.Population;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import algorithms.alps.layers.Layer;
import util.Constants;

/**
 *
 * @author anthony
 */
public class BasicStatistics implements StatisticsCollector{
    
    public void printReportHeader()
    {
    
    }
    
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
     * OVER LOADED FOR ALPS
     */
    public  void printStatsReport(
    		Population pop,
    		Properties p, 
    		ArrayList<Integer> bestIndividuals,
    		final Layer layer,
    		final int generation, 
    		PopulationFitness f)
    {
       System.out.println("Best:      "+ f.getBestFitness() +"\t"
       		            + "Average:   "+ f.getAverageFitness() +"\t"
       		            + "SD:        "+ f.getStandardDeviationFitness() +"\n");
       
       int numberOfIndividualsToPrint = Integer.parseInt(p.getProperty(Constants.NUMBER_INDIVIDUALS_PRINT));
       
      
       if(generation == (layer.getGenerations()-1) )
       {
    	   /**
    	    * report type indicates if tsp or vrp will be printed
    	    * set value in Constants file
    	    */
    	   
          new Ordinates(pop,bestIndividuals,numberOfIndividualsToPrint,p, 
        		  layer.getMaxAge(), generation,",",Constants.REPORT_TYPE);
          //new Ordinates(pop.get(bestIndividuals.get(0)),p, run, generation,",");
          //new Plot(pop.get(bestIndividuals.get(0)),p);
       }
       
       generateStatFile(p, layer.getMaxAge(), generation, f.getBestFitness(), 
    		   f.getAverageFitness(), f.getStandardDeviationFitness());
       //System.out.println(pop.get(bestIndividuals.get(1)).getChromosome());
       
       writeIndividuals(pop,p, layer.getMaxAge(), generation,bestIndividuals,numberOfIndividualsToPrint);
       
    }
    
    
    
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
    		File file = getStatsFile(run,p);
    		
    		//if file doesnt exists, then create it
    		if(!file.exists()) 
    		{ file.createNewFile();}
    		
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
    
    public File getStatsFile(int run,Properties p)
    {
    	return new File(Constants.DEFAULT_PARAM_ROOT+
   				"stat_"+run+"_"+
   				p.getProperty(Constants.STATS_FILE)+
   				Constants.DEFAULT_STATS_EXTENSION);
    }
    
    
    public File getIndFile(int run,Properties p)
    {
    	return new File(Constants.DEFAULT_PARAM_ROOT+
   				"ind_"+run+"_"+
   				p.getProperty(Constants.STATS_FILE)+
   				Constants.DEFAULT_STATS_EXTENSION);
    }
    
    
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
   		 
   		File file = getIndFile(run,p);
   		
   		//if file doesnt exists, then create it
   		if(!file.exists())
   		{
   			file.createNewFile();
   		}

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
    
    
    
}
