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
package ec.algorithms.alps.system;

import java.util.ArrayList;

import ec.algorithms.alps.layers.Layer;

/**
 * 
 * @author Anthony Awuley
 *
 */
public class ALPSLayers {

	public ArrayList<Layer> layers;
	public int index;
	public int evalCounter = 0; //keeps count of number of evaluations completed in a layer
	
	public ALPSLayers(ArrayList<Layer> l,int i) 
	{
		this.layers = l;
		this.index  = i;
	}


}
