package main;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import algorithms.ga.Evolve;
import util.Constants;
import exceptions.InitializationException;

public class GA  extends Start{

	
	
	@Override
	public String toString() 
	{
		 return "Genetic Algorithms";
	}
	
	
	public GA(String[] arguments) throws IOException, InterruptedException, ExecutionException 
	{

      /*
    	int port = 2502;
    	String host = "localhost";
    	//Server s = new Server(host,0,port);
    	//Client c = new Client(host,port);
    	
    	Server s = new Server(host,0,port);
    	Client c = new Client(host,port);
    	
    	 
    	s.startServer();
    	//GreetingServer.start(port);;
    	c.startClient();
    	
    	System.exit(0);
       */
        
        try 
        {
        	//propertiesFilePath = arguments[1].toString().length()>1?arguments[1]:Constants.DEFAULT_PROPERTIES;
            for(int f=1;f<arguments.length;f++)
            {  
            	/* Start Evolve time */
            	start = System.currentTimeMillis();
            	
            	propertiesFilePath = arguments[f].toString().length()>1?arguments[f]:Constants.DEFAULT_PROPERTIES;
            	new Evolve(this.setup());
            	/* End  Evolve  time */
            	end = System.currentTimeMillis();
            }
                /* End System Time */
            sysEndTime = System.currentTimeMillis();
		} 
        catch (InitializationException e) 
        {
			e.printStackTrace();
		}
	}
	
	/**
	 * Begin execution of GA 
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	 public static void main(String[] args) throws IOException, InterruptedException, ExecutionException 
	 {
	       new GA(args);
	 }
	
	

}
