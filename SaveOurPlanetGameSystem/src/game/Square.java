/**
 * 
 */
package game;

/**
 * @author zholm
 *
 */
public abstract class Square {

	private String name;
	
	/**
	 * 
	 */
	public Square() {

	}

	/**
	 * @param name
	 */
	public Square(String name) {
		this.name = name;
	}

	public abstract void activateEvent(Player player, Board board);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}