package util;

import java.io.*;

/**
 * Demonstrates a technique (a hack) to create a "deep clone"
 * in a Java application.
 * @author Alvin Alexander, <a href="http://alvinalexander.com" title="http://alvinalexander.com">http://alvinalexander.com</a>
 */

public class DeepClone {

	public DeepClone() 
	{
		
	}
	
	
	 /**
	   * This method makes a "deep clone" of any object it is given.
	   */
	  public static Object clone(Object object) 
	  {
	    try 
	    {
	      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      ObjectOutputStream oos = new ObjectOutputStream(baos);
	      oos.writeObject(object);
	      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	      ObjectInputStream ois = new ObjectInputStream(bais);
	      return ois.readObject();
	    }
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	      return null;
	    }
	  }
	  
	  

}
