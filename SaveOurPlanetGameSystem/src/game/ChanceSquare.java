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
		iActivate[] chanceDeck = board.getChanceCards();
		
		// draw a random card
		Random random = new Random();
		int chanceIndex = random.nextInt(chanceDeck.length);
		iActivate cardDrawn = chanceDeck[chanceIndex];
		cardDrawn.activate(player, board);
		
		System.out.println("Chance ends.");
	}

}
