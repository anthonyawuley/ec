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
package graphing;

import individuals.Chromosome;
import individuals.populations.Population;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import util.Constants;
import util.Point;

public class Ordinates{

	String append = "";
	
	public Ordinates(Chromosome c,
			Properties p,
			int run,
			int generation,
			String seperator) 
	{
		  writeToFileVRPTW(c,p,run,generation,seperator);
	}

    
	/**
	 *  ploting cordinates
	 * @param cordinates
	 * @param p
	 * @param run
	 * @param generation
	 * @param seperator
	 */
	@SuppressWarnings("rawtypes")
	public Ordinates(ArrayList<Point> cordinates,
			ArrayList<ArrayList> rawGA,
			Properties p,
			int run,
			int generation,
			String seperator,
			boolean sa) 
	{
		if(sa) //PRINT only SA
		{
			writeToFileCVRP(cordinates,p,run,generation,seperator);
		}
		else //print both ga and SA
		{
			writeToFileCVRP(cordinates,p,run,generation,seperator);
			writeToFileCVRPGA(rawGA,p,run,generation,seperator);	
		}
		  
		  
	}
	
	
   /**
    * 
    * @param pop
    * @param bestIndividuals
    * @param p
    * @param run
    * @param generation
    * @param seperator
    * @param type tsp or vrp
    */
   public Ordinates(Population pop,
		   ArrayList<Integer> bestIndividuals,
		   int numberOfIndividualsToPrint,
		   Properties p,int run,
		   int generation,
		   String seperator,String type) 
   {
		//setChromosome(c);
		//setProperties(p);
		for(int i=0;i<numberOfIndividualsToPrint;i++)
		{
			append = i+"";
			
			switch(type)
			{
			   case "vrp":
				   writeToFileVRPTW(pop.get(bestIndividuals.get(i)).getChromosome(),p,run,generation,seperator);
				break;
			   default:
				   writeToFileTSP(pop.get(bestIndividuals.get(i)).getChromosome(),p,run,generation,seperator);
				break;
			}
			
		}
		// TODO Auto-generated constructor stub
		//writeToFile();
	}
	
