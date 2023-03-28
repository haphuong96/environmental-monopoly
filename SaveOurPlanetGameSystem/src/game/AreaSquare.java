/**
 * 
 */
package game;

import java.util.Scanner;

/**
 * @author
 *
 */
public class AreaSquare extends Square {
	/**
	 * This is the required number of developments to start a major development in
	 * the area.
	 */
	private static final int NUM_DEVELOPMENTS_TO_UNLOCK_MAJOR_DEVELOPMENT = 3;

	private Player owner;
	private Field field;
	private int numOfDevelopments;
	private boolean majorDevelopment;
	private int cost;
	private int developmentCost;
	private int majorDevelopmentCost;
	private int basicEntranceFee;
	private int[] entraceFeeWithDevelopment;
	private int entranceFeeWithMajorDevelopment;

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
		this.setCost(cost);
		this.setNumOfDevelopments(developments);
		this.setDevelopmentCost(developmentCost);
		this.setMajorDevelopment(majorDevelopment);
		this.setMajorDevelopmentCost(majorDevelopmentCost);
		this.setEntranceFee(entranceFee);
		this.entranceFee1Development = entranceFee1Development;
		this.entranceFee2Development = entranceFee2Development;
		this.entranceFee3Development = entranceFee3Development;
		this.entranceFeeMajorDevelopment = entranceFeeMajorDevelopment;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getNumOfDevelopments() {
		return numOfDevelopments;
	}

	public void setNumOfDevelopments(int developments) {
		this.numOfDevelopments = developments;
	}

	/**
	 * Update development status when player starts a development. Once a major
	 * development has been established, the area is considered to be fully
	 * developed and player cannot make any further developments in the area. A
	 * major development is also considered a development with future consideration
	 * to add extra benefits.
	 */
	public void addDevelopment() {
		if (!isMajorDevelopment()) {
			if (this.numOfDevelopments == NUM_DEVELOPMENTS_TO_UNLOCK_MAJOR_DEVELOPMENT) {
				this.setMajorDevelopment(true);
			}

			this.numOfDevelopments++;
		}
	}

	public int getDevelopmentCost() {
		return developmentCost;
	}

	public void setDevelopmentCost(int developmentCost) {
		this.developmentCost = developmentCost;
	}

	/**
	 * Return the cost to start the next development, which could be a major
	 * development.
	 * 
	 * @return
	 */
	public int getNextDevelopmentCost() {
		if (this.numOfDevelopments < NUM_DEVELOPMENTS_TO_UNLOCK_MAJOR_DEVELOPMENT) {
			return this.developmentCost;
		} else if (this.numOfDevelopments == NUM_DEVELOPMENTS_TO_UNLOCK_MAJOR_DEVELOPMENT) {
			return this.majorDevelopmentCost;
		} else {
			return 0;
		}
	}

	public boolean isMajorDevelopment() {
		return majorDevelopment;
	}

	public void setMajorDevelopment(boolean majorDevelopment) {
		this.majorDevelopment = majorDevelopment;
	}

	public int getMajorDevelopmentCost() {
		return majorDevelopmentCost;
	}

