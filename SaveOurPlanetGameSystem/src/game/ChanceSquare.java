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
	
	private Chance[] chancePool;
	
	public ChanceSquare(String name, int index) {
		super(name, index);
	}
	
	public Chance[] getChancePool() {
		return chancePool;
	}

	public void setChancePool(Chance[] chancePool) {
		this.chancePool = chancePool;
	}

	@Override
	public void activate(Player player, Board board) {
		super.activate(player, board);
		// draw a random card
		Chance chance = this.draw(this.chancePool);
		
		chance.activateChance(player, board);
		
		System.out.println("Chance ends.");
	}
	
	/**
	 * Draw a random chance from the chance pool
	 * @param pool
	 * @return
	 */
	public Chance draw(Chance[] pool) {
		Random random = new Random();
		int chanceIndex = random.nextInt(pool.length);
		Chance cardDrawn = pool[chanceIndex];
		
		return cardDrawn;
	}
}
