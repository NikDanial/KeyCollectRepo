/**
 * class for TutorialTexts to store texts in tutorial section 
 * @author Nik
 * @param none
 * @return none
 */
 
public class TutorialTexts{
	private String pinkeyText  = "  Pinkey  : Allowed to move in any direction, but only 1 space";
	private String donkeyText  = "  Donkey  : Allowed to move up to 3 spaces but only diagonally";
	private String keydiskText = "  KeyDisk : Allowed to move up to 3 spaces but only horizontally or vertically";
	private String keynoteText = "  KeyNote : Allowed to move only exactly 2 spaces, i.e must skip first space";
	private String monkeyText  = "  Monkey  : Allowed to move up to 3 spaces in any direction";
	
	private String space = "   ";
	
	private String instruct1 =  "  There are 4 Playes. To win, a player needs to collect all 5 keys and"; 
	private String instruct2 =  "  go to the middle of the board to unlock the treasure chest.";
        private String instruct3 =  "  The players take turns to move. Initially each player can only move"; 
	private String instruct4 =  "  1 or 2 squares in any direction at a time. When they collect a key,"; 
        private String instruct5 =  "  the last key collected restricts their movement :";

	/**
	 * get method for given variable
	 * @author Nik
	 * @return pinkeyText 
	 */
	public String getPinkeyText() { return pinkeyText; }
	/**
	 * get method for given variable
	 * @author Nik
	 * @return donkeyText
	 */
	public String getDonkeyText() { return donkeyText; }
	/**
	 * get method for given variable
	 * @author Nik
	 * @return keydiskText
	 */
	public String getKeydiskText() { return keydiskText; }
	/**
	 * get method for given variable
	 * @author Nik
	 * @return keynoteText
	 */
	public String getKeynoteText() { return keynoteText; }
	/**
	 * get method for given variable
	 * @author Nik
	 * @return monkeyText
	 */
	public String getMonkeyText() { return monkeyText; }
	/**
	 * get method for given variable
	 * @author Nik
	 * @return space 
	 */
	public String getSpace() { return space; }
	/**
	 * get method for given variable
	 * @author Nik
	 * @return instruct1 
	 */
	public String getInstruct1() { return instruct1; }
	/**
	 * get method for given variable
	 * @author Nik
	 * @return instruct2
	 */
	public String getInstruct2() { return instruct2; }
	/**
	 * get method for given variable
	 * @author Nik
	 * @return instruct3
	 */
	public String getInstruct3() { return instruct3; }
	/**
	 * get method for given variable
	 * @author Nik
	 * @return instruct4
	 */
	public String getInstruct4() { return instruct4; }
	/**
	 * get method for given variable
	 * @author Nik
	 * @return instruct5
	 */
	public String getInstruct5() { return instruct5; }
}