	/**
	 * 
	 * @param c
	 * @param p
	 * @param run
	 * @param generation
	 * @param seperator
	 * @deprecated note writeToFileVRPTW1 is more modern than writeToFileVRPTW2
	 */
    @SuppressWarnings({ "unused", "unchecked" })
    public void writeToFileVRPTW1(Chromosome c,Properties p,int run, int generation,String seperator)
	{
		try 
		{
			
			File file = new File(Constants.DEFAULT_PARAM_ROOT+"cord_"+run+"_"+
			                    p.getProperty(Constants.STATS_FILE)+"_"+append+generation+
			                    Constants.DEFAULT_STATS_EXTENSION+".csv");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			/*
	    	 * node.1.x         = 40.00
	         * node.1.y         = 50.00
	    	 */
			String[] cord0,cordz,corda,cordi0,cordi1= new String[6];
	    	String[] params = new String[6];
	    	ArrayList<Double>  vrptw = new ArrayList<>();
	    	ArrayList<Integer> chrom = (ArrayList<Integer>) c.convertToInt().clone();
	    	int lastLocation = 0;
	    	double totalDistance = 0;
	     	int vCount = 1;
	      	
		    /*
	    	 * for each customer indexes cord are distributed as below
	    	 * CUST NO.   XCOORD.    YCOORD.    DEMAND   READY TIME   DUE DATE   SERVICE TIME
	    	 *              0          1           2         3            4            5
	    	 */
	     	
	     	int vCapacity = Integer.parseInt(p.getProperty(Constants.VEHICLE_CAPACITY));
	 	    cord0    = p.getProperty(Constants.CO_ORDINATES+".1").split("\\s{1,}"); //get depo cordinates
	   	    Point p0 = new Point(Double.parseDouble(cord0[0]),Double.parseDouble(cord0[1]));
	   	    Point pi1 = p0; //initialize end point to start point
	   	    
	 	    while(chrom.size()>0)
	 	    { 
	 	    	double sumOfDemand = 0;
	 	    	double distanceTime  = 0;
	 	    	int skippedCount = 0;
	 	 	    
	 	 	    bw.write((int) Double.parseDouble(cord0[0])+seperator+(int) Double.parseDouble(cord0[1])+"\n"); //always end with beginning cordinate
			   
	 	 	    corda    = p.getProperty(Constants.CO_ORDINATES+"."+(chrom.get(0)+1)).split("\\s{1,}"); //get first cordinate in chromosome
	 	   	    Point pa = new Point(Double.parseDouble(corda[0]),Double.parseDouble(corda[1]));
	 	   	    
	 	     	//totalDistance = p0.cartesianDistance(pa); //distance from depot to first coordinate
	            //distanceTime  = p0.cartesianDistance(pa); //time from depot to first coordinate 
	 	     	
	 	 	    for(int i=0; i<chrom.size();i++)
	 	        {
	 	 	    	params        = p.getProperty(Constants.CO_ORDINATES+"."+(chrom.get(i)+1)).split("\\s{1,}");
	 		    	double demand = Double.parseDouble(params[2]); 
	 		    
	 		    	cordi0     = p.getProperty(Constants.CO_ORDINATES+"."+(chrom.get(i)+1)).split("\\s{1,}"); //get current cordinate
	 	            Point pi0 = new Point(Double.parseDouble(cordi0[0]),Double.parseDouble(cordi0[1]));
	 	            
	 	            if( ((distanceTime+Double.parseDouble(cordi0[5])) < Double.parseDouble(cordi0[4])) && ((sumOfDemand+demand) < vCapacity) ) //compare current distance-time to due date
	 	    	    { 	
	 	            	int j = (i-skippedCount-1)>0?i-skippedCount-1:0; //estimate the last point that was added
	 	 		    	cordi1      = p.getProperty(Constants.CO_ORDINATES+"."+(chrom.get(j)+1)).split("\\s{1,}"); //get previously visited cordinate
	 	 	            pi1 = new Point(Double.parseDouble(cordi1[0]),Double.parseDouble(cordi1[1]));
	 	 	            
	 	 	            //totalDistance += pi0.cartesianDistance(pi1);
	 	 	            distanceTime  += pi0.calculateEuclidianDistance(pi1); //estimate time traveled so far by adding cartesian distance from visited nodes
	 	 	            
	 	 	            bw.write((int) Double.parseDouble(cordi1[0])+seperator+(int) Double.parseDouble(cordi1[1])+"\n"); //always end with beginning cordinate
	 	 	   		   
	 	 	            if(distanceTime <= Double.parseDouble(cordi0[3]))
	 	 	            	distanceTime = Double.parseDouble(cordi0[3]) + Double.parseDouble(cordi0[5]); 
	 	 	            else
	 	 	            	distanceTime += ( Double.parseDouble(cordi0[5])); //add service time
	 	 	            
	 	 	            
	 	 	            sumOfDemand += demand;
	 	                skippedCount = 0; //reset skipped counter anytime an assignable customer is found
	 	            	lastLocation = i;
	 	            	chrom.remove(i);  //remove assigned customer from array
	 		        }     
	 	            else
	 	            { 
	 	            	skippedCount++;
	 	            }
	 	             
	 	        }
	 	 	   bw.write((int) Double.parseDouble(cord0[0])+seperator+(int) Double.parseDouble(cord0[1])+"\n"); //always end with beginning cordinate
	 	 	    /**
	        	 * get last coordinate in previous route
	        	 * This will be used to complete total distance traveled by previous route
	        	 * by adding distance from this point to depot
	        	 */
	 	 	     //totalDistance += p0.cartesianDistance(pi1) +  // depot to last node in previous route
	            
	 	 	  vCount++; //counting assigned cars
	 	    }
	 	    
	 	   bw.close();
	      	
			//System.out.println("Done");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
   
   
   
	/**
	 * second route estimator
	 * @param c
	 * @param p
	 * @param run
	 * @param generation
	 * @param seperator
	 * @deprecated
	 */
	@SuppressWarnings("unused")
	public void writeToFileVRPTW2(Chromosome c,Properties p,int run, int generation,String seperator)
	{
		try 
		{
			
			File file = new File(Constants.DEFAULT_PARAM_ROOT+"cord_"+run+"_"+
			                    p.getProperty(Constants.STATS_FILE)+"_"+append+generation+
			                    Constants.DEFAULT_STATS_EXTENSION+".csv");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			/*
	      	 * node.1.x         = 40.00
	         * node.1.y         = 50.00
	      	 */
	      	String[] cord0,cordi0, cordi1 = new String[6];
	      	String[] cord1 = new String[6];
	      	
	      	String[] params = new String[6];
	      	double sumOfDemand = 0;
	      	int vCount = 0;
		    int j;
	      	
	      	/*
	    	 * for each customer indexes cord are distributed as below
	    	 * CUST NO.   XCOORD.    YCOORD.    DEMAND   READY TIME   DUE DATE   SERVICE TIME
	    	 *              0          1           2         3            4            5
	    	 */
	     	//System.out.println("#capacity: "+p.getProperty(Constants.VEHICLE_CAPACITY));
	 	    int vCapacity = Integer.parseInt(p.getProperty(Constants.VEHICLE_CAPACITY));
	 	    
	 	    cord0    = p.getProperty(Constants.CO_ORDINATES+".1").split("\\s{1,}"); //get depo cordinates
	   	    Point p0 = new Point(Double.parseDouble(cord0[0]),Double.parseDouble(cord0[1]));
	   	    
	   	    bw.write((int) Double.parseDouble(cord0[0])+seperator+(int) Double.parseDouble(cord0[1])+"\n");
	   	    //TODO
	   	    cord1    = p.getProperty(Constants.CO_ORDINATES+"."+(c.getGenes().get(0).getId())).split("\\s{1,}"); //get depo cordinates
		    Point p1 = new Point(Double.parseDouble(cord0[0]),Double.parseDouble(cord0[1]));
		    
	 	    double distanceTime = -p0.calculateEuclidianDistance(p1); //initialise distance to p0-p1
	 	    double totalDistance = p0.calculateEuclidianDistance(p1); //set total 
	 	    
	 	    for(int i=0; i<c.getGenes().size();i++)
	        {
	        	/*
	        	 * when genes are exhausted, loop back j to one
	        	 * so cycle completes e.g. 2-4-5--1-3-2, 4-2-5-3-1-4 ... etc
	        	 */
	        	j = (i==c.getGenes().size()-1)?0:i+1;
	        	//note use of i+1 & j+1 to reference cordinates, because of cordinate numbering in param file
		    	params        = p.getProperty(Constants.CO_ORDINATES+"."+(c.getGenes().get(i).getId()+1)).split("\\s{1,}");
		    	double demand = Double.parseDouble(params[2]);
	 	    	/*
	 	    	 * keep adding demand until vehicle capacity is full
	 	    	 * it its full, start loading an empty truck
	 	    	 */
		    	cordi0     = p.getProperty(Constants.CO_ORDINATES+"."+(c.getGenes().get(i).getId()+1)).split("\\s{1,}");
	            Point pi0 = new Point(Double.parseDouble(cordi0[0]),Double.parseDouble(cordi0[1]));
		    	cordi1      = p.getProperty(Constants.CO_ORDINATES+"."+(c.getGenes().get(j).getId()+1)).split("\\s{1,}");
	            Point pi1 = new Point(Double.parseDouble(cordi1[0]),Double.parseDouble(cordi1[1]));
	    	    
	            //distanceTime += Double.parseDouble(cordi0[3]) + Double.parseDouble(cordi0[5]); //ready time + service time
	            	
	            if( (distanceTime < Double.parseDouble(cordi0[4])) && (sumOfDemand < vCapacity) ) //compare current distance-time to due date
	    	    { 	
	            	distanceTime += (Double.parseDouble(cordi0[3]) - pi0.calculateEuclidianDistance(pi1));//  + Double.parseDouble(cordi0[5]); //ready time + service time
	            	bw.write((int) Double.parseDouble(cordi0[0])+seperator+(int) Double.parseDouble(cordi0[1])+"\n");
	            	sumOfDemand+=demand;
		        }
		        else
		    	{ 
		        	distanceTime   = (Double.parseDouble(cordi0[3]) - p0.calculateEuclidianDistance(pi0));//  + Double.parseDouble(cordi0[5]); //initialise distance to p0-p1
		        	totalDistance += (distanceTime+ p0.calculateEuclidianDistance(p1)); //;
		        	sumOfDemand    = demand;
		        	
		        	bw.write((int) Double.parseDouble(cord0[0])+seperator+(int) Double.parseDouble(cord0[1])+"\n");
	  	    		//buggy
	  	    		bw.write((int) Double.parseDouble(cordi0[0])+seperator+(int) Double.parseDouble(cordi0[1])+"\n");
		        	
		        	vCount++;
		    	}
	          }
	 	   bw.write((int) Double.parseDouble(cord0[0])+seperator+(int) Double.parseDouble(cord0[1])+"\n"); //always end with beginning cordinate
		   bw.close();
		  //System.out.println("Done");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void writeToFileVRPTW(Chromosome c,Properties p,int run, int generation,String seperator)
	{
		try 
		{
			
			File file = new File(Constants.DEFAULT_PARAM_ROOT+"cord_"+run+"_"+
			                     p.getProperty(Constants.STATS_FILE)+"_"+append+generation+
			                     Constants.DEFAULT_STATS_EXTENSION+".csv");
 
			// if file doesnt exists, then create it
			if (!file.exists()) 
				file.createNewFile();
			
 
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			/*
	      	 * node.1.x         = 40.00
	         * node.1.y         = 50.00
	      	 */
	      	String[] cord0 = new String[6];
	      	String[] cord1 = new String[6];
	      	String[] params = new String[6];
	      	double sum = 0;
	     	/*
	     	 * for each customer indexes cord are distributed as below
	     	 * CUST NO.   XCOORD.    YCOORD.    DEMAND   READY TIME   DUE DATE   SERVICE TIME
	     	 *              0          1           2         3            4            5
	     	 */
	  	   
	  	    int vCapacity = Integer.parseInt(p.getProperty(Constants.VEHICLE_CAPACITY));
	  	    cord0    = p.getProperty(Constants.CO_ORDINATES+".1").split("\\s{1,}"); //get starting point
	  	    bw.write((int) Double.parseDouble(cord0[0])+seperator+(int) Double.parseDouble(cord0[1])+"\n");
	  	    
	  	    for(int i=0; i<c.getGenes().size();i++)
	        {
	          	/*
	          	 * when genes are exhausted, loop back j to one
	          	 * so cycle completes e.g. 2-4-5--1-3-2, 4-2-5-3-1-4 ... etc
	          	 */
	          	//j = (i==c.getChromosome().size()-1)?0:i+1;
	          	
	  	    	params        = p.getProperty(Constants.CO_ORDINATES+"."+c.getGenes().get(i)).split("\\s{1,}");
	  	    	double demand = Double.parseDouble(params[2]);
	  	    	cord1    = p.getProperty(Constants.CO_ORDINATES+"."+c.getGenes().get(i)).split("\\s{1,}");
  	    		
	  	    	/*
	  	    	 * keep adding demand until vehicle capacity is full
	  	    	 * it its full, start loading an empty truck
	  	    	 */
	  	    	if( (sum+demand) <= vCapacity)
	  	    	{
	  	    		bw.write((int) Double.parseDouble(cord1[0])+seperator+(int) Double.parseDouble(cord1[1])+"\n");
	  	    		//cord2    = p.getProperty(Constants.CO_ORDINATES+"."+c.getChromosome().get(j)).split("\\s{1,}");
	      	    	//bw.write((int) Double.parseDouble(cord2[0])+","+(int) Double.parseDouble(cord2[1])+"\n");
	  	    		sum+=demand;
	  	    	}
	  	    	else
	  	    	{ 
	  	    		bw.write((int) Double.parseDouble(cord0[0])+seperator+(int) Double.parseDouble(cord0[1])+"\n");
	  	    		//buggy
	  	    		bw.write((int) Double.parseDouble(cord1[0])+seperator+(int) Double.parseDouble(cord1[1])+"\n");
	  	    		sum = demand;
	  	    	}
	         }
	  	    bw.write((int) Double.parseDouble(cord0[0])+seperator+(int) Double.parseDouble(cord0[1])+"\n"); //always end with beginning cordinate
			bw.close();
			//System.out.println("Done");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * @param cordinates take already defined cordinates and print
	 * @param p
	 * @param run
	 * @param generation
	 * @param seperator
	 */
	public void writeToFileCVRP(ArrayList<Point> cordinates,Properties p,int run, int generation,String seperator)
	{ 
		try 
		{
			File file = new File(Constants.DEFAULT_PARAM_ROOT+"cord_hy_"+run+"_"+
			                    p.getProperty(Constants.STATS_FILE)+"_"+append+generation+
			                    Constants.DEFAULT_STATS_EXTENSION+".csv");
			// if file doesnt exists, then create it
			if (!file.exists()) 
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
	  	    for(Point point: cordinates)
	          bw.write((int)point.getX() +seperator+ (int)point.getY()+"\n");
	        
	  	  bw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public void writeToFileCVRPGA(ArrayList<ArrayList> route,Properties p,int run, int generation,String seperator)
	{
		try 
		{
			
			File file = new File(Constants.DEFAULT_PARAM_ROOT+"cord_ga_"+run+"_"+
			                    p.getProperty(Constants.STATS_FILE)+"_"+append+generation+
			                    Constants.DEFAULT_STATS_EXTENSION+".csv");
 
			// if file doesnt exists, then create it
			if (!file.exists())
				file.createNewFile();
 
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			/*
	      	 * node.1.x         = 40.00
	         * node.1.y         = 50.00
	      	 */
	      	String[] cord0 = new String[6];
	      	String[] corda = new String[6];
	     
	  	    cord0    = p.getProperty("1").split("\\s{1,}"); //get starting point
	  	    
	  	    for(int i=0; i<route.size();i++)
	        {
	  	    	//BEGIN depot
	  	    	bw.write((int) Double.parseDouble(cord0[0])+seperator+(int) Double.parseDouble(cord0[1])+"\n");
		  	    
	  	    	for(int j=0;j<route.get(i).size();j++)
	  	    	{
	  	    	   corda    = p.getProperty(route.get(i).get(j)+"").split("\\s{1,}"); 
	  	    	   bw.write((int) Double.parseDouble(corda[0])+seperator+(int) Double.parseDouble(corda[1])+"\n");
	  	    	}
	  	    	//END depot
	  	    	bw.write((int) Double.parseDouble(cord0[0])+seperator+(int) Double.parseDouble(cord0[1])+"\n");
	  	    	
	         }
	  	     bw.close();
			//System.out.println("Done");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * 
	 * @param c
	 * @param p
	 * @param run
	 * @param generation
	 * @param seperator
	 * 
	 * NODE_COORD_SECTION
     * 1 0.00000e+00 0.00000e+00
     * 2 1.11630e+03 1.55520e+03
     * 3 1.35760e+03 1.47900e+03
     * 4 1.14810e+03 1.77110e+03
     * 5 1.18620e+03 1.79650e+03
	 */
	public void writeToFileTSP(Chromosome c,Properties p,int run, int generation,String seperator)
	{
		try 
		{
			File file = new File(Constants.DEFAULT_PARAM_ROOT+"cord_"+run+"_"+
			                    p.getProperty(Constants.STATS_FILE)+"_"+append+generation+
			                    Constants.DEFAULT_STATS_EXTENSION+".csv");
			// if file doesnt exists, then create it
			if (!file.exists()) 
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			/*
	      	 * node.1.x         = 40.00
	         * node.1.y         = 50.00
	      	 */
	      	//String[] cord0 = new String[6];
	      	//String[] cord1 = new String[6];
	        /*
	         * when genes are exhausted, loop back j to one
	         * so cycle completes e.g. 2-4-5--1-3-2, 4-2-5-3-1-4 ... etc
	         */
	  	    for(int i=0; i<c.getGenes().size();i++)
	        {
	  	       //cord1    = p.getProperty("node."+c.getChromosome().get(i)).split("\\s{1,}");
	  	       //cord1    = p.getProperty(""+c.getGenes().get(i)).split("\\s{1,}");
	  	       //bw.write((int) Double.parseDouble(cord1[0])+seperator+(int) Double.parseDouble(cord1[1])+"\n");
	  	       bw.write((int) c.getGenes().get(i).getAlleles()[0]+seperator+
	  	    		    (int) c.getGenes().get(i).getAlleles()[1]+"\n");
	         }
	  	    //cord0    = p.getProperty(""+c.getGenes().get(0)).split("\\s{1,}"); 
	  	    //cord0    = p.getProperty("node."+c.getChromosome().get(0)).split("\\s{1,}");
	  	    //bw.write((int) Double.parseDouble(cord0[0])+seperator+(int) Double.parseDouble(cord0[1])+"\n"); //always end with beginning cordinate
	  	    bw.write((int) c.getGenes().get(0).getAlleles()[0]+seperator+
	  	    		(int) c.getGenes().get(0).getAlleles()[1]+"\n"); //always end with beginning cordinate
			bw.close();
			//System.out.println("Done");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	

}