	public void setMajorDevelopmentCost(int majorDevelopmentCost) {
		this.majorDevelopmentCost = majorDevelopmentCost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getBasicEntranceFee() {
		return basicEntranceFee;
	}

	public void setBasicEntranceFee(int BasicEntranceFee) {
		this.basicEntranceFee = BasicEntranceFee;
	}

	public int[] getEntraceFeeWithDevelopment() {
		return entraceFeeWithDevelopment;
	}

	public void setEntraceFeeWithDevelopment(int[] entraceFeeWithDevelopment) {
		this.entraceFeeWithDevelopment = entraceFeeWithDevelopment;
	}

	public int getEntranceFeeWithMajorDevelopment() {
		return entranceFeeWithMajorDevelopment;
	}

	public void setEntranceFeeWithMajorDevelopment(int entranceFeeWithMajorDevelopment) {
		this.entranceFeeWithMajorDevelopment = entranceFeeWithMajorDevelopment;
	}

	public int getAreaSellPrice() {
		return this.cost / 2;
	}

	public int getDevelopmentSellPrice() {
		return this.developmentCost / 2;
	}

	public int getMajorDevelopmentSellPrice() {
		return this.majorDevelopmentCost / 2;
	}

	public void sellDevelopment(Player player, int numToSell) {
		if (this.isMajorDevelopment()) {
			this.majorDevelopment = false;
		}
		
		int totalEarning = this.getMajorDevelopmentSellPrice() + this.getDevelopmentSellPrice() * (numToSell - 1);
		this.numOfDevelopments -= numToSell;
		
		player.earnMoney(totalEarning, "Gains From Selling Area Developments");
	}

	/**
	 * Activate Area event when a player lands on an Area. If the area is available,
	 * the player will have the opportunity to buy the area. Else if the area
	 * already has a owner, the player must pay the owner some of his resources.
	 */
	@Override
	public void activate(Player player, Board board) {

		if (owner == null) {
			this.offerToBuy(player);
		} else if (!player.equals(owner)) {
			this.payOwner(player);
		} else {
			System.out.println("You own the area :D. End move!");
		}
	}

	/**
	 * Player landing on an available area will have the chance to take charge of a
	 * particular area in exchange for some resources.
	 * 
	 * @param area The area to buy
	 */
	public void offerToBuy(Player player) {
		System.out.println("This area is available for purchase.");

		Scanner scanner = new Scanner(System.in);

		String playerOption;
		if (player.getBalance() >= this.cost) {
			do {
				System.out.println("Do you want to buy this area?\n" + "Press '1' for yes, press '2' for no.");

				playerOption = scanner.nextLine();

				if (playerOption.equalsIgnoreCase("1")) {
					player.buyArea(this);
				} else if (playerOption.equalsIgnoreCase("2")) {
					System.out.println("Offer to buy area ends.");
				} else {
					System.out.println("Invalid entry. Please try again and enter an entry as instructions.");
				}

			} while (!playerOption.equalsIgnoreCase("2"));
		} else {
			System.out.println("You don't have enough resource to buy this area. Offer to buy area ends.");
		}

		scanner.close();
	}
	
	

	/**
	 * Player landing on an area owned by another player must pay the owner an
	 * amount of resources specified by the area's rule of fee.
	 * 
	 * @param currPlayer
	 */
	public void payOwner(Player player) {
		System.out.printf("This area is owned by %s. You must pay an entrance fee to the owner for visiting their area.\n", this.owner);
		
		int fee = retrieveEntranceFee();
		
		if (player.getBalance() < fee) {
			player.offerToSell(fee);
		}
		
		if (player.isAlive()) {
			player.payMoney(fee, "Pay Entrance Fee To Owner.");
			this.owner.earnMoney(fee, "Collect Entrance Fee From Visitor");
		}
	
	}
	
	/**
	 * The owner develops the area.
	 * 
	 * @param area
	 */
	public void developArea(Player player) {

		int cost = this.getNextDevelopmentCost();
		
		// update area number of development;
		this.addDevelopment();
		// update player's balance
		player.payMoney(cost, "Cost To Develop An Area.");
		System.out.println("Successfully complete a development!");
		this.displayDevelopmentDetails();

	}

	/**
	 * Method to retrieve the entrance fee which is currently applied to the area.
	 * 
	 * @return an int for current entrance fee
	 */
	private int retrieveEntranceFee() {
		if (isMajorDevelopment()) {
			return this.entranceFeeWithMajorDevelopment;
		} else if (this.numOfDevelopments > 0) {
			return this.entraceFeeWithDevelopment[numOfDevelopments - 1];
		} else if (this.field.isMonopoly(owner)) {
			return this.basicEntranceFee * 2;
		} else if (this.owner != null){
			return this.basicEntranceFee;
		} else {
			return 0;
		}
	}

	public void displayDevelopmentDetails() {
		System.out.println(this.getName());
		System.out.println("Field: " + this.field.getName());
		System.out.println("Number of Developments: " + this.getNumOfDevelopments());
		System.out.println("Development Cost: " + this.getDevelopmentCost());
		System.out.println("Major Development Activated: " + this.getMajorDevelopmentCost());
		System.out.println("Major Development Cost: " + this.getMajorDevelopmentCost());
	}

	public void displayAreaDetails() {
		System.out.println(this.getName());
		System.out.println("Owner: " + this.owner.getName());
		System.out.println("Field: " + this.field.getName());
		System.out.println("Cost: " + this.getCost());
		System.out.println("Entrance fee: " + this.getBasicEntranceFee());
	}

}
