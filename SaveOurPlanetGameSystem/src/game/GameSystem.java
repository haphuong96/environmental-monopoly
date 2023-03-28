/**
 * 
 */
package game;

import java.util.ArrayList;
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

		int numOfPlayers;
		String name;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter num of players");
		numOfPlayers = scanner.nextInt();

		// Ensures that there are always 2-4 players
		while (numOfPlayers < 2 || numOfPlayers > 4) {
			System.out.println("Sorry, this game only allows 2-4 players, please enter a new number");
			numOfPlayers = scanner.nextInt();
		}

		scanner.nextLine();

		players = new Player[numOfPlayers];
		ArrayList<String> playerNames = new ArrayList<>();

		for (int loop = 0; loop < numOfPlayers; loop++) {

			Player player = new Player();

			System.out.println("Please enter player name");
			name = scanner.nextLine();

			// The system does not allow more than one player to have the same name (Case
			// sensitive)
			if (!playerNames.contains(name)) {
				playerNames.add(name);

				player.setName(name);
				players[loop] = player;

				System.out.println("Player " + (loop + 1) + " has been named " + name);

			} else {
				System.out.println("Sorry, that name has been taken, please enter another name\n");
				loop--;
			}

		}

		scanner.close();
	}

	/**
	 * Start the gameplay. Each player will take turns to make a move. In each turn,
	 * the player will be able to throw dices and move to a new position on the
	 * board. The player will also have an opportunity to start an area development
	 * in their turn.
	 */
	public static void startGame() {
		Scanner scanner = new Scanner(System.in);

		int numOfPlayersAlive;

		do {
			numOfPlayersAlive = 0;
			for (Player player : players) {
				if (player.isAlive()) {
					// count to number of alive members
					numOfPlayersAlive++;

					System.out.println("Please hit 'ENTER' to throw dices and start your move!");
					scanner.nextLine();

					// throw dices
					int diceResult = 0;
					for (Dice dice : dices) {
						dice.roll();
						diceResult += dice.getFaceValue();
					}
					System.out.printf("Player %s have rolled a %d and a %d - that totals %d.\n", player.getName(),
							dices[0].getFaceValue(), dices[1].getFaceValue(), diceResult);

					// start move event
					player.move(diceResult, board);

					// start development event
					player.offerToDevelopArea(player);
				}
			}
		} while (numOfPlayersAlive > 1);

		scanner.close();
	}

	/**
	 * When the game ends, display the amount of resource each player holds.
	 */
	public static void evaluateResult() {
		Player winner = null;
		
		// find the winner
		for (Player player : players) {
			if (player.isAlive()) {
				winner = player;
			}
		}
		
		if (winner != null) {
			System.out.println("Player " + winner.getName() + "is the winner!");
		}
		
		// display player's resources
		for (Player player : players) {
			player.showDetails();
		}
		
	}

}
