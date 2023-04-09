/**
 * 
 */
package game;

/**
 * @author
 *
 */
public class Square {

	private String name;
	private Square nextSquare;
	private Square prevSquare;
	private int index;
	
	public Square (String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public void setNextSquare (Square nextSquare) {
		this.nextSquare = nextSquare;
	}
	
	public Square getNextSquare () {
		return this.nextSquare;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * Activate event when a player lands on a square.
	 * @param player
	 * @param board
	 */
	public void activate(Player player, Board board) {
		System.out.printf("You landed on %s!\n", this.name);
	};
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Square getPrevSquare() {
		return prevSquare;
	}

	public void setPrevSquare(Square prevSquare) {
		this.prevSquare = prevSquare;
	}

}