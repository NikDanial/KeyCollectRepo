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

/**
 * class KeyCollect that configures the 9x9 board with its respective components
 * @author Nik, Aneek, Arif
 * @param none
 * @return none
 */
public class KeyCollect extends JFrame implements ActionListener {
	
	/** creates panels npl(panel for 9x9 board), dialog_pnl(panel for scoreboard), tuto_pnl(panel for tutorial dialog box),
	 * and tuto_text_pnl (panel for tutorial text box 
	 */
	JPanel npl = new JPanel();
	JPanel dialog_pnl = new JPanel();
	JPanel tuto_pnl = new JPanel();
	JPanel tuto_text_pnl = new JPanel();
	
	/** creates dialog boxes for scoreboard, tutorial dialog, and tutorial text */
	JDialog jdl = new JDialog();
	JDialog tutorial = new JDialog(); 
	JDialog tuto_text = new JDialog();
	
	/** creates a 9x9 JButton array for the 9x9 board and
	 *  4x6 JButton array for the scoreboard
	 */
	JButton[][] buttons = new JButton[9][9];
	JButton[][] buttons_jdialog = new JButton[4][6];
	
	/** creates 4 player objects, 6 key objects (0 for null key) and a new TutorialTexts object */
	Player[] players =  new Player[4];
	Keys[] keys = new Keys[6];
	TutorialTexts tt = new TutorialTexts();
	
	/** creates image icons and image icon arrays to add to buttons */
	ImageIcon P1,P2,P3,P4,pinkey,donkey,keydisk,keynote,monkey,chest;
	ImageIcon[] player_images = new ImageIcon[4];
	ImageIcon[] key_images = new ImageIcon[6];
	
											
	/** 9x9 binary matrix for logic. 0:empty, 1:occupied */
	int[][] binary_matrix = new int[9][9]; 
	/** store the old coordinates, to be compared with the new, i.e i and j */
	Point[] temps = new Point[4]; 
	
