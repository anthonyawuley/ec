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
package ec.algorithms.tabu.solution;

public class Writer {

	public Writer() {
		// TODO Auto-generated constructor stub
	}
	

	public static void printSolution(int[] solution) 
	{
	   for (int i = 0; i < solution.length; i++) 
	   {
	       System.out.print(solution[i] + " ");
	   }
	   System.out.println();
	}

}
