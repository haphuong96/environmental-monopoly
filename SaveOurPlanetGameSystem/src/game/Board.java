/**
 * 
 */
package game;

/**
 * @author HA PHUONG
 *
 */
public class Board {
	
	private Square[] squares;

	/**
	 * 
	 */
	public Board() {
		this.build();
	}

	/**
	 * Build the board.
	 * Individual square information is stored in a csv file. This method will read
	 * information from input file to build the squares and the board.
	 */
	private void build() {

	}
	
	
	/**
	 *  Method for finding new user position.
	 */
	public static void findNewPosition(int playerPosition, int numberOfMoves) {
		
		// getting the current player
		Player currentPlayer = getCurrentPlayer(); 
		
		// Find the player's new position
		int newPlayerPosition = playerPosition+numberOfMoves;
		

		// Will need further logic here to set the player's new position (in Player Class Position int) 
		

		// Find the Square Type based on the new player position 
		String squareType = findSquareType(newPlayerPosition);
		
		
		if (squareType == "Tax Square Type") {
			TaxSquare.activateEvent(currentPlayer);
		} 
		else if (squareType == "Donation Square Type") {
			DonationSquare.activateEvent(currentPlayer);
		} 
		else {
		System.out.println("Move Complete");
		
		}

	}

	public static Player getCurrentPlayer() {

		// finds the current player and returns them
		Player dummyPlayer = "Placeholder Player 1";
		return dummyPlayer;

	}

	public static String findSquareType(int index) {

		// finds the new square that the user lands on and returns the square type
		String dummySquareType = "Tax Square";
		return dummySquareType;

	}
}
