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
