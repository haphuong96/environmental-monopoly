/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author
 *
 */
public class GameSystem {

	private static final int NUM_OF_PLAYERS_MIN = 2;
	private static final int NUM_OF_PLAYERS_MAX = 4;
	private static final int NUM_OF_DICES = 2;

	private static Player[] players;

	private static Board board = new Board();

	private static Dice[] dices = new Dice[NUM_OF_DICES];

	private static boolean gameExit = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to Save Our Planet! Have fun!");

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
		// Register number of players
		int numOfPlayers;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of players!");

		while (true) {
			String playerInput = scanner.nextLine();

			try {
				numOfPlayers = Integer.parseInt(playerInput);

				if (numOfPlayers < NUM_OF_PLAYERS_MIN || numOfPlayers > NUM_OF_PLAYERS_MAX) {
					System.err.println("Sorry, this game only allows 2-4 players, please enter a new number");
					continue;
				}

				break;

			} catch (NumberFormatException e) {
				System.err.println("You should enter a valid number. Please try again.");
			}
		}
		
		// Register player names
		players = new Player[numOfPlayers];
		List<String> playerNames = new ArrayList<>();

		Square startingSq = board.getSquare(0);

		for (int i = 0; i < players.length; i++) {
			Player player = new Player(startingSq);

			while (true) {
				System.out.printf("Please enter player %d name:\n", i + 1);
				String name = scanner.nextLine();

				if (playerNames.contains(name)) {
					System.err.println("Sorry, that name has been taken, please enter another name\n.");
					continue;
				}

				// add to list of names
				playerNames.add(name);
				// set player
				player.setName(name);
				players[i] = player;

				System.out.println("Player " + (i + 1) + " has been named " + player.getName() + "!");
				break;
			}

		}

	}

	/**
	 * Start the gameplay. Each player will take turns to make a move. In each turn,
	 * the player will be able to throw dices and move to a new position on the
	 * board. The player will also have an opportunity to start an area development
	 * in their turn.
	 */
	public static void startGame() {
		// create dices
		for (int i = 0; i < dices.length; i++) {
			dices[i] = new Dice();
		}

		System.out.println("Start the game");
		Scanner scanner = new Scanner(System.in);

		int numOfPlayersAlive;

		do {
			numOfPlayersAlive = 0;
			for (Player player : players) {
				if (player.isAlive()) {
					// count to number of alive members
					numOfPlayersAlive++;

					System.out.println();
					System.out.println(
							"------------------------------------------------------------------------------------------------------------------");
					System.out.println("It's player " + player.getName() + "'s turn!");
					System.out.println("Your current balance is " + player.getBalance());
					System.out.println(
							"Please hit 'ENTER' to throw dices and start your move! If you want to exit the game, type 'Exit'.");
					String playerInput = scanner.nextLine();

					if (playerInput.equalsIgnoreCase("exit")) {
						gameExit = true;
						break;
					}

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
					player.offerToDevelopArea();
				}
			}
		} while (numOfPlayersAlive > 1 && !gameExit);

		scanner.close();
	}

	/**
	 * When the game ends, display the amount of resource each player holds.
	 */
	public static void evaluateResult() {
		System.out.println("---------------------------Game Evaluation------------------------------");
		if (!gameExit) {
			Player winner = null;

			// find the winner
			for (Player player : players) {
				if (player.isAlive()) {
					winner = player;
				}
			}

			if (winner != null) {
				System.out.println("Player " + winner.getName() + " is the winner!\n");
			}
		}

		// display player's resources
		for (Player player : players) {
			player.showDetails();
			System.out.println();
		}

	}

}
