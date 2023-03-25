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
	private Field[] fields;
	
	/**
	 * Constructor to build the list of squares and fields.
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
		
		
	}
	
	public int getBoardLength() {
		return squares.length;
	}
	
	public Square getSquare(int index) {
		return squares[index];
	}
	
	/**
	 * Find all fields that the player is in charge of.
	 * @param player
	 * @return
	 */
	public ArrayList<Field> getMonopolyFields(Player player) {
		ArrayList<Field> monopolies = new ArrayList<>();
		
		for (Field field : fields) {
			if (field.isMonopoly(player)) {
				monopolies.add(field);
			}
		}
		
		return monopolies;
	}
	
}