	double d; /** distance calculation */
	int which_key, winner; /** which key is selected, and which is the winner */
	double max_dist;
	boolean you_shall_pass;
	boolean at_key;
	boolean revert_to_key_image;
	int temp_key;
	int which_player_turn = 0; /* indicate who's turn is it for the actionlistener block */
	int which_player_turn_clone =0;

	
	/**
	* class KeyCollect default constructor
	* @author Nik, Aneek, Arif
	* @param none
	* @return none
	*/
	public KeyCollect(){

	    /** Creates the Board JFrame */
		super("Key Collector");
		setSize(600,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(300,80);
		npl.setLayout(new GridLayout(9,9));
		dialog_pnl.setLayout(new GridLayout(4,6));
		
		/** Creates the Scoreoard JDialog */
		jdl.setTitle("SCOREBOARD");
		jdl.setSize(400,300);
		jdl.setResizable(false);
		jdl.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jdl.setLocation(900,80);
		
		/** Creates the Tutorial JDialog */
		tutorial.setTitle("TUTORIAL");
		tutorial.setSize(400,100);
		tutorial.setLayout(new FlowLayout());
		tutorial.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tutorial.setLocationRelativeTo(null);
		
		/** Creates the Tutorial text JDialog */
		tuto_text.setTitle("TUTORIAL");
		tuto_text.setSize(450,250);
		tuto_text.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tuto_text.setLocation(895,380);
		
		/** Adding text from TutorialTexts class into tuto_text_pnl */
		tuto_text_pnl.setLayout(new BoxLayout(tuto_text_pnl, BoxLayout.Y_AXIS));
		tuto_text_pnl.add(new JLabel(tt.getSpace()));
		tuto_text_pnl.add(new JLabel(tt.getInstruct1()));
		tuto_text_pnl.add(new JLabel(tt.getInstruct2()));
		tuto_text_pnl.add(new JLabel(tt.getInstruct3()));
		tuto_text_pnl.add(new JLabel(tt.getInstruct4()));
		tuto_text_pnl.add(new JLabel(tt.getInstruct5()));
		tuto_text_pnl.add(new JLabel(tt.getSpace()));
		tuto_text_pnl.add(new JLabel(tt.getPinkeyText()));
		tuto_text_pnl.add(new JLabel(tt.getDonkeyText()));
		tuto_text_pnl.add(new JLabel(tt.getKeydiskText()));
		tuto_text_pnl.add(new JLabel(tt.getKeynoteText()));
		tuto_text_pnl.add(new JLabel(tt.getMonkeyText()));
		tuto_text.add(tuto_text_pnl);
		
		tuto_pnl.add(new JLabel("Do you wish to view the tutorial?     "));
		JButton yes = new JButton("YES");
		JButton no = new JButton("NO");
		
		/** if Yes selected, go to actionPerformed defintion */
		yes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                yesActionPerformed(evt);
            }
        });
		/** if No selected, go to actionPerformed defintion */
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				noActionPerformed(evt);
			}
		});
		
		tuto_pnl.add(yes); /** add yes and no buttons to tutorial JDialog */
		tuto_pnl.add(no);
		tutorial.add(tuto_pnl);
		
		temp_key = 0;
		dialog_pnl.setLayout(new GridLayout(4,6));
		

		/** add 9x9 grid of buttons with 81 actionlisteners */
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
		
		/** initialize the binary matrix */
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				binary_matrix[i][j] = 0;
			}
		}
		
		/** set the image icons to the gif images */
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
		
		/** put images into a neat array for player and keys */
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
		
		/** creates indexes for all 4 players to be shuffled for random placement on the board */
		List<Integer> solution = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) 
			solution.add(i);
		
		/** shuffle initial player positions */
		Collections.shuffle(solution);
	    Point[] butts = new Point[4];
	 	butts[0] = new Point(0,0); /** initial player positions */
		butts[1] = new Point(0,8);
		butts[2] = new Point(8,0);
		butts[3] = new Point(8,8);
		
		/** set 4 players' initial coordinates */
		for (int i=0;i<4;i++){
			if (solution.get(i) == 0){
				players[0] = new Player(butts[i]); 
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
	
	    /** set the images corresponding to each player, now that coordinates are set */
		buttons[4][4].setIcon(chest); 
		
		buttons[players[0].player_pos.getX()][players[0].player_pos.getY()].setIcon(P1);
		buttons[players[1].player_pos.getX()][players[1].player_pos.getY()].setIcon(P2);
		buttons[players[2].player_pos.getX()][players[2].player_pos.getY()].setIcon(P3);
		buttons[players[3].player_pos.getX()][players[3].player_pos.getY()].setIcon(P4);
		
		buttons_jdialog[0][0].setIcon(P1);
		buttons_jdialog[1][0].setIcon(P2);
		buttons_jdialog[2][0].setIcon(P3);
		buttons_jdialog[3][0].setIcon(P4);
		
		/** set 0 to 1 in binary matrix where space is taken by player*/
		for (int i=0;i<4;i++)
			binary_matrix[players[i].player_pos.getX()][players[i].player_pos.getY()] = 1;
		
		binary_matrix[4][4] = 1; /** for chest */
		
		/** initialize temps to store the initial (old) coordinates of players */
		for (int i=0;i<4;i++)
			temps[i] = new Point(players[i].player_pos.getX(), players[i].player_pos.getY());
		
		Random rand = new Random();

		/** assigns keys to locations that don't overlap */
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
		tutorial.setVisible(true);
		
		
	}
	/** actionListener event definition for button press event in 9x9 grid
	 * @param ae the event thrown from button press in 9x9 grid
	 * @return none
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		   
		   which_player_turn %= 4;
		   
		   Object source = ae.getSource(); 
		   for (int i=0;i<9;i++){
			   for (int j=0;j<9;j++){
				   if (source == (buttons[i][j])){ /** if the source of the click is equals to that particular button */
					   
					   if ( (players[which_player_turn].getNoKeys() >= 5)) /** if the current player has >= 5 keys, chest location is accessible */
						     binary_matrix[4][4] = 0;
							
						
					   System.out.println("-----------------------------------------");
					   System.out.println("Button " + i + " " + j + " pressed");
					   
					      /** Initial default move restriction (no keys yet) */
						  if (players[which_player_turn].getPlayerKey() == 0){
								max_dist = 3; //actually 2, but 2.9 also count
								System.out.println("Maximum 2 spaces");
						  }
						  /** current key is Pinkey */
						  else if (players[which_player_turn].getPlayerKey() == 1){
							    max_dist = 1.5;
								System.out.println("Maxium 1 space");
						  }
						   /** current key is Donkey */
						  else if (players[which_player_turn].getPlayerKey() == 2){
							  max_dist = 4.5;
							  System.out.println("Maximum 3 spaces");
						  }
						   /** current key is Keydisk */
						  else if (players[which_player_turn].getPlayerKey() == 3){
							  max_dist = 4;
							  System.out.println("Maximum 3 spaces");
						  }
						   /** current key is Keynote */
						  else if (players[which_player_turn].getPlayerKey() == 4){
							  max_dist = 3;
							  System.out.println("More than 1 space, maximum 2");
						  }
						   /** current key is Monkey */
						  else if (players[which_player_turn].getPlayerKey() == 5){
							    max_dist = 5; //some random number. not needed 
								System.out.println("Maximum 3 spaces");
						  }
							  
					     /** calculate the distance between the player location and the click whether it is within range */
					     d = sqrt( pow((i - temps[which_player_turn].getX()),2) + pow((j - temps[which_player_turn].getY()),2) );
						 System.out.println("Distance is : " + d);
						 
                         you_shall_pass = false;
						 at_key = false;
						 
						 /** checks whether the location clicked is occupied, and the new location is within distance */
					     if ( ((binary_matrix[i][j] == 0) || (binary_matrix[i][j] == 2)) && (d < max_dist)){
							 
							 /** For KEYDISK */
							 if (players[which_player_turn].getPlayerKey() == 3)
							 {
								 /** checks that either x-axis or y-axis of the new coordinate is the same with old coordinate */
								 if ( (i == temps[which_player_turn].getX()) || (j == temps[which_player_turn].getY()) )
								    you_shall_pass = true;
									
							 }
							 /** For DONKEY */
							 else if (players[which_player_turn].getPlayerKey() == 2)
							 {
								 /* checks that both the x-axis and y-axis of the new coordinate are different than old coordinate */
								 if ( (i != temps[which_player_turn].getX()) && (j != temps[which_player_turn].getY()) )
								     you_shall_pass = true;
							 }
							 /** For KEYNOTE */
							 else if (players[which_player_turn].getPlayerKey() == 4)
							 {
								 if (d > 1.5)
									 you_shall_pass = true;
							 }
							 /** For MONKEY */
							 else if (players[which_player_turn].getPlayerKey() == 5)
							 {
								 /** if the new position is NOT diagonal */
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
										/** quit the program */
									}
									
									for (int z=0;z<4;z++)
										buttons_jdialog[z][0].setBackground(Color.white);
									
									which_player_turn_clone = which_player_turn;
									if (which_player_turn_clone == 3)
										which_player_turn_clone = -1;
									buttons_jdialog[which_player_turn_clone+1][0].setBackground(Color.blue);
									
									/** when player lands on a key, updates the player_key increase no_keys by 1 */ 
									which_key = 0;
									for (int k=1;k<6;k++){
										/** checks if new location is a location of a key */
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
							 
									/** set the old location to 0 */
									binary_matrix[temps[which_player_turn].getX()][temps[which_player_turn].getY()] = 0;
									/* set the new location to 1 */
									binary_matrix[i][j] = 1;
						
									
									/** change the new location image */
									buttons[i][j].setIcon(player_images[which_player_turn]);
									/** nullify old location image */
									buttons[temps[which_player_turn].getX()][temps[which_player_turn].getY()].setIcon(null);
									
									for (int q=1;q<6;q++){
										for (int a=0;a<4;a++){
											/** replace the previous icons with what they were before. Not that important to understand */
											if ( (players[a].player_pos.getX() == keys[q].key_pos.getX()) && (players[a].player_pos.getY() == keys[q].key_pos.getY()) ){
												buttons[players[a].player_pos.getX()][players[a].player_pos.getY()].setIcon(player_images[a]);
												break;
											}
											else
												buttons[keys[q].key_pos.getX()][keys[q].key_pos.getY()].setIcon(key_images[q]);
								
										}
										
									}
								
									
									/** set the old coordinates to current coordinates */
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
	/** defintion for yes button actionPerformed event
	 * @author nik
	 * @param evt event of yes button clicked
	 */
	private void yesActionPerformed(ActionEvent evt) {                                           
		System.out.println("YES IS CLICKED");
		tutorial.dispose();
		tuto_text.setVisible(true);
        
    }      
	/** defintion for no button actionPerformed event
	 * @author nik
	 * @param evt event of no button clicked
	 */
	private void noActionPerformed(ActionEvent evt) {                                           
		System.out.println("NO IS CLICKED");
		tutorial.dispose();
        
    }      
	
	
	
}
