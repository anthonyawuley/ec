package main;

import parameter.Parameters;

public abstract class  Start extends Parameters 
{
	
  long start      = 0;
  long end        = 0;
  long sysEndTime = 0;
  
	
  long sysStartTime = System.currentTimeMillis();
	
  public abstract String toString();

}
