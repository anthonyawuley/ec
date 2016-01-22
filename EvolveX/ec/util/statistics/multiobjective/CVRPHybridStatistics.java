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
package util.statistics.multiobjective;

import fitnessevaluation.eval.CVRP;
import graphing.Ordinates;
import individuals.populations.Population;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import util.Point;
import util.statistics.BasicStatistics;
import algorithms.simulatedannealing.StartTSP;

public class CVRPHybridStatistics extends BasicStatistics{

	 
	 
    //System.out.println("Using " + arguments[1]);
     //long start = System.currentTimeMillis();
     
 
	public CVRPHybridStatistics() {
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
   			statpop.add(pop.get(bestIndividuals.get(i)));
   		}
	   	
   		CVRP cvrp = new CVRP();
   		//calculate paretho fitness ranking of individuals
   		cvrp.calcGenerationalFitness(statpop,p);
   		//ArrayList<Double> genFit = cvrp.calcGenerationalFitness(statpop,p);
   		
	   	try 
	   	{
	   		File file = getIndFile(run,p,"");
	   		//if file doesnt exists, then create it
	   		if(!file.exists())
	   		{
	   			file.createNewFile();
	   		}

	   		//true = append file
	   		fw = new FileWriter(file ,true);
	   		bw = new BufferedWriter(fw);
	   		
	   		for(int i=0;i<numberOfIndividualsToPrint;i++)
	   		{
	   			ArrayList<Point> cordinates = new ArrayList<>();
	   			double totalRoute = 0;
	   			
	   			System.out.println("\nInitializing hybrid \"SimulatedAnnealing \" for best individual #"+ i+" \n");
	   			StartTSP sa = new StartTSP(p,run);
	   	       	ArrayList<ArrayList> hybridString = multipleTokenStrings(
	   	       			                                   pop.get(bestIndividuals.get(i)).getHybrid());
	   		    try 
	   		    {
	   		       
	   	       	   
	   	       	   for(int h=0;h<hybridString.size();h++)
	   	       	   {
	   	       		  String[] cord0,cord1,corda,cordz = new String[6];
	   	       	
	   	       	      if(hybridString.get(h).size()==1)     //no hybridization if only one node
 	       		      { 
	   	       	        System.out.println("Route #"+ h+" \n");
 	       		        cord0    = p.getProperty("1").split("\\s{1,}"); 
  	                    Point p0 = new Point(Double.parseDouble(cord0[0]),Double.parseDouble(cord0[1]));
  	                    corda    = p.getProperty(hybridString.get(h).get(0)+"").split("\\s{1,}"); 
 	                    Point pa = new Point(Double.parseDouble(corda[0]),Double.parseDouble(corda[1]));
    	                totalRoute += (p0.calculateEuclidianDistance(pa)*2); //distance from depot to first coordinate
    	                
    	                cordinates.add(p0);
    	                cordinates.add(pa);
    	                cordinates.add(p0);
    	                //System.out.println("Adding1 " + pa.getX() +" "+pa.getY());
    	                
 	       		      }
 	       	          else if(hybridString.get(h).size()==2) //no hybridization if only two nodes
      		          {
 	       	        	System.out.println("Route #"+ h+" \n");
      		            cord0    = p.getProperty("1").split("\\s{1,}"); 
                        Point p0 = new Point(Double.parseDouble(cord0[0]),Double.parseDouble(cord0[1]));
                        corda    = p.getProperty(hybridString.get(h).get(0)+"").split("\\s{1,}"); 
                        Point pa = new Point(Double.parseDouble(corda[0]),Double.parseDouble(corda[1]));
                        cordz    = p.getProperty(hybridString.get(h).get(1)+"").split("\\s{1,}"); 
                        Point pz = new Point(Double.parseDouble(cordz[0]),Double.parseDouble(cordz[1]));
                  
	                    totalRoute += (p0.calculateEuclidianDistance(pa)+
	                    		       pa.calculateEuclidianDistance(pz)+
	                    		       p0.calculateEuclidianDistance(pz)); //distance from depot to first coordinate
	                    
	                    cordinates.add(p0);
    	                cordinates.add(pa);
    	                cordinates.add(pz);
    	                cordinates.add(p0);
    	                //System.out.println("Adding 2 " + pa.getX() +" "+pa.getY());
      		          }
 	       	          else if(hybridString.get(h).size()>2)
	   	       		  {
 	       	        	System.out.println("Route #"+ h+" \n");
	   	       		    sa.hybrid = Boolean.TRUE;
	   	                //System.out.println("RESULTS "+ hybridString.get(h).size()); System.exit(0);
	   	       		    sa.setHybridString(hybridString.get(h));
	   	       	        totalRoute += sa.start();
	   	       	        //System.out.println("FIRST:"+hybridString.get(h).get(sa.getPath()[0])+" LAST:"+hybridString.get(h).get(sa.getPath()[sa.getPath().length - 1]));
	   	       	        cord0    = p.getProperty("1").split("\\s{1,}"); 
	 	   	            Point p0 = new Point(Double.parseDouble(cord0[0]),Double.parseDouble(cord0[1]));
	 	   	            
	 	   	            corda    = p.getProperty(hybridString.get(h).get(sa.getPath()[0])+"").split("\\s{1,}"); 
 	   	                Point pa = new Point(Double.parseDouble(corda[0]),Double.parseDouble(corda[1]));
 	   	                
 	   	                cordz    = p.getProperty(hybridString.get(h).get(sa.getPath()[sa.getPath().length - 1])+"").split("\\s{1,}"); 
	   	                Point pz = new Point(Double.parseDouble(cordz[0]),Double.parseDouble(cordz[1]));
	   	           
	 	     	        totalRoute += (p0.calculateEuclidianDistance(pa)+p0.calculateEuclidianDistance(pz)); //distance from depot to first coordinate
	 	     	        
	 	     	        //assempble cordinates
	 	     	        cordinates.add(p0);
	 	     	        //int[] print = sa.getPath();
  	                      for(int city: sa.getPath())
  	                      {
  	                    	cord1    = p.getProperty(hybridString.get(h).get(city)+"").split("\\s{1,}"); 
  		   	                Point p1 = new Point(Double.parseDouble(cord1[0]),Double.parseDouble(cord1[1]));
  		   	                //System.out.println("Adding > " + p1.getX() +" "+p1.getY());
  	                    	cordinates.add(p1);
  	                      }
  	                    cordinates.add(p0);
  	                  
	   	       		  }
	   	       	   }
	   		    } 
	   		    catch (IOException e) 
	   		    {
	   			  e.printStackTrace();
	   		    }
	   			ArrayList<Double> vrp = pop.get(bestIndividuals.get(i)).getMultipleFitness();
	   			
	   			individualsChromosome += ""+i+"\t"   //generation
				              //+  "" + genFit.get(i) +"\t"// fitness
				              +  "" + statpop.get(i).getFitness().getDouble() +"\t"// fitness
				              +  ""+ vrp.get(0) +"\t"  // number of vehicles
				              +  ""+ vrp.get(1) +"\t"  // ga route
				              +  ""+ totalRoute +"\t"; //hybrid route
	   			//PRINT cordinates hybrid + GA cordinates
	   			new Ordinates(cordinates,hybridString,p, run, gen,",",false);
	   			
	   		}
	   		//bw.write("Generation #" + gen + "\t" + individualsChromosome +"\n");
	   	    bw.write("" + gen + "\t" + individualsChromosome +"\n");
	   		bw.close();
	   	} 
	   	catch (IOException ex) 
	   	{
	   		 System.err.println("Error writing file for run "+ run +" generation " +gen+  ex.getMessage());
	   	} 
	   	
	   	
	   }
	
	
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> multipleTokenStrings(ArrayList<Integer> hybrid)
	{
		int count = 0;
		ArrayList<ArrayList> myToken = new ArrayList<>();
		ArrayList<Integer> sub = new ArrayList<>();
		
		for(int i=1;i<hybrid.size();i++) //ignore first and last string
		{
			if(hybrid.get(i) == 1) //1 is used as depot
			{
			  myToken.add(count,(ArrayList) sub.clone());
			  sub.clear();
			  count++;
			}
			else
			{
			  sub.add(hybrid.get(i));
			}
		}
		return myToken;
	}
	

}
