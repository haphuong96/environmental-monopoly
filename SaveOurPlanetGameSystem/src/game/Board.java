/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HA PHUONG
 *
 */
public class Board {

	private Square[] squares;
	private AreaSquare[] areas;
	private iActivate[] chanceCards;

	/**
	 * Constructor to build the list of squares and fields.
	 */
	public Board() {
		this.build();

	}

	/**
	 * Build the board. Individual square information is stored in a csv file. This
	 * method will read information from input file to build the squares and the
	 * board.
	 */
	private void build() {

		/*
		 * String line; File file = new File("Board.csv");
		 * 
		 * FileReader fileReader; BufferedReader bufferedReader;
		 * 
		 * try {
		 * 
		 * fileReader = new FileReader(file); bufferedReader = new
		 * BufferedReader(fileReader);
		 * 
		 * line = bufferedReader.readLine();
		 * 
		 * while(line != null) {
		 * 
		 * String[] fields; if(line.contains(",")) { fields = line.split(","); } else {
		 * fields = new String[0]; fields[0] = line; }
		 * 
		 * }
		 * 
		 * bufferedReader.close(); fileReader.close();
		 * 
		 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
		 * e) { e.printStackTrace(); }
		 */

		// build chance decks
		chanceCards = new iActivate[2];
		chanceCards[0] = new BuyAreaCard();
		chanceCards[1] = new MoveRandomCard();

	}

	public int getBoardLength() {
		return squares.length;
	}

	public Square getSquare(int index) {
		return squares[index];
	}

	public iActivate[] getChanceCards() {
		return chanceCards;
	}
	
	public List<AreaSquare> getAvailableAreas() {
		ArrayList<AreaSquare> availableAreas = new ArrayList<>();

		for (AreaSquare area : this.areas) {
			if (area.getOwner() == null) {
				availableAreas.add(area);
			}
		}

		return availableAreas;
	}

	public void displayListAreaDetails() {
		for (AreaSquare area : areas) {
			area.displayAreaDetails();
		}
	}

	

	
}
