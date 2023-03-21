/**
 * 
 */
package game;

/**
 * @author zholm
 *
 */
public class Player {
	private String name;
	private int position;
	private int balance;
	private boolean isAlive;
	
	/**
	 * Default constructor
	 */
	public Player() {
		
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * The player moves on the board to a new position, given the number of moves.
	 * 
	 * @param numOfMoves
	 */
	public void move(int numOfMoves) {
		
	}
	
	
	/**
	 * The player starts a development in a field they own.
	 */
	public void startDevelopment() {
		// system check if the player is qualified:
		// ++ Player owns a field
		// ++++ Check player must own all areas belong to the field.
		// ++++++ loop through the boards to check all areas owned by player
		// ++++++++++ Map 
		// ++ Player has enough money
		// ++ area is not fully developed
	}
	
	public void increaseBalance(int amount) {
		
	}
	
	public void decreaseBalance(int amount) {
		
	}
}
