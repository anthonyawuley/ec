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
package ec.algorithms.simulatedannealing.solution;

import ec.graphing.Ordinates;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import ec.util.Point;
import ec.util.statistics.multiobjective.CVRPHybridStatistics;
import ec.algorithms.simulatedannealing.StartTSP;

public class Writer{

	public Writer() {
		// TODO Auto-generated constructor stub
	}
	
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public void writeIndividuals(
	   		 Properties p,
	   		 int run,
	   		 ArrayList<Integer> routes)
	   {
		
		CVRPHybridStatistics hs = new CVRPHybridStatistics();
		 
	   	FileWriter fw = null;
	   	BufferedWriter bw = null;
	   	String individualsChromosome = "";

   		
	   	try 
	   	{
	   		File file = hs.getIndFile(run,p,"");
	   		//if file doesnt exists, then create it
	   		if(!file.exists())
	   		{
	   			file.createNewFile();
	   		}

	   		//true = append file
	   		fw = new FileWriter(file ,true);
	   		bw = new BufferedWriter(fw);
	   		
	   		
	   			ArrayList<Point> cordinates = new ArrayList<>();
	   			double totalRoute = 0;
	   			
	   			System.out.println("\nInitializing hybrid \"SimulatedAnnealing...\n");
	   			StartTSP sa = new StartTSP(p,run);
	   	       	ArrayList<ArrayList> hybridString = hs.multipleTokenStrings(routes);
	   		    try 
	   		    {
	   	       	   for(int h=0;h<hybridString.size();h++)
	   	       	   {
	   	       		  String[] cord0,cord1,corda,cordz = new String[6];
	   	       	
	   	       	      if(hybridString.get(h).size()==1)                     //no hybridization if only one node
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
	   		 
	   			//ArrayList<Double> vrp = pop.get(bestIndividuals.get(i)).getMultipleFitness();
	   			
	   			individualsChromosome += ""+hybridString.size()+"\t"
				              //+  "" + genFit.get(i) +"\t"
				              //+  ""+ vrp.get(0) +"\t"
				              //+  ""+ vrp.get(1) +"\t"
				              +  ""+ totalRoute +"\t";
	   			
	   			//PRINT cordinates hybrid + GA cordinates
	   			new Ordinates(cordinates,hybridString,p, run, 0,",",true);
	   		
	   		//bw.write("Generation #" + gen + "\t" + individualsChromosome +"\n");
	   	    bw.write("" + run + "\t" + individualsChromosome +"\n");
	   		bw.close();
	   	} 
	   	catch (IOException ex) 
	   	{
	   		 System.err.println("Error writing file for run "+ run +  ex.getMessage());
	   	} 
	   	
	   	
	   }
	
	
	

}
