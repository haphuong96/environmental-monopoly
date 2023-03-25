/**
 * 
 */
package game;

/**
 * @author HA PHUONG
 *
 */
public abstract class ChanceCard {
	private String name;
	
	public abstract void activateChance(Player player, Board board);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
