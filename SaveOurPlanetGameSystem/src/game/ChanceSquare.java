/**
 * 
 */
package game;

import java.util.Random;

/**
 * @author HA PHUONG
 *
 */
public class ChanceSquare extends Square {
	
	public ChanceSquare() {
		
	}
	
	@Override
	public void activate(Player player, Board board) {
		IEvent[] chanceDeck = board.getChanceCards();
		
		// draw a random card
		IEvent chance = this.draw(chanceDeck);
		
		chance.activate(player, board);
		
		System.out.println("Chance ends.");
	}
	
	/**
	 * Draw a random chance from the chance pool
	 * @param pool
	 * @return
	 */
	public IEvent draw(IEvent[] pool) {
		Random random = new Random();
		int chanceIndex = random.nextInt(pool.length);
		IEvent cardDrawn = pool[chanceIndex];
		
		return cardDrawn;
	}

}
