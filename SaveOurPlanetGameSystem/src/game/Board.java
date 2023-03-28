/**
 * 
 */
package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author HA PHUONG
 *
 */
public class Board {

	private Square[] squares;
	private AreaSquare[] areas;
	private IEvent[] chancePool;
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

		
		  String line; File file = new File("Board.csv");
		  
		  FileReader fileReader;
		  BufferedReader bufferedReader;
		  
		  try {
		  
		  fileReader = new FileReader(file);
		  bufferedReader = new BufferedReader(fileReader);
		  
		  line = bufferedReader.readLine();
		  line = bufferedReader.readLine();
		  
		  squares = new ArrayList<>();
		  
		  while(line != null) {
		  
			  String squareInfo[] = line.split(",");
			  
			  switch (squareInfo[0]) { 
			  case ("Blank"):
				  
				  	BlankSquare blankSquare = new BlankSquare(squareInfo[1]);
			  		squares.add(blankSquare);
			  		
			  		break;
			  case ("Area"):
				  
				  	AreaSquare areaSquare = new AreaSquare();
//				  	AreaSquare areaSquare = new AreaSquare(null, squareInfo[3], squareInfo[4], squareInfo[5], squareInfo[6], squareInfo[7]);
			  		
			  		areaSquare.setName(squareInfo[1]);
			  		squares.add(areaSquare);
			  
			  		break;
			  case ("Donation"):
				  	
				  	Integer donationAmount;
			  		donationAmount = Integer.parseInt(squareInfo[8]);
				  	DonationSquare donationSquare = new DonationSquare(squareInfo[1], donationAmount);
				  	
				  	squares.add(donationSquare);
				  	
			  		break;
			  case ("Tax"):
				  	
				  	Integer taxAmount;
		  			taxAmount = Integer.parseInt(squareInfo[9]);
		  			TaxSquare taxSquare = new TaxSquare(squareInfo[1], taxAmount);
			  	
		  			squares.add(taxSquare);
				  
			  		break;
			  case ("Chance"):
				  	
				  	ChanceSquare chanceSquare = new ChanceSquare();
			  
			  		chanceSquare.setName(squareInfo[1]);
			  		squares.add(chanceSquare);
				  
			  		break;
			  default :
			  }
			  
			  line = bufferedReader.readLine();
			  
		  }
		  
		  bufferedReader.close(); fileReader.close();
		  
		  } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
		  e) { e.printStackTrace(); }
		  
		  // build chance decks
		chancePool = new IEvent[2];
		chancePool[0] = new BuyAreaChance();
		chancePool[1] = new MoveRandomChance();
		  
		 
	}

	public int getBoardLength() {
		return squares.length;
	}

	public Square getSquare(int index) {
		return squares[index];
	}
	
	public IEvent[] getChanceCards() {
		return chancePool;
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
