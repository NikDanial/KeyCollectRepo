/**
 * class for Keys object 
 * @author Arif
 * @param none
 * @return none
 */
public class Keys {
	Point key_pos = new Point();
	int key_code;
	
	/** 
	 * @author Arif
	 * default constructor for Keys
	 */
	public Keys(){
		key_pos = new Point();
		key_code = 0;
	}
	
	/** 
	 * @author Arif
	 * overloaded constructor for Keys
	 */
	public Keys(Point pos, int code){
		this.key_pos = pos;
		this.key_code = code;
	}
	
	/**
	 * @author Arif
	 * overloaded constructor for Keys
	 * @param pos the position of the key
	 */
	public Keys(Point pos){
		this.key_pos = pos;
		key_code = 0;
	}
	
	/**
	 * @author Arif
	 * get method for key_pos
	 */
	public Point getKeyPos() { return key_pos; }
	/**
	 * @author Arif
	 * set method for key_pos
	 * @param pos the new position of the key
	 */
	public void setKeyPos(Point pos) { this.key_pos = pos; }
	
	/**
	 * @author Arif
	 * get method for key_code
	 */
	public int getKeyCode() { return key_code; }
	/**
	 * @author Arif
	 * set method for key_code
	 * @param pos the new value of the key
	 */
	public void setKeyCode(int code) { this.key_code = code; } 
	
}
