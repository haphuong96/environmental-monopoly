/**
 * 
 */
package game;


public class BlankSquare extends Square {

	/**
	 * 
	 * @param name
	 */
	public BlankSquare(String name, int index) {
		super(name, index);
	}
	
	@Override
	public void activate(Player player, Board board) {
		super.activate(player, board);
	}

}
