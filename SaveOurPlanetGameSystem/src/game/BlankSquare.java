/**
 * 
 */
package game;

/**
 * @author zholm
 *
 */
public class BlankSquare extends Square {
	
	/**
	 * 
	 */
	public BlankSquare(String name) {
		super(name);
	}

	@Override
	public void activateEvent(Player player, Board board) {
		System.out.println("You landed on a blank square");

	}

}
