/**
 * 
 */
package game;

/**
 * @author HA PHUONG
 *
 */
public class Development {
	private Field field;
	private int level;
	private int cost;
	private int entranceFee;
	
	public Development(){
		
	}
	
	/**
	 * @param field
	 * @param level
	 * @param cost
	 * @param entranceFee
	 */
	public Development(Field field, int level, int cost, int entranceFee) {
		this.field = field;
		this.level = level;
		this.cost = cost;
		this.entranceFee = entranceFee;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getEntranceFee() {
		return entranceFee;
	}

	public void setEntranceFee(int entranceFee) {
		this.entranceFee = entranceFee;
	}
	
	
	
}
