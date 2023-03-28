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
import java.util.List;

/**
 * @author HA PHUONG
 *
 */
public class Board {
	
	private static final int NUM_DEVELOPMENTS_TO_UNLOCK_MAJOR_DEVELOPMENT = 3;

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
		
		  String line;
		  File fileBoard = new File("Board.csv");
		  File fileFields = new File("Fields.csv");
		  
		  ArrayList<Square> squaresFromFile = new ArrayList<>();
		  ArrayList<Field> fieldsFromFile = new ArrayList<>();
		  ArrayList<AreaSquare> areasFromFile = new ArrayList<>();
		  
		  FileReader fileReader;
		  BufferedReader bufferedReader;
		  
		  try {
			  
			fileReader = new FileReader(fileFields);
			bufferedReader = new BufferedReader(fileReader);
			
			line = bufferedReader.readLine();
			line = bufferedReader.readLine();
			
			while(line != null) {
				
				Field field = new Field(line); 
				line = bufferedReader.readLine();
				
				fieldsFromFile.add(field);
				
			}
			
		  } catch (FileNotFoundException e) {
			  	e.printStackTrace();
		  } catch (IOException e) {
			  	e.printStackTrace();
		  }
		  
		  Field[] fields = new Field[fieldsFromFile.size()];
		  
		  for (int loop = 0; loop < fields.length; loop++) {
				fields[loop] = fieldsFromFile.get(loop);
			} 
		  
		  try {
		  
		  fileReader = new FileReader(fileBoard);
		  bufferedReader = new BufferedReader(fileReader);
		  
		  line = bufferedReader.readLine();
		  line = bufferedReader.readLine();
		  
		  while(line != null) {
		  
			  String squareInfo[] = line.split(",");
			  
			  switch (squareInfo[0]) { 
			  case ("Blank"):
				  
				  	BlankSquare blankSquare = new BlankSquare(squareInfo[1]);
			  		squaresFromFile.add(blankSquare);
			  		
			  		break;
			  case ("Area"):
				  
				  	AreaSquare areaSquare = new AreaSquare();

			  		int fieldIndex, cost, developmentCost, majorDevelopmentFee, basicEntranceFee, entranceFeeWithMajorDevelopment;
			  		int[] entraceFeeWithDevelopment = new int[NUM_DEVELOPMENTS_TO_UNLOCK_MAJOR_DEVELOPMENT];

			  		fieldIndex = Integer.parseInt(squareInfo[2]);
			  		cost = Integer.parseInt(squareInfo[3]);
			  		developmentCost = Integer.parseInt(squareInfo[4]);
			  		majorDevelopmentFee = Integer.parseInt(squareInfo[5]);
			  		basicEntranceFee = Integer.parseInt(squareInfo[6]);
			  		entraceFeeWithDevelopment[0] = Integer.parseInt(squareInfo[7]);
			  		entraceFeeWithDevelopment[1] = Integer.parseInt(squareInfo[8]);
			  		entraceFeeWithDevelopment[2] = Integer.parseInt(squareInfo[9]);
			  		entranceFeeWithMajorDevelopment = Integer.parseInt(squareInfo[10]);
			  		
			  		areaSquare.setField(fields[fieldIndex]);
			  		areaSquare.setCost(cost);
			  		areaSquare.setDevelopmentCost(developmentCost);
			  		areaSquare.setMajorDevelopmentCost(majorDevelopmentFee);
			  		areaSquare.setBasicEntranceFee(basicEntranceFee);
			  		areaSquare.setEntraceFeeWithDevelopment(entraceFeeWithDevelopment); 
			  		areaSquare.setEntranceFeeWithMajorDevelopment(entranceFeeWithMajorDevelopment);
			  
			  		squaresFromFile.add(areaSquare);
			  		areasFromFile.add(areaSquare);
			  		fields[fieldIndex].addArea(areaSquare);
			  
			  		break;
			  case ("Donation"):
				  	
				  	int donationAmount;
			  		donationAmount = Integer.parseInt(squareInfo[11]);
				  	DonationSquare donationSquare = new DonationSquare(squareInfo[1], donationAmount);
				  	
				  	squaresFromFile.add(donationSquare);
				  	
			  		break;
			  case ("Tax"):
				  	
				  	Integer taxAmount;
		  			taxAmount = Integer.parseInt(squareInfo[12]);
		  			TaxSquare taxSquare = new TaxSquare(squareInfo[1], taxAmount);
			  	
		  			squaresFromFile.add(taxSquare);
				  
			  		break;
			  case ("Chance"):
				  	
				  	ChanceSquare chanceSquare = new ChanceSquare();
			  
			  		chanceSquare.setName(squareInfo[1]);
			  		squaresFromFile.add(chanceSquare);
				  
			  		break;
			  default : System.out.println("Error reading files");
			  }
			  
			  line = bufferedReader.readLine();
			  
		  }
		  
		  bufferedReader.close(); fileReader.close();
		  
		  } catch (FileNotFoundException e) {
			  	e.printStackTrace();
		  } catch (IOException e) {
			  	e.printStackTrace();
		  }
		  
		  // build chance decks
		chancePool = new IEvent[2];
		chancePool[0] = new BuyAreaChance();
		chancePool[1] = new MoveRandomChance();
		
		squares = new Square[squaresFromFile.size()];
		
		for (int loop = 0; loop < squares.length; loop++) {
			squares[loop] = squaresFromFile.get(loop);
		}
		
		areas = new AreaSquare[areasFromFile.size()];
		
		for (int loop = 0; loop < areas.length; loop++) {
			areas[loop] = areasFromFile.get(loop);
		}
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
