/**
 * 
 */
package game;

/**
 * @author zholm
 *
 */
public class Area extends Square {

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
	public Area() {
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
	public Area(Player owner, int cost, int developments, int developmentCost, boolean majorDevelopment,
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
		
		if(owner == null) {
			this.buyArea(player);
		} else if(!player.equals(owner)){
			this.payOwner(player);
		}
		
	}
	
	public void buyArea(Player currentPlayer) {
		
	}
	
	public void payOwner(Player currentPlayer) {
		
	}

}
