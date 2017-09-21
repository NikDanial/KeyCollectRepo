/**
 * class for Point object 
 * @author Arif
 * @param none
 * @return none
 */

public class Point {
	int x,y;
	
	/** 
	 * default constructor for Point
	 */
	public Point(){ x=0; y=0; }
	
	/**
	 * @author Arif
	 * overloaded constructor for Point
	 * @param x the x value of the point
	 * @param y the y value of the point
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @author Arif
	 * get method for x
	 */
	public int getX(){ return x; }
	/** 
	 * @author Arif
	 * set method for x
	 * @param x the new x value
	 */
	public void setX(int x){ this.x = x; }
	/**
	 * @author Arif
	 * get method for y
	 */
	public int getY(){ return y; }
	/** 
	 * @author Arif
	 * set method for y
	 * @param y the new y value
	 */
	public void setY(int y){ this.y = y; }
}
