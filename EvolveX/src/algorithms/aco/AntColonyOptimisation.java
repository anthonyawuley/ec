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
package algorithms.aco;

import individuals.representation.ant.TSP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.Constants;
//import algorithms.aco.core.Record;
import algorithms.aco.core.WalkedPath;
import algorithms.aco.solution.Writer;
import cern.jet.random.Uniform;

public final class AntColonyOptimisation {
	

  @SuppressWarnings("unused")
private static String TSP_FILE;
  
  private Uniform uniform;
  
  private Properties p;
  private int run;
  
  protected static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(Constants.POOL_SIZE);

  protected final ExecutorCompletionService<WalkedPath> agentCompletionService = 
		  new ExecutorCompletionService<WalkedPath>(THREAD_POOL);
  
 

  final double[][] matrix;
  final double[][] invertedMatrix;

  private final double[][] pheromones;
  private final Object[][] mutexes;
  
  
  public AntColonyOptimisation() throws IOException 
  {
	    // read the matrix
	    matrix = (new TSP()).readMatrixFromFile(new Properties()); //change null object later
	  
	    invertedMatrix = invertMatrix();
	    pheromones = initializePheromones();
	    mutexes = initializeMutexObjects();
	    uniform = new Uniform(0, matrix.length - 1,(int) System.currentTimeMillis());
  }

  /**
   * 
   * @param arg_file
   * @throws IOException
   */
  public AntColonyOptimisation(Properties prop, int runNum) throws IOException 
  {
	    //rep = new Representation();
	    //TSP_FILE = arg_file;
	    p = prop;
	    run = runNum;
	    // read the matrix
	    matrix = (new TSP()).readMatrixFromFile(prop);
	    
	   /* testing ajecency matrix 
	    for(double j []: matrix)
	    {
	    	for(double i : j)
	    	{
	    		System.out.print(i +" ");
	    	} System.out.println();
	    	
	    }
	    */
	    
	    invertedMatrix = invertMatrix();
	    pheromones = initializePheromones();
	    mutexes = initializeMutexObjects();
	    uniform = new Uniform(0, matrix.length - 1,(int) System.currentTimeMillis());
  }
  
  /**
   * Create nXn matrix of new objects
   * @return
   */
  private final Object[][] initializeMutexObjects() 
  {
    final Object[][] localMatrix = new Object[matrix.length][matrix.length];
    int rows = matrix.length;
    
    for (int columns = 0; columns < matrix.length; columns++) 
    {
      for (int i = 0; i < rows; i++) 
      {
        localMatrix[columns][i] = new Object();
      }
    }
    return localMatrix;
  }

  /**
   * REad amount of pheromone deposited on the edge x to y
   * @param x
   * @param y
   * @return
   */
  final double readPheromone(int x, int y) 
  {
    return pheromones[x][y];
  }

  
  /**
   * UPDate the amount of absolute pheromone which gets deposited for worker "k" on the edge to "xy"
   * 
   * @param x
   * @param y
   * @param updated amount of absolute pheromone which gets deposited for worker "k" on the edge to "xy"
   */
  final void adjustPheromone(int x, int y, double newPheromone) 
  {
    synchronized (mutexes[x][y]) 
    {
      final double result = calculatePheromones(pheromones[x][y], newPheromone);
      if (result >= 0.0d) 
      {
        pheromones[x][y] = result;
      } 
      else 
      {
        pheromones[x][y] = 0;
      }
    }
  }

