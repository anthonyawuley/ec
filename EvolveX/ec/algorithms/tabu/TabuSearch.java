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
package algorithms.tabu;

import individuals.representation.tabu.AMatrix;
import individuals.representation.tabu.TSP;

import java.io.IOException;
import java.util.Properties;

import util.Constants;

/**
*
* @author http://voidException.weebly.com
* Use this code at your own risk ;)
*/
public class TabuSearch {

	Properties p;
	int run,numberOfIterations,tabuLength;
	int [] currentSolution;
	int numberOfNodes;
	double [][] adjencyDistanceMatrix;
	
	public TabuSearch(Properties prop, int runNum) throws IOException
	{
		this.p = prop;
		this.run = runNum;
		this.numberOfIterations = Constants.NUMBER_OF_ITERATIONS;
        this.numberOfNodes = Integer.parseInt(this.p.getProperty("nodes"));
        		
		//city numbers start from 0
		//the first and last cities' positions do not change
	    //this.currSolution = new int[]{0, 1, 2, 3, 4, 0};   //initial solution
	    //this.tabuLength = 10; //must be equal to the length of the chromosome
	    
	    //this.currentSolution = new int[]{0, 1, 2, 9, 4, 5, 6, 7, 8, 3, 0};   //initial solution
		
		
	    this.currentSolution = TSP.startSolution(this.numberOfNodes);
	    //this.tabuLength = 100; //must be equal to the length of the chromosome
	    this.tabuLength = this.numberOfNodes; 
	    
		this.adjencyDistanceMatrix =  (new AMatrix()).readMatrixFromFile(prop);
	}
	
	
   public static int[] getBestNeighbour(TabuList tabuList,
           TSPEnvironment tspEnviromnet,
           int[] initSolution) 
   {


       int[] bestSol = new int[initSolution.length]; //this is the best Solution So Far
       System.arraycopy(initSolution, 0, bestSol, 0, bestSol.length);
       double bestCost = tspEnviromnet.getObjectiveFunctionValue(initSolution);
       int city1 = 0;
       int city2 = 0;
       boolean firstNeighbor = true;

       for (int i = 1; i < bestSol.length - 1; i++) 
       {
           for (int j = 2; j < bestSol.length - 1; j++) 
           {
               if (i == j) 
               {
                   continue;
               }

               int[] newBestSol = new int[bestSol.length]; //this is the best Solution So Far
               System.arraycopy(bestSol, 0, newBestSol, 0, newBestSol.length);

               newBestSol = swapOperator(i, j, initSolution); //Try swapping cities i and j
               // , maybe we get a bettersolution
               double newBestCost = tspEnviromnet.getObjectiveFunctionValue(newBestSol);

              //System.out.println("Tabu "+tabuList.tabuList[i][j]);

               if ((newBestCost > bestCost || firstNeighbor) && tabuList.tabuList[i][j] == 0) //if better move found, store it
               { 
                   firstNeighbor = false;
                   city1 = i;
                   city2 = j;
                   System.arraycopy(newBestSol, 0, bestSol, 0, newBestSol.length);
                   bestCost = newBestCost;
               }

           }
       }

       if (city1 != 0) 
       {
           tabuList.decrementTabu();
           tabuList.tabuMove(city1, city2);
       }
       
       return bestSol;

   }

   //swaps two cities
   public static int[] swapOperator(int city1, int city2, int[] solution) 
   {
       int temp = solution[city1];
       solution[city1] = solution[city2];
       solution[city2] = temp;
       return solution;
   }

   
   
   public final int[] start() 
   {

       TSPEnvironment tspEnvironment = new TSPEnvironment();

       tspEnvironment.distances = //Distance matrix, 5x5, used to represent distances
           this.adjencyDistanceMatrix;
       
       /*
       tspEnvironment.distances = //Distance matrix, 5x5, used to represent distances
               new double [][]{{0, 1, 3, 4, 5},
                               {1, 0, 1, 4, 8},
                               {3, 1, 0, 5, 1},
                               {4, 4, 5, 0, 2},
                               {5, 8, 1, 2, 0}};
         */
       //Between cities. 0,1 represents distance between cities 0 and 1, and so on.

       TabuList tabuList = new TabuList(this.tabuLength);
      
       //test
       //int[] currSolution = new int[this.initializeSolution.length];;   //initial solution
       //System.arraycopy(this.initializeSolution, 0, currSolution, 0, currSolution.length);
       
       int[] bestSol = new int[this.currentSolution.length]; //this is the best Solution So Far
       System.arraycopy(this.currentSolution, 0, bestSol, 0, bestSol.length);
       //System.out.println(tspEnvironment.distances[0].length); System.exit(0);
       double bestCost = tspEnvironment.getObjectiveFunctionValue(bestSol);
       System.out.println("Initial Cost "+bestCost);
       for (int i = 0; i < this.numberOfIterations; i++) // perform iterations here
       { 

    	   this.currentSolution = TabuSearch.getBestNeighbour(tabuList, tspEnvironment,this.currentSolution);
    	  
           //printSolution(currSolution);
           double currCost = tspEnvironment.getObjectiveFunctionValue(this.currentSolution);
           
           /*
           for(int q: this.currentSolution)
    	   {
    		   System.out.print(q +" ");
    	   } System.out.println("Cost "+ tspEnvironment.getObjectiveFunctionValue(this.currentSolution));
    	   */
           
           //System.out.println("Current best cost = " + tspEnvironment.getObjectiveFunctionValue(currSolution));
             
           if (currCost < bestCost) 
           {
               System.arraycopy(this.currentSolution, 0, bestSol, 0, bestSol.length);
               bestCost = currCost;
           }
           System.out.println("Current best "+bestCost + " "+ currCost);
       }

       System.out.println("Search done! \nBest Solution cost found = " + bestCost + "\nBest Solution :");

       //printSolution(bestSol);

        return bestSol;

   }

}
