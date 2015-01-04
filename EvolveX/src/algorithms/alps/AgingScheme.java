package algorithms.alps;

import java.util.ArrayList;

import algorithms.alps.layers.Layer;

public interface AgingScheme {

	public String toString(String str);
	/**
	 * 
	 * @param ageGap
	 * @return
	 */
	public ArrayList<Layer> agingScheme(int ageGap,int ageLayers);

	
}
