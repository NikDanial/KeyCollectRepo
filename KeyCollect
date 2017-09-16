import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Window;
import javax.swing.ImageIcon;
import static java.lang.Math.*;

public class KeyCollect extends JFrame implements ActionListener {
	
	JPanel npl = new JPanel();
	JPanel dialog_pnl = new JPanel();
	JDialog jdl = new JDialog();
	//JDialog windlg = new JDialog();
	
	JButton[][] buttons = new JButton[9][9];
	JButton[][] buttons_jdialog = new JButton[4][6];
	
	Player[] players =  new Player[4];
	Keys[] keys = new Keys[6];
	
	ImageIcon P1,P2,P3,P4,pinkey,donkey,keydisk,keynote,monkey,chest;
	ImageIcon[] player_images = new ImageIcon[4];
	ImageIcon[] key_images = new ImageIcon[6];
	
	int[][] binary_matrix = new int[9][9]; /* 0:empty 1:occupied */
	
	int which_player_turn = 0; /* indicate who's turn is it for the actionlistener block */
	Point[] temps = new Point[4]; /* store the old coordinates, to be compared with the new, i.e i and j */
	
	double d;
	int which_key, winner;
	double max_dist;
	boolean you_shall_pass;
	boolean at_key;
	boolean revert_to_key_image;
	int temp_key;
	
	int which_player_turn_clone =0;

	
	
	public static void main(String[] args){
		KeyCollect k = new KeyCollect();
	}
	
	public KeyCollect(){

		super("Key Collector");
		setSize(600,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(300,80);
		npl.setLayout(new GridLayout(9,9));
		dialog_pnl.setLayout(new GridLayout(4,6));
		
		jdl.setTitle("SCOREBOARD");
		jdl.setSize(400,300);
		jdl.setResizable(false);
		jdl.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jdl.setLocation(900,200);
		
		temp_key = 0;
		
		
		dialog_pnl.setLayout(new GridLayout(4,6));
		
		/* add 9x9 grid of buttons with 81 actionlisteners */
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(this);
				npl.add(buttons[i][j]);
			}
		}
		
		/** add 4x6 grid to jdialog */
		for (int i=0;i<4;i++){
			for (int j=0;j<6;j++){
				buttons_jdialog[i][j] = new JButton();
				buttons_jdialog[i][j].setBackground(Color.white);
				buttons_jdialog[i][j].setBorder(null);
				dialog_pnl.add(buttons_jdialog[i][j]);
			}
		}
		
