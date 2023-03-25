/**
 * 
 */
package game;

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
	
	public List<AreaSquare> getAvailableAreas() {
		ArrayList<AreaSquare> availableAreas = new ArrayList<>();
		
		for (AreaSquare area : this.areas) {
			if (area.getOwner() == null) {
				availableAreas.add(area);
			}
		}
		
		return availableAreas;
	}
	
	public List<AreaSquare> getAreasIncharge(Player player){
		ArrayList<AreaSquare> areasIncharge = new ArrayList<>();
		
		for (AreaSquare area : areas) {
			areasIncharge.add(area);
		}
		
		return areasIncharge;
	}
	
	public void displayListAreaDetails() {
		for (AreaSquare area : areas) {
			area.displayAreaDetails();
		}
	}
	
}
