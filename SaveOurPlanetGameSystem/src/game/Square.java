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
	
	public abstract void activateEvent(Player player);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}