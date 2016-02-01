/*******************************************************************************
 * Copyright (c) 2014, 2015 
 * Anthony Awuley - Brock University Computer Science Department
 * All rights reserved. This program and the accompanying materials
 * are made available under the Academic Free License version 3.0
 * which accompanies this distribution, and is available at
 * https://aawuley@bitbucket.org/aawuley/evolvex.git
 *
 * Contributors:
 *     ECJ                     MersenneTwister & MersenneTwisterFast (https://cs.gmu.edu/~eclab/projects/ecj)
 *     voidException      Tabu Search (http://voidException.weebly.com)
 *     Lucia Blondel       Simulated Anealing 
 *     
 *
 *        
 *******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.parameter;

import ec.exceptions.InitializationException;
import ec.parameter.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 *
 * @author Anthony Awuley
 */
public class Parameters extends Instance implements ParameterInterface {
    
    
	protected static String propertiesFilePath;
    protected Parameters initialiser;
    protected Properties properties;
    protected String stdOut;
    protected String stdErr;
    
    public Parameters()
    {
        //super();
    }
     
   
    /**
     * Read the default properties.
     * @param args arguments
     */
    @Override
	@SuppressWarnings({"static-access"})
	public Properties setup() throws InitializationException 
    {
        ClassLoader loader;
        InputStream in;
        try 
        {
            this.properties = new Properties();
            
            File f = new File("ec/"+Parameters.propertiesFilePath);
             
            if (!f.exists()) 
            {   //try classloading
                loader = ClassLoader.getSystemClassLoader();
                in = loader.getResourceAsStream(Parameters.propertiesFilePath);
                this.properties.load(in);
                System.out.println("Loading properties from ClassLoader and: " + Parameters.propertiesFilePath);
            } 
            else 
            {
                FileInputStream is = new FileInputStream(f);
                this.properties.load(is);
                licenceOut();
                System.out.println("Loading properties from file system: \"" + Parameters.propertiesFilePath+"\"");
               
                //begin initialisaiton of individuals
                setOutput(); //default system output
            }
        } 
        catch (IOException e) 
        {
            loader = ClassLoader.getSystemClassLoader();
            in = loader.getResourceAsStream(Parameters.propertiesFilePath);
            try 
            {
                this.properties.load(in);
            } 
            catch (Exception ex) 
            {
                System.err.println("Properties reading output caught:" + ex);
            }
            System.err.println(MessageFormat.format("Using default: {0} Bad:{1} Could not load:{2}", 
            		Parameters.propertiesFilePath, Parameters.propertiesFilePath, e));
        } 
        
        return properties;
        
    }
    
    /**
     * 
     */
    private void setOutput()
    {
    	 System.out.printf( "%-15s %10s %n", "Number of experiments:", 
    			 Integer.parseInt(properties.getProperty("number-of-experiments")));
    	
    	try
    	{
          
          System.out.printf( "%-15s %10s %n", "Number of generations:", Integer.parseInt(properties.getProperty("generations")));
          System.out.printf( "%-15s %16s %n", "Population size:", Integer.parseInt(properties.getProperty("population-size")));
          System.out.printf( "%-15s %8s  %n", "Initial chromosome size:", Integer.parseInt(properties.getProperty("initial-chromosome-size")));
          System.out.printf( "%-15s %16s %n", "Tournament Size:", Integer.parseInt(properties.getProperty("tournament-size")));
          System.out.printf( "%-15s %17s %n", "Elitism Size:", Integer.parseInt(properties.getProperty("elite-size")));
          System.out.printf( "%-15s %10s %n", "Crossover probability:", Float.parseFloat(properties.getProperty("crossover-probability")));
          System.out.printf( "%-15s %11s %n", "Mutation probability:", Float.parseFloat(properties.getProperty("mutation-probability")));
          
    	}
    	catch(NumberFormatException e)
    	{	
    	}
    	System.out.println(".................................\n");
    }
    
    private void licenceOut()
    {
      System.out.println("| EvoGA-v1.0\n"
              +          "| Evolutionary computation\n"
              +          "| By Anthony Awuley\n"
              +          "| Mail: aa12qw@brocku.ca\n"
              +          "| Date: February 01, 2014-2015"
              +          "\n");
      
    }

	@Override
	public void setProperties(Properties p) {
		// TODO Auto-generated method stub
		
	}
   
    
}
