/**
 * 
 */
package game;

import java.util.Scanner;

/**
 * @author
 *
 */
public class GameSystem {

	private static Player[] players;

	private static Board board = new Board();

	private static Dice[] dices;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// register number of players and player's name
		registerPlayers();

		// start the gameplay
		startGame();

		// Display each player's stats when the game ends
		evaluateResult();

	}

	/**
	 * Register players into the game. System will ask for user's input for the
	 * total number of players and individual player's name
	 */
	public static void registerPlayers() {

//		Scanner scanner = new Scanner(System.in);
//		System.out.println("	Enter num of players");
//		numOfPlayers = scanner.nextInt();
	}

	/**
	 * Start the gameplay. Each player will take turns to make a move. In each turn,
	 * the player will be able to throw dices and move to a new position on the
	 * board. The player will also have an opportunity to start an area development
	 * in their turn.
	 */
	public static void startGame() {
		
		int numOfPlayersAlive;
		
		do {
			numOfPlayersAlive = 0;
			for (Player player : players) {
				if (player.isAlive()) {
					// count to number of alive members
					numOfPlayersAlive++;
		
					// throw dice
					int diceResult = 0;
					for (Dice dice : dices) {
						dice.roll();
						diceResult += dice.getFaceValue();
					}
					// player move
					player.move(diceResult);
					
					// activate square event
					
					// player develop
					player.startDevelopment(board);
				}
			}
		} while (numOfPlayersAlive > 1);

	}

	/**
	 * When the game ends, display the amount of resource each player holds.
	 */
	public static void evaluateResult() {

	}

}
