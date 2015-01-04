package operator.operations.replacement.steadystate;

import operator.operations.SelectionOperation;
import operator.operations.replacement.AbstractSSReplacement;
import operator.operations.selection.TournamentSelection;
import util.random.MersenneTwisterFast;
import util.random.RandomGenerator;
import individuals.Individual;
import individuals.populations.Population;
import algorithms.alps.system.ALPSLayers;

public class Random  extends AbstractSSReplacement {
	
	private int individualID;
	
	public Random() 
	{
	}

	public String toString()
	{
		return "Random Replacement";
	}
	
	/*
	@Override
	public Population performAgeLayerMovements(ALPSLayers alpsLayers,
			Population current) {
		
		Population higherPop = null;
		Population deleteList = new Population();
		SelectionOperation selectionOperation = new TournamentSelection();
		
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
				else if(higherPop.size()>0) //once higher layer is filled, do selective replacement randome based on new individuals that have higher age than in the individual in the  higher layer
				{
					@SuppressWarnings("unused")
					RandomGenerator randGen = new RandomGenerator(); 
			        MersenneTwisterFast mtf = new MersenneTwisterFast();
			        mtf.setSeed(alpsLayers.layers.get(alpsLayers.index).getParameters().getSeed()); //set seed
			        
			        //perform tournament selection on higher layer
					selectionOperation.performTournamentSelection(alpsLayers,higherPop.size(),
						 alpsLayers.layers.get(alpsLayers.index+1).getParameters().getTournamentSize());
			        
					this.individualID = selectionOperation.performTournamentSelection( //select one individual at random
								selectionOperation.getTournamentSelection().size(),1).get(0);
				
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
	*/
	
	
	
	
   public Population ssReplacements(ALPSLayers alpsLayers, Population currentPop, Population replacement) {
		
		//Population currentPop = null;
		//Population deleteList = new Population();
		SelectionOperation selectionOperation = new TournamentSelection();
		
		//Do not clone
		//currentPop = (Population) alpsLayers.layers.get(alpsLayers.index).
		//		getEvolution().getCurrentPopulation();
		
		@SuppressWarnings("unused")
		RandomGenerator randGen = new RandomGenerator(); 
		MersenneTwisterFast mtf = new MersenneTwisterFast();
		mtf.setSeed(alpsLayers.layers.get(alpsLayers.index).getParameters().getSeed()); //set seed
		
		for(Individual ind: replacement.getAll()) //iterate through individuals to be replaced
		{
		   selectionOperation.performTournamentSelection(
				      alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize(),
				      alpsLayers.layers.get(alpsLayers.index).getParameters().getTournamentSize());
		   
			
			 /*
			 selectionOperation.performTournamentSelection(
					  currentPop.size(),
				      alpsLayers.layers.get(alpsLayers.index).getParameters().getTournamentSize());
		     */  
		    /**
		     * first half of elements in array are individuals from current layer
		     * keep padding list if the number of individuals in the current layer isn't
		     * up to the required population size. this is true from layer 1
		     */
		   if(alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer() &&  (alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size() //NOT WORKING ALWAYS EQUAL
					< (alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize())) )
			{   
				currentPop.add(0,ind); //System.out.println("Population size()"+currentPop.size());
			}
		   else if( !alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer() &&  (alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size() 
					< (alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize())) 
					//&& currentPop.size()<(2*alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize()) 
					)
			{  
				currentPop.add(0,ind); //System.out.println("Population size()"+currentPop.size());
			}
			else
			{  
		        //perform tournament selection on higher layer
				selectionOperation.performTournamentSelection(alpsLayers,currentPop.size(),
					 alpsLayers.layers.get(alpsLayers.index+1).getParameters().getTournamentSize());
		        
				this.individualID = selectionOperation.performTournamentSelection( //select one individual at random
							selectionOperation.getTournamentSelection().size(),1).get(0);
			
				//System.out.println("Replace "+currentPop.get(this.individualID).getFitness().getDouble() +" with: "+ind.getFitness().getDouble());
				 currentPop.set(this.individualID, ind); 
			}
		  
		}
		
		//System.out.println("--"+currentPop.size()+" ---"+alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size());
		/*
		System.out.println(deleteList.size()+ " -- Current!! "+currentPop.size()+
				" Next "+alpsLayers.layers.get(alpsLayers.index).getEvolution().
				getCurrentPopulation().size()); //System.exit(0);
		*/
		return currentPop;
	}
	
	
	
	

}
