package algorithms.aco;
import java.util.Random;
import java.util.concurrent.Callable;

import algorithms.aco.core.WalkedPath;
import util.Constants;

public final class Agent implements Callable<WalkedPath> {

  private final AntColonyOptimisation instance;
  private double distanceWalked = 0.0d;
  private final int start;
  private final boolean[] visited;
  private final int[] way;
  private int toVisit;
  private final Random random = new Random(System.nanoTime());

  /**
   * 
   * @param instance
   * @param start randomly selected starting point
   */
  public Agent(AntColonyOptimisation instance, int start) 
  {
    super();
    this.instance = instance;
    this.visited = new boolean[instance.matrix.length];
    visited[start] = true;
    toVisit = visited.length - 1;
    this.start = start;
    this.way = new int[visited.length];
  }

  // TODO really needs improvement
  private final int getNextProbableNode(int y) 
  { 
    if (toVisit > 0) 
    {
      int danglingUnvisited = -1;
      final double[] weights = new double[visited.length];

      double columnSum = 0.0d;
      for (int i = 0; i < visited.length; i++) 
      {
        columnSum += Math.pow(instance.readPheromone(y, i),Constants.ALPHA) * 
        		     Math.pow(instance.invertedMatrix[y][i],Constants.BETA);
      }

      double sum = 0.0d;
      for (int x = 0; x < visited.length; x++) 
      {
        if (!visited[x]) 
        {
          weights[x] = calculateProbability(x, y, columnSum);
          sum += weights[x];
          danglingUnvisited = x;
        }
      }

      if (sum == 0.0d)
        return danglingUnvisited;

      // weighted indexing stuff
      double pSum = 0.0d;
      for (int i = 0; i < visited.length; i++) 
      {
        pSum += weights[i] / sum;
        weights[i] = pSum;
      }
      final double r = random.nextDouble();
      
      for (int i = 0; i < visited.length; i++) 
      {
        if (!visited[i]) 
        {
          if (r <= weights[i]) 
          {
            return i;
          }
        }
      }

    }
    return -1;
  }

  // test method
  @SuppressWarnings("unused")
  private final int calculateChoice(double[] probabilityDistr, Random rnd) 
  {
    double rndNumber = rnd.nextDouble();
    int counter = -1;

    while (rndNumber > 0) 
    {
      rndNumber -= probabilityDistr[++counter];
    }

    return counter;
  }

  /**
   * (pheromones ^ ALPHA) * ((1/length) ^ BETA) divided by the sum of 
   * all rows (the summation of every possible solution).
   */
  private final double calculateProbability(int row, int column, double sum) 
  {
    final double p = Math.pow(instance.readPheromone(column, row),
    		          Constants.ALPHA) * Math.pow(instance.invertedMatrix[column][row],
        		      Constants.BETA);
    return p / sum;
  }

  @Override
  public final WalkedPath call() throws Exception 
  {

    int lastNode = start;
    int next = start;
    int i = 0;
    while ((next = getNextProbableNode(lastNode)) != -1) 
    {
      way[i] = lastNode;
      i++;
      distanceWalked += instance.matrix[lastNode][next];
      //"phero" is the amount of absolute pheromone which gets deposited for worker "i" on the edge to "xy"
      final double phero = (Constants.Q / (distanceWalked));
      instance.adjustPheromone(lastNode, next, phero);
      visited[next] = true;
      lastNode = next;
      toVisit--;
    }
    distanceWalked += instance.matrix[lastNode][start];
    way[i] = lastNode;

    return new WalkedPath(way, distanceWalked);
  }
}

