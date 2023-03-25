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

	private Player owner;
	private Field field;
	private int cost;
	private int numOfDevelopments;
	private int developmentCost;
	private boolean majorDevelopment;

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
	
	public void incrementDevelopments() {
		if (this.numOfDevelopments < 3) {
			this.numOfDevelopments++;
		} else if (this.numOfDevelopments == 3) {
			this.setMajorDevelopment(true);
		}
		
	}

	public int getDevelopmentCost() {
		return developmentCost;
	}

	public void setDevelopmentCost(int developmentCost) {
		this.developmentCost = developmentCost;
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

	public int getEntranceFee() {
		return entranceFee;
	}

	public void setEntranceFee(int entranceFee) {
		this.entranceFee = entranceFee;
	}

	public int getNextDevelopmentCost() {
		switch (this.numOfDevelopments) {
		case 0:
		case 1:
		case 2:
			return this.developmentCost;
		case 3:
			return this.majorDevelopmentCost;
		default:
			return 0;
		}
	}

	@Override
	public void activateEvent(Player player, Board board) {

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
		
		Scanner scanner = new Scanner(System.in);
		int areaCost = this.cost;
		int resourceBalance = currPlayer.getBalance();
		int playerOption;
		boolean validAnswer = false;

		while (validAnswer == false) {

			System.out.println("Do you want to buy this area?\nPress 1 for yes, press 2 for no.");
			playerOption = scanner.nextInt();

			if (playerOption == 1) {
				// buy the property
				if (resourceBalance > areaCost) {
					currPlayer.decreaseBalance(areaCost);
					System.out.println("Congratulations, you have bought this area!");
					validAnswer = true;
				} else {
					System.out.println("Not enough resources to buy area!");
					validAnswer = true;
				}

			} else if (playerOption == 2) {
				// don't buy the property
				System.out.println("Area not purchased.");
				validAnswer = true;

			} else {
				System.out.println("Invalid input, try again...");
			}
		}
		scanner.close();
	}

	/**
	 * Player landing on an area owned by another player must pay the owner an
	 * amount of resources specified by the area's rule of fee.
	 * 
	 * @param currPlayer
	 */
	public void payOwner(Player currPlayer) {

		int resourceBalance = currPlayer.getBalance();
		Player areaSquareOwner = this.owner;
		int totalCost = calcTotalEntranceFeeHelper();

		if (resourceBalance > totalCost) {
			// decreasing funds from the player
			currPlayer.decreaseBalance(totalCost);

			// crediting funds to square owner
			areaSquareOwner.increaseBalance(totalCost);

		} else {
			// call to sell development or square logic
			System.out.println("You don't have the resorces to pay this entrance fee!");
		}

	}

	/**
	 * Helper method to help calculate total entrance fee
	 */
	private int calcTotalEntranceFeeHelper(){
		
		int numOfDevelopments = this.getNumOfDevelopments();
		int totalEntranceFee = this.entranceFee;

		try {
			switch (numOfDevelopments) {
			case 1:
				totalEntranceFee += this.entranceFee1Development;
				break;
			case 2:
				totalEntranceFee += this.entranceFee2Development;
				break;
			case 3:
				totalEntranceFee += this.entranceFee3Development;
				break;
			case 4:
				totalEntranceFee += this.entranceFeeMajorDevelopment;
				break;
			default:
				System.out.println("No Developments.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR: Unable to calculate total entrance fee.");
		}
		return totalEntranceFee;
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
		System.out.println("Owner: "+ this.owner.getName());
		System.out.println("Field: "+ this.field.getName());
		System.out.println("Cost: "+this.getCost());
		System.out.println("Entrance fee: "+this.getEntranceFee());
	}

}
