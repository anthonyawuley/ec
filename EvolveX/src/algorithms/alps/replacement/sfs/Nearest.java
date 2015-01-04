package algorithms.alps.replacement.sfs;

import util.random.MersenneTwisterFast;
import util.random.RandomGenerator;
import individuals.populations.Population;
import algorithms.alps.ALPSReplacement;
import algorithms.alps.system.ALPSLayers;

public class Nearest extends ALPSReplacement{

	private int individualID;
	
	public Nearest() 
	{
	}
	
	public String toString()
	{
		return "Nearest Neighbour Replacment";
	}


	@Override
	public Population performAgeLayerMovements(ALPSLayers alpsLayers,
			Population current) 
	{
		
		Population higherPop = null;
		Population deleteList = new Population();
		
		
		higherPop = (Population) alpsLayers.layers.get(alpsLayers.index+1).
					getEvolution().getCurrentPopulation().clone();
	
		
		for(int i=0;i<current.size();i++)
		{
			if(current.get(i).getAge() >= alpsLayers.layers.get(alpsLayers.index).getMaxAge() )
			{ 
				//fill higher layer with individuals that fall withing its age limit
				if(higherPop.size() < alpsLayers.layers.get(alpsLayers.index+1).getParameters().getPopulationSize())
				{
					alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
					getCurrentPopulation().add(current.get(i));
				}
				else if(higherPop.size()>0) //once higher layer is filled, do selective replacement based on new individuals that have higher age than in the individual in the  higher layer
				{
					@SuppressWarnings("unused")
					RandomGenerator randGen = new RandomGenerator(); 
			        MersenneTwisterFast mtf = new MersenneTwisterFast();
			        mtf.setSeed(alpsLayers.layers.get(alpsLayers.index).getParameters().getSeed()); //set seed
			        
			        this.individualID = nearestPopulationIndividual( //select worst individual in population
									               higherPop,
									               current.get(i).getAge());
					
					alpsLayers.layers.get(alpsLayers.index+1).getEvolution().getCurrentPopulation().
					   set(this.individualID,current.get(i));
					deleteList.add(current.get(i));
				}
			}
		}
		//remove all individuals older than current layer
		for(int id=0;id<deleteList.size();id++)
		{
			//current.remove(deleteList.get(id));
		}
		
		System.out.println(deleteList.size()+ " -- Current!! "+current.size()+
				" Next "+alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
				getCurrentPopulation().size()); //System.exit(0);
		
		return current;
	}
	

	/**
	 * 
	 * @param pop
	 * @param age
	 * @return the nearest individual in population
	 */
	private int nearestPopulationIndividual(Population pop,double age)
	{
		int nearest = 0;
		System.out.println("\nBegin:::\n" + age);
		for(int id=1;id<pop.size();id++)
		{
			if(Math.abs(pop.get(nearest).getAge() - age) > Math.abs(pop.get(id).getAge() - age))
			{   
				nearest = id;
			}System.out.print(" "+pop.get(id).getAge()+" ");
		}
		System.out.println("\nThis is the nearest "+pop.get(nearest).getAge()+" \n");
		return nearest;
	}
	

}
