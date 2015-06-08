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
package algorithms.alps.replacement;

import individuals.populations.Population;
import algorithms.alps.ALPSReplacement;
import algorithms.alps.system.ALPSLayers;
/**
 * @author anthony
 *
 * no modifications done. All individuals are shipped to the next higher 
 * layer after evolution is exhausted in one layer
 */
public class Default  extends ALPSReplacement{

	public Default() 
	{
	}
	
	public String toString()
	{
		return "Default Replacment";
	}

	@Override
	public Population performAgeLayerMovements(ALPSLayers alpsLayers,
			Population current) {
		return current;
	}

}
