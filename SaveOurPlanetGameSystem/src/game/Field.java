/**
 * 
 */
package game;

/**
 * @author HA PHUONG
 *
 */
public class Field {
	private String name;
	private AreaSquare[] areas;

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

	/**
	 * Check if a player is in charge of this particular field. Player must be the
	 * owner of all areas with the field to be considered the owner of the whole
	 * field.
	 * 
	 * @param player
	 * @return boolean value, true if player is the monopoly of the field, false otherwise.
	 */
	public boolean isMonopoly(Player player) {
		boolean isMonopoly = true;

		for (AreaSquare area : areas) {
			if (area.getOwner() == null || !area.getOwner().equals(player) ) {
				isMonopoly = false;
				break;
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
