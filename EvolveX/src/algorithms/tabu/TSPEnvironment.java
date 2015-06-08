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

/**
*
* @author http://voidException.weebly.com
* Use this code at your own risk ;)
*/
public class TSPEnvironment { //Tabu Search Environment
   
   public double [][] distances;
   
   public TSPEnvironment(){
       
   }
   
   public double getObjectiveFunctionValue(int solution[])
   { //returns the path cost
       //the first and the last cities'
       //  positions do not change.
       // example solution : {0, 1, 3, 4, 2, 0}
      
       double cost = 0;
  
       for(int i = 0 ; i < solution.length-1; i++){
           cost+= distances[solution[i]][solution[i+1]];
       }
  
       return cost;
       
   }
   
  

}