		jdl.add(dialog_pnl);
		add(npl);
		jdl.setVisible(true);
		
		
		/* initialize the binary matrix */
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				binary_matrix[i][j] = 0;
			}
		}
		
		
		P1=new ImageIcon(this.getClass().getResource("1.gif"));
		P2=new ImageIcon(this.getClass().getResource("2.gif"));
		P3=new ImageIcon(this.getClass().getResource("3.gif"));
		P4=new ImageIcon(this.getClass().getResource("4.gif"));
		chest=new ImageIcon(this.getClass().getResource("5.gif"));
		pinkey=new ImageIcon(this.getClass().getResource("a.gif"));
		donkey=new ImageIcon(this.getClass().getResource("b.gif"));
		keydisk=new ImageIcon(this.getClass().getResource("c.gif"));
		keynote=new ImageIcon(this.getClass().getResource("d.gif"));
		monkey=new ImageIcon(this.getClass().getResource("e.gif"));
		
		/* put images into a neat array for player and keys */
		player_images[0] = P1;
		player_images[1] = P2;
		player_images[2] = P3;
		player_images[3] = P4;
		
		key_images[0] = null;
		key_images[1] = pinkey;
		key_images[2] = donkey;
		key_images[3] = keydisk;
		key_images[4] = keynote;
		key_images[5] = monkey;
		
		/* shuffle initial player positions */
		List<Integer> solution = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) 
			solution.add(i);
		
		Collections.shuffle(solution);
	    Point[] butts = new Point[4];
	 	butts[0] = new Point(0,0); //initial player positions
		butts[1] = new Point(0,8);
		butts[2] = new Point(8,0);
		butts[3] = new Point(8,8);
		
		for (int i=0;i<4;i++){
			if (solution.get(i) == 0){
				players[0] = new Player(butts[i]); //set 4 players' initial coordinates
			}
			else if (solution.get(i) == 1){
				players[1] = new Player(butts[i]);
			}
			else if (solution.get(i) == 2){
			    players[2] = new Player(butts[i]);
			}
			else if (solution.get(i) == 3){
			    players[3] = new Player(butts[i]);
			}
		} 
	
	    /* set the images corresponding to each player, now that coordinates are set */
		buttons[players[0].player_pos.getX()][players[0].player_pos.getY()].setIcon(P1);
		buttons[players[1].player_pos.getX()][players[1].player_pos.getY()].setIcon(P2);
		buttons[players[2].player_pos.getX()][players[2].player_pos.getY()].setIcon(P3);
		buttons[players[3].player_pos.getX()][players[3].player_pos.getY()].setIcon(P4);

		buttons[4][4].setIcon(chest); 
		
		buttons_jdialog[0][0].setIcon(P1);
		buttons_jdialog[1][0].setIcon(P2);
		buttons_jdialog[2][0].setIcon(P3);
		buttons_jdialog[3][0].setIcon(P4);
		
		
		
		/* useless debugging output */
		System.out.println("Player 1 point : (" + players[0].player_pos.getX() + "," + players[0].player_pos.getY() + ")");
		System.out.println("Player 2 point : (" + players[1].player_pos.getX() + "," + players[1].player_pos.getY() + ")");
		System.out.println("Player 3 point : (" + players[2].player_pos.getX() + "," + players[2].player_pos.getY() + ")");
		System.out.println("Player 4 point : (" + players[3].player_pos.getX() + "," + players[3].player_pos.getY() + ")");
		
		/* set 1 to in bin matrix where space is taken by player*/
		for (int i=0;i<4;i++)
			binary_matrix[players[i].player_pos.getX()][players[i].player_pos.getY()] = 1;
		
		binary_matrix[4][4] = 1; /* for chest */
		
		/* print bin matrix for debugging */
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++)
				System.out.print(binary_matrix[i][j] + " ");
			System.out.println("");
		}
		
		/* initialize temps to store the initial (old) coordinates of players */
		for (int i=0;i<4;i++)
			temps[i] = new Point(players[i].player_pos.getX(), players[i].player_pos.getY());
		
		Random rand = new Random();

		/* assigns keys to locations that don't overlap */
		int randX, randY;
		for (int i=1;i<6;i++){
			while (true)
			{
				randX = rand.nextInt(8) + 0;
				randY = rand.nextInt(8) + 0;
				if (binary_matrix[randX][randY] == 0){
					keys[i] =  new Keys(new Point(randX, randY));
					binary_matrix[randX][randY] = 2;
					break;
				}
			}
			buttons[randX][randY].setIcon(key_images[i]);
         
		}
		keys[0] = new Keys();
		/* initialize key codes */
		for (int i=0;i<6;i++) { keys[i].setKeyCode(i); }
		
		buttons_jdialog[which_player_turn][0].setBackground(Color.blue);

		
		setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		   
		   which_player_turn %= 4;
		  
		   
		   
		   
		   
		   
		   System.out.println("Player " + which_player_turn + " now has key " + players[which_player_turn].getPlayerKey());
		   System.out.println("Player " + which_player_turn + " now has " + players[which_player_turn].getNoKeys() + " keys");
		   
		   Object source = ae.getSource();    
		   for (int i=0;i<9;i++){
			   for (int j=0;j<9;j++){
				   if (source == (buttons[i][j])){
					   
					   if ( (players[which_player_turn].getNoKeys() >= 5))
						     binary_matrix[4][4] = 0;
							
						
					   System.out.println("-----------------------------------------");
					   System.out.println("Button " + i + " " + j + " pressed");
					   
					      
						  if (players[which_player_turn].getPlayerKey() == 0){
								/** REGULAR MOVE **/
								max_dist = 3; //actually 2, but 2.9 also count
								System.out.println("Maximum 2 spaces");
						  }
						  else if (players[which_player_turn].getPlayerKey() == 1){
							  /** PINKEY **/
							    max_dist = 1.5;
								System.out.println("Maxium 1 space");
						  }
						  else if (players[which_player_turn].getPlayerKey() == 2){
							  /** DONKEY **/
							  max_dist = 4.5;
							  System.out.println("Maximum 3 spaces");
						  }
						  else if (players[which_player_turn].getPlayerKey() == 3){
							  /** KEYDISK **/
							  max_dist = 4;
							  System.out.println("Maximum 3 spaces");
						  }
						  else if (players[which_player_turn].getPlayerKey() == 4){
							  /** KEYNOTE **/
							  max_dist = 3;
							  System.out.println("More than 1 space, maximum 2");
						  }
						  else if (players[which_player_turn].getPlayerKey() == 5){
							  /** MONKEY **/
							    max_dist = 5; //some random number. not needed 
								System.out.println("Maximum 3 spaces");
						  }
							  
					   
					     d = sqrt( pow((i - temps[which_player_turn].getX()),2) + pow((j - temps[which_player_turn].getY()),2) );
						 System.out.println("Distance is : " + d);
						 
                         you_shall_pass = false;
						 at_key = false;
					     if ( ((binary_matrix[i][j] == 0) || (binary_matrix[i][j] == 2)) && (d < max_dist)){
							 
							 if (players[which_player_turn].getPlayerKey() == 3)
							 {
								 /** KEYDISK **/
								 /* checks that either x-axis or y-axis of the new coordinate is the same with old coordinate */
								 if ( (i == temps[which_player_turn].getX()) || (j == temps[which_player_turn].getY()) )
								    you_shall_pass = true;
									
							 }
			
							 else if (players[which_player_turn].getPlayerKey() == 2)
							 {
								 /** DONKEY **/
								 /* checks that both the x-axis and y-axis of the new coordinate are different than old coordinate */
								 if ( (i != temps[which_player_turn].getX()) && (j != temps[which_player_turn].getY()) )
								     you_shall_pass = true;
							 }
							 
							 else if (players[which_player_turn].getPlayerKey() == 4)
							 {
								 /** KEYNOTE **/
								 if (d > 1.5)
									 you_shall_pass = true;
							 }
							 
							 else if (players[which_player_turn].getPlayerKey() == 5)
							 {
								 /** MONKEY **/
								 
								 /** if the new position is NOT diagonal **/
								 if ( (i == temps[which_player_turn].getX()) || (j == temps[which_player_turn].getY()) ){
									 if (d < 4)
										 you_shall_pass = true;
								 }
								 else {//new position is diagonal
								     if (d < 4.5)
										 you_shall_pass = true;
								 }
									 
							 }
							 else { you_shall_pass = true; } // for key 0 and 1

                           			
                                if (you_shall_pass){		

									if ( (players[which_player_turn].getNoKeys() >= 5) && (i==4) && (j==4) ){
										JOptionPane.showMessageDialog(this, "Player " +  (which_player_turn+1) + " WINS!");
										/* quit the program */
									}
									
									for (int z=0;z<4;z++)
										buttons_jdialog[z][0].setBackground(Color.white);
									
									which_player_turn_clone = which_player_turn;
									if (which_player_turn_clone == 3)
										which_player_turn_clone = -1;
									buttons_jdialog[which_player_turn_clone+1][0].setBackground(Color.blue);
									
									/* when player lands on a key, updates the player_key increase no_keys by 1 **/ 
									which_key = 0;
									for (int k=1;k<6;k++){
										if ((i == keys[k].key_pos.getX()) && (j == keys[k].key_pos.getY())){
											at_key = true;
											System.out.println("key " + k + " at position : " + keys[k].key_pos.getX() + "," + keys[k].key_pos.getY() );
											
											which_key = k;
											
											System.out.println("------------------ which_key is " + which_key + " -------------------");
											System.out.println("Key " + k + " pressed");
											players[which_player_turn].setPlayerKey(which_key);
											
											if(players[which_player_turn].getPlayerKeyArray(k) == 0){
												/* put back here*/
												players[which_player_turn].setPlayerKeyArray(k, 1);
												players[which_player_turn].setNoKeys(players[which_player_turn].getNoKeys()+1);
												buttons_jdialog[which_player_turn][players[which_player_turn].getNoKeys()].setIcon(key_images[players[which_player_turn].getPlayerKey()]);
											}
											else
												System.out.println("Key taken already");
											
											
											
											System.out.println("Player " + which_player_turn + " now has key " + players[which_player_turn].getPlayerKey());
											System.out.println("Player " + which_player_turn + " now has " + players[which_player_turn].getNoKeys() + " keys");
											

											binary_matrix[i][j] = 0;
											System.out.println("Key " + k + " has moved to " + keys[k].key_pos.getX() + "," + keys[k].key_pos.getY());
											
											for (int r=0;r<9;r++){
												for (int c=0;c<9;c++)
													System.out.print(binary_matrix[r][c] + " ");
												System.out.println("");
											}
										}
										
										
									}
							        players[which_player_turn].player_pos.setX(i);
									players[which_player_turn].player_pos.setY(j);
									
							        System.out.println("Player " + which_player_turn + " is at coordinates (" + players[which_player_turn].player_pos.getX() + "," + players[which_player_turn].player_pos.getY() + ")");
							 
									/* set the old location to 0 */
									binary_matrix[temps[which_player_turn].getX()][temps[which_player_turn].getY()] = 0;
									/* set the new location to 1 */
									binary_matrix[i][j] = 1;
						
									
									/* change the new location image */
									buttons[i][j].setIcon(player_images[which_player_turn]);
									/* nullify old location image */
									buttons[temps[which_player_turn].getX()][temps[which_player_turn].getY()].setIcon(null);
									
									for (int q=1;q<6;q++){
										for (int a=0;a<4;a++){
											if ( (players[a].player_pos.getX() == keys[q].key_pos.getX()) && (players[a].player_pos.getY() == keys[q].key_pos.getY()) ){
												buttons[players[a].player_pos.getX()][players[a].player_pos.getY()].setIcon(player_images[a]);
												System.out.println("IMAGE CHANGED COMRADE");
												break;
												
											}
											else
												buttons[keys[q].key_pos.getX()][keys[q].key_pos.getY()].setIcon(key_images[q]);
								
										}
										
									}
								
									
									/* set the old coordinates to current coordinates */
									temps[which_player_turn].setX(i);
									temps[which_player_turn].setY(j);
									
							
									which_player_turn++;	
									System.out.println("-----------------------------------------");
								}//end of youshallpass
								else
									System.out.println("INVALID MOVE");
						} 
						else
							System.out.println("Invalid move");
				    }
			    }
		    }
		   
	 
	}
	
}
