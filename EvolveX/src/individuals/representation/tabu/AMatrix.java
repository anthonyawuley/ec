package individuals.representation.tabu;

import individuals.Representation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;

import util.Constants;
import util.Point;

public class AMatrix extends Representation{

	
	
	
	
	 /**
	   * The "crossing" between two vertices- array[x][y] is in our case the distance between vertex x and vertex y
	   * 
	   * @return Adjecency matrix - array[x][y] is in our case the distance between vertex x and vertex y
	   * @throws IOException
	   */
	  public final double[][] readMatrixFromFile(Properties p) throws IOException 
	  {
	    //final BufferedReader br = new BufferedReader(new FileReader(new File(TSP_FILE)));
		String[] cord0 = new String[6];
	    final LinkedList<Point> records = new LinkedList<Point>();
	    int numberOfNodes = Integer.parseInt(p.getProperty(Constants.NUMBER_OF_NODES));
		 
	    int count = 0;
	    while (count < numberOfNodes) 
	    { 
	    	count++; //start count from 1
	    	cord0    = p.getProperty(count+"").split("\\s{1,}");
	        records.add(new Point(Double.parseDouble(cord0[0]), Double.parseDouble(cord0[1])));
	    }
	    
	    //create nXn matrix
	    final double[][] localMatrix = new double[records.size()][records.size()];

	    int rIndex = 0;
	    for (Point r : records) 
	    {
	      int hIndex = 0;
	      for (Point h : records) 
	      {
	        localMatrix[rIndex][hIndex] = r.calculateEuclidianDistance(h);
	        hIndex++;
	      }
	      rIndex++;
	    }
	    return localMatrix;
	  }
	
	  /**
	   * HYBRID PARSER
	   * The "crossing" between two vertices- array[x][y] is in our case the distance between vertex x and vertex y
	   * 
	   * @return Adjecency matrix - array[x][y] is in our case the distance between vertex x and vertex y
	   * @throws IOException
	   */
	  public final double[][] readMatrixFromHybrid(Properties p,ArrayList<Integer> hybrid) throws IOException 
	  {
	    //final BufferedReader br = new BufferedReader(new FileReader(new File(TSP_FILE)));
		String[] cord0 = new String[6];
	    final LinkedList<Point> records = new LinkedList<Point>();
	 
	    
	    for(int val: hybrid)
	    { //System.out.print(val+" ");
	    	cord0    = p.getProperty(val+"").split("\\s{1,}");
	        records.add(new Point(Double.parseDouble(cord0[0]), Double.parseDouble(cord0[1])));
	    }//System.out.println();
	    
	    //create nXn matrix
	    final double[][] localMatrix = new double[records.size()][records.size()];

	    int rIndex = 0;
	    for (Point r : records) 
	    {
	      int hIndex = 0;
	      for (Point h : records) 
	      {
	        localMatrix[rIndex][hIndex] = r.calculateEuclidianDistance(h);
	        hIndex++;
	      }
	      rIndex++;
	    }
	    return localMatrix;
	  }
  
	  
	  /**
	   * JUST FOR TEST
	   * @return Adjecency matrix - array[x][y] is in our case the distance between vertex x and vertex y
	   * @throws IOException
	   */
	  public final int[][] readINTMatrixFromFile(Properties p) throws IOException 
	  {
	    //final BufferedReader br = new BufferedReader(new FileReader(new File(TSP_FILE)));
		String[] cord0 = new String[6];
	    final LinkedList<Point> records = new LinkedList<Point>();
	    int numberOfNodes = Integer.parseInt(p.getProperty(Constants.NUMBER_OF_NODES));
		 
	    int count = 0;
	    while (count < numberOfNodes) 
	    { 
	    	count++; //start count from 1
	    	cord0    = p.getProperty(count+"").split("\\s{1,}");
	        records.add(new Point(Double.parseDouble(cord0[0]), Double.parseDouble(cord0[1])));
	    }
	    
	    //create nXn matrix
	    final int[][] localMatrix = new int[records.size()][records.size()];

	    int rIndex = 0;
	    for (Point r : records) 
	    {
	      int hIndex = 0;
	      for (Point h : records) 
	      {
	        localMatrix[rIndex][hIndex] = (int) r.calculateEuclidianDistance(h);
	        hIndex++;
	      }
	      rIndex++;
	    }
	    return localMatrix;
	  }
	
}
