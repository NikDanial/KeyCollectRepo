public class Player {
   Point player_pos = new Point();
   int player_key;
   int no_keys;
   int[] player_key_array = new int[6];
   
   public Player(){
       player_pos = new Point();
	   player_key = 0;
	   no_keys = 0;
	   for (int i = 0;i<6;i++)
		   player_key_array[i] = 0; //array of { 0,0,0,0,0,0 }, become 1 when have key
   }   
   
   public Player(Point pos, int key, int noKeys){
	   this.player_pos = pos;
	   this.player_key = key;
	   this.no_keys =  noKeys;
	   for (int i = 0;i<6;i++)
		   this.player_key_array[i] = 0;
   }
   
   public int getPlayerKey() { return player_key; }
   public void setPlayerKey(int key) { this.player_key = key; }
   public Point getPlayerPos() { return player_pos; }
   public void setPlayerPos(Point pos){
	    this.player_pos = pos;
   }
   public void setPlayerKeyArray(int index, int value){
	   this.player_key_array[index] = value;
   }
   public int getPlayerKeyArray(int index){
	   return player_key_array[index];
   }
   
   /* Copy Constructor */
   public Player(Point pos){
	   this.player_pos = pos;
	   player_key = 0;
	   no_keys = 0;
	   for (int i = 0;i<6;i++)
		   this.player_key_array[i] = 0;
   }
   public int getNoKeys() { return no_keys; }
   public void setNoKeys(int keys) { this.no_keys = keys; }
   
	
}
