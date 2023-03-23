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
	
//	/**
//	 * Return a map of areas categorized by field
//	 * @return a Hashtable of fields and their respective list of areas
//	 */
//	public Map<FieldEnum, List<AreaSquare>> getAreasByField() {
//		Map<FieldEnum, List<AreaSquare>> areasByField = new Hashtable<>();
//		
//		for (Square square : squares) {
//			// if square is an area, put it into field area map
//			if (square instanceof AreaSquare) {
//				AreaSquare area = (AreaSquare) square;
//				FieldEnum areaField = area.getField();
//				
//				// if field area map already contains area field, then append to the list of areas of the respective field.
//				// else create a new list of areas by the respective field and update the map.
//				if (areasByField.containsKey(areaField)) {
//					areasByField.get(areaField).add(area);
//				} else {
//					ArrayList<AreaSquare> areas = new ArrayList<>();
//					areas.add(area);
//					areasByField.put(areaField, areas);
//				}
//			}
//		}
//		
//		return areasByField;
//		
//	}
	
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
