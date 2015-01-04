package algorithms.alps.agingscheme;

import java.util.ArrayList;

import algorithms.alps.AgingScheme;
import algorithms.alps.layers.Layer;

public class Polynomial  implements AgingScheme {

	
	public Polynomial() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public String toString(String str)
	{
		return "polynomial aging scheme selected " + str;
	}
	
	/**
	 * @return n^2
	 */
	public ArrayList<Layer> agingScheme(int ageGap,int ageLayers)
	{
		this.toString(" with an age gap of "+ ageGap +" and "+ ageLayers +" layers");
		ArrayList<Layer> layers  = new ArrayList<>();
		
		for(int i=0; i<ageLayers;i++)
		{
			Layer layer = new Layer();
			layer.setIsActive(Boolean.FALSE);
			layer.setGenerationalCount(0); //initialize generational count
			layer.setId(i);
			
			if(i==0)
			{
				layer.setMaxAgeLayer((int) 1*ageGap);//0:1
				layer.setIsBottomLayer(Boolean.TRUE);
				layer.setGenerations(layer.getMaxAge());
			}
			else
			{
				if(i==1) 
				{
					layer.setMaxAgeLayer((int) 2*ageGap); // 1:2
				}
				else
				{
					layer.setMaxAgeLayer((int) Math.pow(i,2)*ageGap);	
				}
				layer.setIsBottomLayer(Boolean.FALSE);
				layer.setGenerations( //get the difference between maximum of this layer and previous layer
				           layer.getMaxAge() - layers.get(i-1).getMaxAge());
			}
			layers.add(layer);
			//layers.set(i, layer);
		}
		/*
		 * there is no age-limit to the last layer so as to be able to keep the 
		 * best individuals from the most promising (longest running) search
		 */
		//layers.get(layers.size()-1).setMaxAgeLayer(Constants.ALPS_MAX_AGE_LAST_LAYER);
		//layers.get(layers.size()-1).setGenerations(
		//		Constants.ALPS_MAX_AGE_LAST_LAYER - layers.get(layers.size()-2).getMaxAge());
		
		return layers;
	}

}
