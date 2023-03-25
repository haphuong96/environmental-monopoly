/**
 * 
 */
package game;

import java.util.Random;

/**
 * @author HA PHUONG
 *
 */
public class ChanceCardRandomMove extends ChanceCard {
	private static final int MIN_RANDOM_MOVES = 1;
	private static final int MAX_RANDOM_MOVES = 3;

	@Override
	public void activateChance(Player player, Board board) {
		System.out.printf(
				"You draw %s. You will move forward or backward for a random number of of moves from %d to %d forward or backward.\n",
				this.getName(), MIN_RANDOM_MOVES, MAX_RANDOM_MOVES);
		// Get a random number of moves, from MIN_RANDOM_MOVES - MAX_RANDOM_MOVES
		Random random = new Random();

		int moves = random.nextInt(MAX_RANDOM_MOVES) + MIN_RANDOM_MOVES;
		boolean isForwardMovement = random.nextBoolean();

		if (isForwardMovement) {
			System.out.printf("You will now move forward %d square(s)\n", moves);
			player.move(moves, board);
		} else {
			System.out.printf("You will now move backward %d square(s)\n", moves);
			player.moveBackward(moves, board);
		}

		// Activate landing square event
		Square landingSquare = board.getSquare(player.getPosition());
		landingSquare.activateEvent(player, board);
	}

}
