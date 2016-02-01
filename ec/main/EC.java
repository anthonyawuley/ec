package ec.main;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import ec.exceptions.InitializationException;

/**
 * Main interface for all Evolutionary Algorithms
 * All EAs implment this interface
 * This allows specification of main-class in the parameter file
 * 
 * @author Anthony Awuley
 *
 */
public interface EC extends Serializable {
	
	
	public void start(Properties p) throws IOException, InterruptedException, 
	ExecutionException, InitializationException;

}
