package main;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import util.Constants;
import exceptions.InitializationException;
import algorithms.tabu.RunTabu;

public class Tabu extends Start{

	public Tabu() 
	{
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() 
	{
		 return "Tabu Search";
	}
   
  /** 
   * Creates a new instance of Run 
   * @throws IOException 
   * @throws ExecutionException 
   * @throws InterruptedException 
   */
   public Tabu(String[] arguments) throws IOException, InterruptedException, ExecutionException 
   {
       
       try 
       {
       	
           for(int f=1;f<arguments.length;f++)
           {  
        	   /* Start Evolve time */
           	   start = System.currentTimeMillis();
           	   
           	  propertiesFilePath = arguments[f].toString().length()>1?arguments[f]:Constants.DEFAULT_PROPERTIES;
           	  //AntColonyOptimisation antColonyOptimization = new AntColonyOptimisation(arguments[1],this.setup());
           	  new RunTabu(this.setup());
           	  
           	 /* End Evolve time */
          	  end = System.currentTimeMillis();
           } 
           /* End time */
         	sysEndTime = System.currentTimeMillis();
		} 
       catch (InitializationException e) 
       {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
   }
  
   
   
   public static void main(String[] args) throws IOException, InterruptedException, ExecutionException 
   {
       new Tabu(args);
   }

   
}