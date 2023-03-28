/**
 * 
 */
package game;

/**
 * @author HA PHUONG
 *
 */
public interface iActivate {
	
	/**
	 * Activate an event in the game.
	 * This method is currently used for Chance card activation and Square event activation.
	 * @param player
	 * @param board
	 */
	public void activate(Player player, Board board);
}
