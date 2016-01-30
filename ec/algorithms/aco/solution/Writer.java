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
package ec.algorithms.aco.solution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import ec.util.Constants;



public  class Writer {
  
  private static int[] arr;
  @SuppressWarnings("unused")
  private Properties p;
  private ArrayList<Double> bestDistance;
	
  public Writer(Properties prop,int run, int[] a, ArrayList<Double> bestDist)
  {
	  arr = a;
	  p   = prop;
	  bestDistance = bestDist;
	  
    try 
    {
		swTSP(prop,run);
		bd(prop,run);
	} 
    catch (NumberFormatException e) 
    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
	
  
  /**
   * 
   * @param p
   * @param run
   * @throws NumberFormatException
   * @throws IOException
   * 
   * #CUST NO.   XCOORD.   YCOORD.    DEMAND   READY TIME   DUE DATE   SERVICE TIME
   * node.1      40.00      50.00       0.00       0.00    1236.00       0.00 
   * node.2      45.00      68.00      10.00       0.00    1127.00      90.00 
   * node.3      45.00      70.00      30.00       0.00    1125.00      90.00 
   * node.4      42.00      66.00      10.00       0.00    1129.00      90.00 
   * ....
   */
  public void swVRP(Properties p,int run) throws NumberFormatException,IOException 
  {
    String[] cord0 = new String[6];
    BufferedWriter writer = new BufferedWriter(new FileWriter(
    		new File(Constants.DEFAULT_PARAM_ROOT
    				+p.getProperty(Constants.STATS_FILE)+ "_cord_" + run +".tsp")));
    
    //System.out.println("hello:"+arr.length);
    for (int j = 0; j < arr.length; j++) 
    {
      int i = arr[j];
      cord0    = p.getProperty(Constants.CO_ORDINATES+"."+(i+1)).split("\\s{1,}");
      writer.write(Double.parseDouble(cord0[0]) + " " + Double.parseDouble(cord0[1]) + "\n");
    }
    cord0    = p.getProperty(Constants.CO_ORDINATES+"."+(arr[0]+1)).split("\\s{1,}");
    writer.write(Double.parseDouble(cord0[0]) + " " + Double.parseDouble(cord0[1]) + "\n");
    
    writer.flush();
    writer.close();
  }
  
  /**
   * 
   * @param p
   * @param run
   * @throws NumberFormatException
   * @throws IOException
   *  
   * 1 288 149
   * 2 288 129
   * 3 270 133
   * 4 256 141
   * ....
   */
  public void swTSP(Properties p,int run) throws NumberFormatException,IOException 
  {
    String[] cord0 = new String[6];
    BufferedWriter writer = new BufferedWriter(new FileWriter(
    		new File(Constants.DEFAULT_PARAM_ROOT+
    				p.getProperty(Constants.STATS_FILE)+ "_cord_" + run +".tsp")));
    //System.out.println("hello:"+arr.length);
    for (int j = 0; j < arr.length; j++) 
    {
      int i = arr[j];
      cord0    = p.getProperty(""+(i+1)).split("\\s{1,}");
      writer.write(Double.parseDouble(cord0[0]) + " " + Double.parseDouble(cord0[1]) + "\n");
    }
    cord0    = p.getProperty(""+(arr[0]+1)).split("\\s{1,}");
    writer.write(Double.parseDouble(cord0[0]) + " " + Double.parseDouble(cord0[1]) + "\n");
    
    writer.flush();
    writer.close();
  }
  
  
  public void bd(Properties p,int run) throws NumberFormatException,IOException 
  {
     
    BufferedWriter writer = new BufferedWriter(new FileWriter(new File(Constants.DEFAULT_PARAM_ROOT+p.getProperty(Constants.STATS_FILE)+ "_dist_" + run +".tsp")));
    //System.out.println("hello:"+arr.length);
    for (int j = 0; j < bestDistance.size(); j++) 
    {
      writer.write(bestDistance.get(j) + "\n");
    }
    
    writer.flush();
    writer.close();
  }
  
  
}