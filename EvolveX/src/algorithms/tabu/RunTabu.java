package algorithms.tabu;

import java.io.IOException;
import java.util.Properties;

import algorithms.tabu.solution.Writer;
import util.Constants;

public class RunTabu {

	int number_of_experiments;
	
	public RunTabu(Properties prop) throws IOException 
	{
		
		 this.number_of_experiments = Integer.parseInt(prop.getProperty(Constants.NUMBER_OF_EXPERIMENTS));
		  
		 for(int i=0;i<this.number_of_experiments;i++)
	     {
	         System.out.println("\nInitializing population for Run # "+i +"\n");
	         
	          //System.out.println("Using " + arguments[1]);
     	      long start = System.currentTimeMillis();
     	      
     	      TabuSearch tabu = new TabuSearch(prop,i);
         	  
         	  int[] result = tabu.start();
         	 //agentCompletionService.take();
         	 //antColonyOptimization.agentCompletionService.take();
         	 
  	         System.out.println("Took: " + (System.currentTimeMillis() - start) + " ms!");
  	         //System.out.println("Result was: " + result + "\n\n");
  	         Writer.printSolution(result);
  	         //antColonyOptimization.THREAD_POOL.shutdown();
  	         //antColonyOptimization.agentCompletionService.take();
	         
	     }
		
	}

}
