/**
 * 
 */
package game;

/**
 * @author HA PHUONG
 * 
 * An interface that represents a game event. An event is triggered following
 * some certain behaviors from the player. For example, when the player lands on
 * a new position, system will trigger the appropriate event in respective
 * square. Or when the player/system draws a chance from the chance pool, an
 * event associated with the chance is also triggered.
 */
public interface IEvent {

	/**
	 * Activate an event in the game. This method is currently used for Chance card
	 * activation and Square event activation.
	 * 
	 * @param player
	 * @param board
	 */
	public void activate(Player player, Board board);
}
