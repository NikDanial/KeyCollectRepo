/**
 * class for Player object 
 * @author Aneek
 * @param none
 * @return none
 */
public class Player {
   Point player_pos = new Point();
   int player_key;
   int no_keys;
   int[] player_key_array = new int[6];
   
   /** default constructor for Player class
    * @param none
	*/
   public Player(){
       player_pos = new Point();
	   player_key = 0;
	   no_keys = 0;
	   for (int i = 0;i<6;i++)
		   player_key_array[i] = 0; //array of { 0,0,0,0,0,0 }, become 1 when have key
   }   
   
   /** Overloaded constructor for Player class
    * @param pos the position of the Player in a Point object
	* @param key the key held by the player
	* @param noKeys the number of keys held by the player
	*/
   public Player(Point pos, int key, int noKeys){
	   this.player_pos = pos;
	   this.player_key = key;
	   this.no_keys =  noKeys;
	   for (int i = 0;i<6;i++)
		   this.player_key_array[i] = 0;
   }
   
   /**
    * get method for player_key
    */
   public int getPlayerKey() { return player_key; }
   /**
    * set method for player_key
	* @param key player key
    */
   public void setPlayerKey(int key) { this.player_key = key; }
   /**
    * get method for player_pos
	*/
   public Point getPlayerPos() { return player_pos; }
   
   /**
    * set method for player_pos
	* @param pos the position (Point object)
	*/
   public void setPlayerPos(Point pos){
	    this.player_pos = pos;
   }
   /**
    * set method for player_key_array
	* @param index the index in the array
	* @param value the value to be replaced in the array at point index
	*/
   public void setPlayerKeyArray(int index, int value){
	   this.player_key_array[index] = value;
   }
   
   /** 
    * get method for player_key_array
	* @param index the index of the array to be retrieved 
	*/
   public int getPlayerKeyArray(int index){
	   return player_key_array[index];
   }
   
   /**
     * Copy Constructor for player
	 * @param pos the position of the player
	 */
   public Player(Point pos){
	   this.player_pos = pos;
	   player_key = 0;
	   no_keys = 0;
	   for (int i = 0;i<6;i++)
		   this.player_key_array[i] = 0;
   }
   
   /**
    * get method for no_keys
	*/
   public int getNoKeys() { return no_keys; }
   
   /** set method for noKeys
    * @param keys the no of new keys
	*/
   public void setNoKeys(int keys) { this.no_keys = keys; }
   
	
}
