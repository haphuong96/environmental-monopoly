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
	 * @param name
	 */
	public BlankSquare(String name) {
		this.setName(name);
	}

	@Override
	public void activate(Player player, Board board) {
		
	}

}
