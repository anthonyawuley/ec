package main;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import util.Constants;
import exceptions.InitializationException;
import algorithms.alps.system.Engine;

public class Alps extends Start{

	
	public Alps() 
	{}

	
	@Override
	public String toString() 
	{
		 return "Age Layered Population Strategy";
	}
	
   
 /** 
  * Creates a new instance of Run 
  * @throws IOException 
  * @throws ExecutionException 
  * @throws InterruptedException 
  */
   public Alps(String[] arguments) throws IOException, InterruptedException, ExecutionException 
   {
       try 
       {
          for(int f=1;f<arguments.length;f++)
          {  
        	  /* Start Evolve time */
          	  start = System.currentTimeMillis();
          	
          	  propertiesFilePath = arguments[f].toString().length()>1?
          			  arguments[f]:Constants.DEFAULT_PROPERTIES;
           	  new Engine(this.setup());
           	  
           	  /* End Evolve time */
          	  end = System.currentTimeMillis();
          } 
          /* Start Evolve time */
      	   sysEndTime = System.currentTimeMillis();
      	   
          System.out.println("Exiting!!");
          System.exit(0);
		} 
       catch (InitializationException e) 
       {
		  e.printStackTrace();
	   }
   }
  
   
   
   public static void main(String[] args) throws IOException, InterruptedException, ExecutionException 
   {
       new Alps(args);
   }
   
   
}
