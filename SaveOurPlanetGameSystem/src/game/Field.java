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
public class Field {
	private String name;
	private AreaSquare[] areas;
	private int areaCost;
	private int entranceFee;
	
	/**
	 * Default constructor
	 */
	public Field() {
		
	}
	
	/**
	 * 
	 * @param name
	 */
	public Field(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AreaSquare[] getAreas() {
		return areas;
	}

	public void setAreas(AreaSquare[] areas) {
		this.areas = areas;
	}
	
	public int getAreaCost() {
		return areaCost;
	}

	public void setAreaCost(int areaCost) {
		this.areaCost = areaCost;
	}

	public int getEntranceFee() {
		return entranceFee;
	}

	public void setEntranceFee(int entranceFee) {
		this.entranceFee = entranceFee;
	}

	/**
	 * Check if a player is in charge of this particular field
	 * @param player
	 * @return
	 */
	public boolean isMonopoly(Player player) {
		boolean isMonopoly = true;
		
		for (AreaSquare area : areas) {
			if (!area.getOwner().equals(player)) {
				isMonopoly = false;
			}
		}
		
		return isMonopoly;
	}
	
	public int getTotalNumOfDevelopmentsInField() {
		int total = 0;
		
		for (AreaSquare area : areas) {
			total += area.getNumOfDevelopments();
		}
		
		return total;
	}
}
