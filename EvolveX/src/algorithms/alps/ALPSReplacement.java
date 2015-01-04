package algorithms.alps;

import java.util.ArrayList;

import algorithms.alps.system.ALPSLayers;
import individuals.Individual;
import individuals.populations.Population;

public abstract class ALPSReplacement {

	/**
	 * 
	 * @return
	 */
	public abstract String toString();
	
	/**
	 * 
	 * @param alpsLayers
	 * @param current
	 * @return
	 */
	public abstract Population performAgeLayerMovements(ALPSLayers alpsLayers, Population current);
	
	
	/**
	 * 
	 * @param pop
	 * @param tournament
	 * @param age
	 * @return the nearest tournament individual
	 */
	protected int nearestTournamentIndividualAge(Population pop, ArrayList<Integer> tournament,double age)
	{
		int nearest = 0;
		//System.out.println("\nBegin:::\n" + age);
		for(int id=1;id<tournament.size();id++)
		{
			if(Math.abs(pop.get(tournament.get(nearest)).getAge() - age) > Math.abs(pop.get(tournament.get(id)).getAge() - age))
			{   
				nearest = id;
			}//System.out.print(" "+pop.get(tournament.get(id)).getAge()+" ");
		}
		//System.out.println("\nThis is the nearest "+pop.get(tournament.get(nearest)).getAge()+" \n");
		return tournament.get(nearest);
	}
	
	/**
	 * 
	 * @param pop
	 * @param tournament
	 * @param age
	 * @return the nearest tournament individual
	 */
	protected int nearestTournamentIndividualFitness(Population pop, ArrayList<Integer> tournament,double fitness)
	{
		int nearest = 0;
		//System.out.println("\nBegin:::\n" + age);
		for(int id=1;id<tournament.size();id++)
		{
			if(Math.abs(pop.get(tournament.get(nearest)).getFitness().getDouble() - fitness) > 
			Math.abs(pop.get(tournament.get(id)).getFitness().getDouble() - fitness))
			{   
				nearest = id;
			}//System.out.print(" "+pop.get(tournament.get(id)).getAge()+" ");
		}
		//System.out.println("\nThis is the nearest "+pop.get(tournament.get(nearest)).getAge()+" \n");
		return tournament.get(nearest);
	}
	
	
	/**
	 * 
	 * @param pop
	 * @param age
	 * @return the nearest individual in population
	 */
	protected int nearestPopulationIndividualAge(Population pop,double age)
	{
		int nearest = 0;
		//System.out.println("\nBegin:::\n" + age);
		for(int id=1;id<pop.size();id++)
		{
			if(Math.abs(pop.get(nearest).getAge() - age) > Math.abs(pop.get(id).getAge() - age))
			{   
				nearest = id;
			}//System.out.print(" "+pop.get(id).getAge()+" ");
		}
		//System.out.println("\nThis is the nearest "+pop.get(nearest).getAge()+" \n");
		return nearest;
	}
	
	
	/**
	 * 
	 * @param pop
	 * @param age
	 * @return the nearest individual in population
	 */
	protected int nearestPopulationIndividualFitness(Population pop,double fitness)
	{
		int nearest = 0;
		//System.out.println("\nBegin:::\n" + age);
		for(int id=1;id<pop.size();id++)
		{
			if(Math.abs(pop.get(nearest).getFitness().getDouble() - fitness) > 
			Math.abs(pop.get(id).getFitness().getDouble() - fitness))
			{   
				nearest = id;
			}//System.out.print(" "+pop.get(id).getAge()+" ");
		}
		//System.out.println("\nThis is the nearest "+pop.get(nearest).getAge()+" \n");
		return nearest;
	}
	
	/**
	 * @param pop
	 * @param tournament
	 * @return the worse tournament individual
	 */
	protected int worseTournamentIndividual(Population pop, ArrayList<Integer> tournament)
	{
		int worse = 0;
		for(int id=1;id<tournament.size();id++)
		{
			if(pop.get(tournament.get(worse)).getFitness().getDouble() < 
					pop.get(tournament.get(id)).getFitness().getDouble() )
			{   
				worse = id;
			}
		}
		return tournament.get(worse);
	}
	
	
	
	/**
	 * usually, combined is total of current + lower layer 0------i, i+1-------n
	 * first half is current population and second is lower layer
	 * @param combined
	 * @param current
	 */
	public void consolidatePopulation(ALPSLayers alpsLayers,Population combined)
	{   //keep removing individuals from the head-- till the required population size is met
		if(!alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer()) {
		  for(int i=0;i<alpsLayers.layers.get(alpsLayers.index-1).
				  getEvolution().getCurrentPopulation().size();i++)
		  { //System.out.println("this is the line i'm interested in!!"); System.exit(0);
		  	combined.remove(combined.get(combined.size()-1));
		  }
		}
	}
	
	/**
	 * @param pop
	 * @param individual
	 * @return the worse tournament individual in the next higher layer
	 * if no individual was found, the population size is returned: NB- 
	 * this index doesn't exist, it means no individual found
	 */
	protected int worseFitIndividual(Population pop, Individual ind)
	{
		for(int i=0;i<pop.size();i++)
		{
			if(ind.getFitness().getDouble() > pop.get(i).getFitness().getDouble() )
			{   
				return i;
			}
		}
		return pop.size(); //this index doesnt exist, it means no individual found
	}
	
	/**
	 * @param pop
	 * @return the worse individual in population
	 */
	protected int worsePopulationIndividual(Population pop)
	{
		int worse = 0;
		for(int id=1;id<pop.size();id++)
		{
			if(pop.get(worse).getFitness().getDouble() < 
					pop.get(id).getFitness().getDouble() )
			{   
				worse = id;
			}
		}
		return worse;
	}
	
	
	
	/**
	 * @param pop
	 * @param tournament
	 * @return the worse tournament individual
	 */
	protected int worseTournamentIndividualAge(Population pop, ArrayList<Integer> tournament)
	{
		int worse = 0;
		for(int id=1;id<tournament.size();id++)
		{
			if(pop.get(tournament.get(worse)).getAge() < 
					pop.get(tournament.get(id)).getAge() )
			{   
				worse = id;
			}
		}
		return tournament.get(worse);
	}
	
	
	/**
	 * @param pop
	 * @return the worse individual in population
	 */
	protected int worsePopulationIndividualAge(Population pop)
	{
		int worse = 0;
		for(int id=1;id<pop.size();id++)
		{
			if(pop.get(worse).getAge()  < pop.get(id).getAge() )
			{   
				worse = id;
			}
		}
		return worse;
	}
	
	
	
}
