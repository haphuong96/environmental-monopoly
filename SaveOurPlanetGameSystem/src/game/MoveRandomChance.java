/**
 * 
 */
package game;

import java.util.Random;

/**
 * @author HA PHUONG
 *
 */
public class MoveRandomChance extends Chance {
	
	private static final int MIN_RANDOM_MOVES = 1;
	private static final int MAX_RANDOM_MOVES = 3;

	@Override
	public void activateChance(Player player, Board board) {
		System.out.println("You will now make a random jump to a new location...");
		// Get a random number of moves, from MIN_RANDOM_MOVES - MAX_RANDOM_MOVES
		Random random = new Random();

		int moves = random.nextInt(MAX_RANDOM_MOVES) + MIN_RANDOM_MOVES;
		boolean isForwardMovement = random.nextBoolean();

		if (isForwardMovement) {
			System.out.printf("You will move forward %d square(s)\n", moves);
			player.move(moves, board);
		} else {
			System.out.printf("You will move backward %d square(s)\n", moves);
			player.moveBackward(moves, board);
		}	
	}

}
