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
	private ChanceCard[] chanceCards;
	
	@Override
	public void activateEvent(Player player, Board board) {
		Random random = new Random();
		
		int chanceIndex = random.nextInt(chanceCards.length);
		
		chanceCards[chanceIndex].activateChance(player, board);
		
		System.out.println("Chance ends.");
	}

}
