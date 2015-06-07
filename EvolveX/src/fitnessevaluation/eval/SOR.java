package fitnessevaluation.eval;

import java.util.ArrayList;
import java.util.Properties;

import fitnessevaluation.multiobjective.SumOfRanks;
import individuals.populations.Population;

public class SOR extends WS{

	  public SOR() 
	  {
		// TODO sum of runks
	  }
	
    
    
       /**
        * activate later
        * @param pop
        * @return 
        * @deprecated
        */
       public double setAverageFitness(Population pop)
       {
          double averageFitness = 0;
          for( int i=0; i< pop.size(); i++)
          {
             averageFitness += sumDistanceVRP(pop.get(i).getChromosome(),this.getProperties());
          }
          return averageFitness/pop.size();
       }

       
    
       /**
        * 
        * @return
        * overloaded from parent class
        */
       public ArrayList<Double> calcGenerationalFitness(Population pop,Properties p)
      	{
    	      SumOfRanks sor = new SumOfRanks();
    	      
      		  double sum = 0.0d;
      		  
      		  //get elements ranked using SOR
      		  this.setGenerationFitness(sor.sorCalculations(changePopFormatForMOP(pop,p,","),pop.size()));
      		  //add total fitnes of rank
      		  for(double d: this.getGenerationFitness())
      		  {
      			  sum+=d;
      		  }
      	     
      	     this.setTotalFitness(sum); //total fitness
      	  return this.getGenerationFitness();
      	}



}
