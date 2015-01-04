package algorithms.aco;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import util.Constants;

public class RunAnt {

	private int number_of_experiments;
	
	public RunAnt(Properties prop) throws IOException, InterruptedException, ExecutionException {
		
		/*
	     * SET SYSTEM PARAMETERS
	     */
	  	 this.number_of_experiments = Integer.parseInt(prop.getProperty(Constants.NUMBER_OF_EXPERIMENTS));
		  
		 for(int i=0;i<this.number_of_experiments;i++)
	     {
	         System.out.println("\nInitializing population for Run # "+i +"\n");
	         
	          //System.out.println("Using " + arguments[1]);
     	      long start = System.currentTimeMillis();
     	      
         	  AntColonyOptimisation antColonyOptimization = new AntColonyOptimisation(prop,i);
         	  
         	  double result = antColonyOptimization.start();
         	 //agentCompletionService.take();
         	 //antColonyOptimization.agentCompletionService.take();
         	 
  	         System.out.println("Took: " + (System.currentTimeMillis() - start) + " ms!");
  	         System.out.println("Result was: " + result + "\n\n");
  	       
  	         //antColonyOptimization.THREAD_POOL.shutdown();
  	         //antColonyOptimization.agentCompletionService.take();
	         
	     } 
	}

}
