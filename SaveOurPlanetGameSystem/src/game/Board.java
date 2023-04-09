/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import gamebuild.BuildAreaSquare;
import gamebuild.BuildBlankSquare;
import gamebuild.BuildChanceSquare;
import gamebuild.BuildDonationSquare;
import gamebuild.BuildTaxSquare;
import gamebuild.CompareSquareByLocation;

/**
 * @author HA PHUONG
 *
 */
public class Board {

	private Square[] squares;
	private AreaSquare[] areas;

	/**
	 * Constructor to build the list of squares and fields.
	 */
	public Board() {
		this.build();
	}

	/**
	 * Build the board. Individual square information is stored in csv files. This
	 * method will read information from input file to build the squares and the
	 * board.
	 */
	private void build() {
		List<Square> squareBuildList = new ArrayList<>();
		List<AreaSquare> areaBuildList = new ArrayList<>();
		// Threads for building squares

		Thread buildAreas = new Thread(new BuildAreaSquare(squareBuildList, areaBuildList));
		Thread buildBlanks = new Thread(new BuildBlankSquare(squareBuildList));
		Thread buildChance = new Thread(new BuildChanceSquare(squareBuildList));
		Thread buildDonation = new Thread(new BuildDonationSquare(squareBuildList));
		Thread buildTax = new Thread(new BuildTaxSquare(squareBuildList));

		// build
		buildAreas.run();
		buildBlanks.run();
		buildChance.run();
		buildDonation.run();
		buildTax.run();

		// wait for the squares to be finished with the build
		try {
			buildAreas.join();
			buildBlanks.join();
			buildChance.join();
			buildDonation.join();
			buildTax.join();

			// Sort square by location
			Collections.sort(squareBuildList, new CompareSquareByLocation());

			// set Board properties
			this.squares = squareBuildList.toArray(new Square[squareBuildList.size()]);
			this.areas = areaBuildList.toArray(new AreaSquare[areaBuildList.size()]);
			
			// link the squares together
			linkSquares();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void linkSquares() {
		for (int i = 1; i < squares.length - 1; i++) {
			squares[i].setNextSquare(squares[i + 1]);
			squares[i].setPrevSquare(squares[i - 1]);
		}

		// START square
		squares[0].setNextSquare(squares[1]);
		squares[0].setPrevSquare(squares[squares.length - 1]);

		// last square
		squares[squares.length - 1].setNextSquare(squares[0]);
		squares[squares.length - 1].setPrevSquare(squares[squares.length - 2]);
	}

	public Square getSquare(int index) {
		return squares[index];
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
