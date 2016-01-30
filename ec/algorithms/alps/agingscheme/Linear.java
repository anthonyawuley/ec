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
package ec.algorithms.alps.agingscheme;


import java.util.ArrayList;

import ec.algorithms.alps.AgingScheme;
import ec.algorithms.alps.layers.Layer;


public class Linear implements AgingScheme {

	public Linear() 
	{
		
	}
	
	@Override
	public String toString(String str)
	{
		return "polynomial aging scheme selected " + str;
	}
	
	@Override
	public ArrayList<Layer> agingScheme(int ageGap,int ageLayers)
	{
		ArrayList<Layer> layers = new ArrayList<>();
		
		this.toString(" with an age gap of "+ ageGap +" and "+ ageLayers +" layers");
		
		for(int i=0; i<ageLayers;i++)
		{
			Layer layer = new Layer();
			layer.setMaxAgeLayer((i+1)*ageGap);
			layer.setIsActive(Boolean.FALSE);
			layer.setGenerationalCount(0); //initialize generational count
			layer.setId(i);
			
			if(i==0)
			{
				layer.setIsBottomLayer(Boolean.TRUE);
				layer.setGenerations(layer.getMaxAge());
			}
			else
			{
				layer.setIsBottomLayer(Boolean.FALSE);
				layer.setGenerations( //get the difference between maximum of this layer and previous layer
						           layer.getMaxAge() - layers.get(i-1).getMaxAge());
			}
			layers.add(layer);
		}
		
		/*
		 * there is no age-limit to the last layer so as to be able to keep the 
		 * best individuals from the most promising (longest running) search
		 */
		//layers.get(layers.size()-1).setMaxAgeLayer(Constants.ALPS_MAX_AGE_LAST_LAYER);
		
		return layers;
	}
	

}
