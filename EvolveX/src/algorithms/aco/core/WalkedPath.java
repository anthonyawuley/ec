package algorithms.aco.core;

public class WalkedPath {

	 private int[] way;
	 private double distance;

	  public WalkedPath(int[] way, double distance) 
	  {
	    super();
	    this.setWay(way);
	    this.setDistance(distance);
	  }

	/**
	 * @return the distance
	 */
	public double getDistance() 
	{
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) 
	{
		this.distance = distance;
	}

	/**
	 * @return the way
	 */
	public int[] getWay() 
	{
		return way;
	}

	/**
	 * @param way the way to set
	 */
	public void setWay(int[] way) 
	{
		this.way = way;
	}
}