  /**
   * "current" is the amount of absolute pheromone which gets deposited for worker "i" on the edge to "xy". 
   * "Constants.PHEROMONE_PERSISTENCE" is the factor between 0-1 which represents the decay of the pheromone. 
   * This gets multiplied by the current amount of pheromone deposited and we just add the new pheromone to 
   * it (which is the delta "current").
   * 
   * @param current
   * @param newPheromone
   * @return how much pheromone does a worker lay while traversing the edges
   */
  private final double calculatePheromones(double current, double newPheromone) 
  {
    final double result = (1 - Constants.PHEROMONE_PERSISTENCE)
        * current + newPheromone;
    return result;
  }

  
  /**
   * Calculate apriori knowledge of how "good" the edge is. 
   * In our case this is the inverted distance: 1/distance.
   * 
   * @return 1/distance matrix
   */
  private final double[][] initializePheromones() 
  {
    final double[][] localMatrix = new double[matrix.length][matrix.length];
    int rows = matrix.length;
    for (int columns = 0; columns < matrix.length; columns++) 
    {
      for (int i = 0; i < rows; i++) 
      {
        localMatrix[columns][i] = Constants.INITIAL_PHEROMONES;
      }
    }

    return localMatrix;
  }

  
  /**
   * Generate a matrix with inverse values
   * 
   * @return new inverted value
   */
  private final double[][] invertMatrix() 
  {
    double[][] local = new double[matrix.length][matrix.length];
    for (int i = 0; i < matrix.length; i++) 
    {
      for (int j = 0; j < matrix.length; j++) 
      {
        local[i][j] = invertDouble(matrix[i][j]);
      }
    }
    return local;
  }

  /**
   * Calculate inverse of distance
   * 
   * @param distance
   * @return
   */
  private final double invertDouble(double distance) 
  {
    if (distance == 0d)
      return 0d;
    else
      return 1.0d / distance;
  }

  /**
   * 
   * @param x1
   * @param y1
   * @param x2
   * @param y2
   * @return
  
  private final double calculateEuclidianDistance(double x1, double y1,
      double x2, double y2) 
  {
    final double xDiff = x2 - x1;
    final double yDiff = y2 - y1;
    return Math.abs((Math.sqrt((xDiff * xDiff) + (yDiff * yDiff))));
  }
 */
 
  /**
   * 
   * @return
   * @throws InterruptedException
   * @throws ExecutionException
   */
  public final double start() throws InterruptedException, ExecutionException 
  {

    WalkedPath bestDistance = null;
    ArrayList<Double> bestDistances = new ArrayList<>();
    int agentsSend = 0;
    int agentsDone = 0;
    int agentsWorking = 0;
    
    for (int agentNumber = 0; agentNumber < Constants.NUM_AGENTS; agentNumber++) 
    {
      agentCompletionService.submit(new Agent(this,getGaussianDistributionRowIndex()));
      agentsSend++;
      agentsWorking++;
      while (agentsWorking >= Constants.POOL_SIZE) 
      {
        WalkedPath way = agentCompletionService.take().get();
        if (bestDistance == null || way.getDistance() < bestDistance.getDistance()) 
        { 
          bestDistance = way;
          bestDistances.add(way.getDistance());
          System.out.println("Agent returned with new best distance of: " + way.getDistance());
        }
        agentsDone++;
        agentsWorking--;
      }
      
    }
    
    final int left = agentsSend - agentsDone;
    System.out.println("Waiting for " + left + " agents to finish their random walk!");

    for (int i = 0; i < left; i++) 
    {
      WalkedPath way = agentCompletionService.take().get();
      if (bestDistance == null || way.getDistance() < bestDistance.getDistance()) 
      {
        bestDistance = way;
        bestDistances.add(way.getDistance());
        System.out.println("Agent returned with new best distance of: "
            + way.getDistance());
      }
    }

    //THREAD_POOL.shutdownNow();
    
    System.out.println("Found best so far: " + bestDistance.getDistance() + "");
    
    /**
     * print results
     */
    new Writer(p,run,bestDistance.getWay(),bestDistances);
    
    //System.out.println(Arrays.toString(bestDistance.getWay()));
    
    return bestDistance.getDistance();

  }

  /**
   * 
   * @return
   */
  private final int getGaussianDistributionRowIndex() 
  {
    return uniform.nextInt();
  }

}