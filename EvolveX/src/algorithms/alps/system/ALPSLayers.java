package algorithms.alps.system;

import java.util.ArrayList;

import algorithms.alps.layers.Layer;

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
