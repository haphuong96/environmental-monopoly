/**
 * 
 */
package game;

/**
 * @author
 *
 */
public class AreaSquare extends Square {

	private Player owner;
	private int cost;
	private int developments;
	private int developmentCost;
	private boolean majorDevelopment;
	private int majorDevelopmentCost;
	private int entranceFee;
	private int entranceFee1Development;
	private int entranceFee2Development;
	private int entranceFee3Development;
	private int entranceFeeMajorDevelopment;

	/**
	 * 
	 */
	public AreaSquare() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param owner
	 * @param cost
	 * @param developments
	 * @param developmentCost
	 * @param majorDevelopment
	 * @param majorDevelopmentCost
	 * @param entranceFee
	 * @param entranceFee1Development
	 * @param entranceFee2Development
	 * @param entranceFee3Development
	 * @param entranceFeeMajorDevelopment
	 */
	public AreaSquare(Player owner, int cost, int developments, int developmentCost, boolean majorDevelopment,
			int majorDevelopmentCost, int entranceFee, int entranceFee1Development, int entranceFee2Development,
			int entranceFee3Development, int entranceFeeMajorDevelopment) {
		this.owner = owner;
		this.cost = cost;
		this.developments = developments;
		this.developmentCost = developmentCost;
		this.majorDevelopment = majorDevelopment;
		this.majorDevelopmentCost = majorDevelopmentCost;
		this.entranceFee = entranceFee;
		this.entranceFee1Development = entranceFee1Development;
		this.entranceFee2Development = entranceFee2Development;
		this.entranceFee3Development = entranceFee3Development;
		this.entranceFeeMajorDevelopment = entranceFeeMajorDevelopment;
	}

	@Override
	public void activateEvent(Player player) {

		if (owner == null) {
			this.buyArea(player);
		} else if (!player.equals(owner)) {
			this.payOwner(player);
		}

	}

	/**
	 * Player landing on an available area will have the chance to take charge of a
	 * particular area in exchange for some resources.
	 * 
	 * @param currentPlayer
	 */
	public void buyArea(Player currPlayer) {

	}

	/**
	 * Player landing on an area owned by another player must pay the owner an
	 * amount of resources specified by the area's rule of fee.
	 * 
	 * @param currPlayer
	 */
	public void payOwner(Player currPlayer) {

	}

}
