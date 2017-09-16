public class Keys {
	Point key_pos = new Point();
	int key_code;
	
	public Keys(){
		key_pos = new Point();
		key_code = 0;
	}
	
	public Keys(Point pos, int code){
		this.key_pos = pos;
		this.key_code = code;
	}
	
	public Keys(Point pos){
		this.key_pos = pos;
		key_code = 0;
	}
	
	public Point getKeyPos() { return key_pos; }
	public void setKeyPos(Point pos) { this.key_pos = pos; }
	public int getKeyCode() { return key_code; }
	public void setKeyCode(int code) { this.key_code = code; } 
	
}
