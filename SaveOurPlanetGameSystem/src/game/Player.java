/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author
 *
 */
public class Player {

	private static final int PASS_START_BONUS = 200;
	private static final int START_BALANCE = 1500;
	
	private String name;
	private int position;
	private int balance;
	private boolean isAlive;
	private ArrayList<AreaSquare> areasOwned;
	private ArrayList<Field> monopolies;

	/**
	 * Default constructor
	 */
	public Player() {
		areasOwned = new ArrayList<>();
		monopolies = new ArrayList<>();
		isAlive = true;
		position = 0;
		balance = START_BALANCE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * The event in which a player moves forward to a new position. After the player
	 * moves to the new position, activate the event on the player's landing square.
	 * 
	 * @param numOfMoves
	 * @param board
	 */
	public void move(int numOfMoves, Board board) {

		int newPostition = this.position + numOfMoves;

		// Add a bonus every time the player passes [START] square
		int passStartEarn = 0;
		
		while (newPostition >= board.getBoardLength()) {
			newPostition -= board.getBoardLength();
			passStartEarn += PASS_START_BONUS;
		}
		
		if (passStartEarn > 0) {
			this.earnMoney(passStartEarn, "Pass START Square Earning.");
		}
		
		this.setPosition(newPostition);

		// activate square event
		Square landingSquare = board.getSquare(this.position);
		System.out.printf("You landed on %s!\n", landingSquare.getName());
		landingSquare.activate(this, board);
	}

	/**
	 * The event in which a player moves backward to a new position. After the
	 * player moves to the new position, activate the event on the player's landing
	 * square.
	 * 
	 * @param numOfMoves
	 * @param board
	 */
	public void moveBackward(int numOfMoves, Board board) {

		int newPosition = this.position - numOfMoves;

		while (newPosition < 0) {
			newPosition += board.getBoardLength();
		}

		this.setPosition(newPosition);

		// activate square event
		Square landingSquare = board.getSquare(this.position);
		System.out.printf("You landed on %s!\n", landingSquare.getName());
		landingSquare.activate(this, board);
	}

	/**
	 * The player buys the area.
	 * 
	 * @param player
	 */
	public void buyArea(AreaSquare area) {
		this.payMoney(area.getCost(), "Cost To Buy An Area");
		area.setOwner(this);
		this.areasOwned.add(area);

		Field field = area.getField();
		if (field.isMonopoly(this)) {
			monopolies.add(field);
		}
	}

	/**
	 * The player sells the area.
	 * 
	 * @param area
	 */
	public void sellArea(AreaSquare area) {
		area.setOwner(null);
		this.areasOwned.remove(area);
		this.earnMoney(area.getAreaSellPrice(), "Gains From Selling Area");

		monopolies.remove(area.getField());
	}

	/**
	 * An event in which the player is offered to start a development in their turn.
	 * Player can only start a development on an area within the field that they
	 * take charge. To start a development, the game system will display a list of
	 * fields and their respective areas owned by the player, and prompt for
	 * player's decision to take the opportunity or not. If the player chooses to
	 * start a development, they will enter their area choice. The game system will
	 * then check if it is a valid input from player, and if the player is qualified
	 * to do the development.
	 * 
	 * @param board
	 */
	public void offerToDevelopArea() {

		// If player owns any field, display all relevant areas and ask for player's
		// input
		if (!monopolies.isEmpty()) {
			Scanner scanner = new Scanner(System.in);

			// Display all monopoly fields
			System.out.println("You are in charge of the following fields: ");

			for (int fieldId = 1; fieldId <= monopolies.size(); fieldId++) {
				Field field = monopolies.get(fieldId - 1);
				System.out.println("Field id: " + fieldId);
				System.out.println("Field name: " + field.getName());
				System.out.println();

				AreaSquare[] areas = field.getAreas();
				for (int areaId = 1; areaId <= areas.length; areaId++) {
					System.out.printf("Area id: " + areaId);
					areas[areaId - 1].displayDevelopmentDetails();
				}
			}

			// Ask for player decision
			// Player enter their decision:
			// + If they enter "E", end the opportunity
			// + If they enter another entry, check if it's a valid choice for field and
			// area.
			// ++If it's an invalid choice, print error message
			// ++If it's a valid choice, check conditions for player's balance budget and
			// area development status.

			System.out.println(
					"You can choose to start a development or reject this opportunity. If you wish to develop an area, you shall select a development within your budget.");

			String playerChoice;
			do {
				System.out.println("Please enter your decision as instructions below.");
				System.out.println("1. Enter 'E' - I don't want to take this opportunity. End this opportunity!");
				System.out.println(
						"2. Enter field id and area id that you want to start a development on, separated by a space. For i.e: field Forest - id 1, area Amazon Forest - id 2, you shall enter '1 2'.");

				// player entry!
				playerChoice = scanner.nextLine();

				// if player enter 'E', end the opportunity
				if (playerChoice.equalsIgnoreCase("e")) {
					System.out.println("Start development opportunity ends.");
					break;
				}

				// else, analyze player choice
				String[] choiceSplit = playerChoice.split(" ");

				int fieldChoice, areaChoice; // player choice

				try {
					fieldChoice = Integer.parseInt(choiceSplit[0]);
					areaChoice = Integer.parseInt(choiceSplit[1]);
				} catch (NumberFormatException e) {
					System.err.println(
							"Invalid entries. You shall enter a valid number for field id and area id. Please try again.");
					continue;
				}

				// User shall select a field id and area id corresponding to id suggested by
				// system.
				if (fieldChoice < 1 || fieldChoice > monopolies.size()) {
					System.err.println("Invalid field id. Please try again.");
					continue;
				}

				// get the list of areas by user field choice.
				AreaSquare[] areas = monopolies.get(fieldChoice - 1).getAreas();

				if (areaChoice < 1 || areaChoice > areas.length) {
					System.err.println("Invalid area id. Please try again.");
					continue;
				}

				// A valid choice user makes
				AreaSquare areaSelected = areas[areaChoice];

				// Check user selected area, which must meet the following condition:
				// + Area development/major development cost is within player's balance
				// + Area haven't been fully developed yet (major development hasn't been
				// activated)
				// TODO: To be delegated to processAreaDevelopment()

				if (!areaSelected.isMajorDevelopment()) {
					if (areaSelected.getNextDevelopmentCost() <= this.getBalance()) {
						// Player's qualified to do development, so process the development!
						areaSelected.developArea(this);
						// End the loop
						break;
					} else {
						System.err.println(
								"You don't have enough resources to develop this area. Please try again with another area.");
						continue;
					}
				} else {
					System.err.println(
							"Area has been fully developed, you cannot develop it further. Please try again with another area.");
					continue;
				}

			} while (!playerChoice.equalsIgnoreCase("e"));

			scanner.close();
		}

	}

	/**
	 * Player will be offered to sell their properties once they are facing an
	 * obligation fee with not enough money on hand. Player will need to keep
	 * selling their properties until they have enough balance to pay off the
	 * obligation fee. The offer will stop when either: - The player is out of the
	 * game due to running out of resources or choose to give up, OR - The player
	 * has enough balance to fulfill the obligation.
	 * 
	 * Selling rules: When player chooses an area to sell, the player must attempt
	 * to sell off all developments within the field the area belongs to, before player
	 * can proceed to sell the area.
	 * 
	 * @param board
	 */
	public void offerToSell(int obligationFee) {
		System.out.println(
				"Since you don't have enough resources to pay for obligation fee, you must sell your properties.");

		boolean isAffordable = false;

		while (!isAffordable && this.isAlive) {
			if (!areasOwned.isEmpty()) {
				Scanner scanner = new Scanner(System.in);

				// display all areas player is in charge
				System.out.println("You're currently in charge of the following areas: ");
				for (int areaId = 1; areaId <= areasOwned.size(); areaId++) {
					areasOwned.get(areaId - 1).displayAreaDetails();
				}

				String playerDecision;
				do {
					System.out.println("Please enter your decision as instructions below.");
					System.out.println(
							"Enter 'E' - I want to reject this opportunity. If you choose this option, you will be out of the game.");
					System.out.println(
							"Enter area id and the number of developments you want to sell, separated by a space. "
									+ "Please make sure that you shall sell all of your developments first before attempting to sell your areas."
									+ "A major development is also counted to the number of developments you want to sell. For i.e, you can enter '4' to sell all 3 developments and the major development if the area is fully developed."
									+ "If major development is activated, the major development will be sold out first, then the rest will be counted towards the number of developments."
									+ "If you select an area without any development and have no developments within the field, you will automatically sell the area off without any consideration for the number of developments you enter.");

					playerDecision = scanner.nextLine();

					if (playerDecision.equalsIgnoreCase("e")) {
						System.out.println(
								"You have chosen to reject the opportunity to sell your resources to pay for your obligation. You are out of the game...");
						this.setAlive(false);
						break;
					}

					String[] playerDecisionSplit = playerDecision.split(" ");

					try {
						// process area id
						int areaId = Integer.parseInt(playerDecisionSplit[0]);

						// check area id
						if (areaId < 1 || areaId > areasOwned.size()) {
							System.out.println("Invalid area id. Please select an area id from the list above.");
							continue;
						}

						// get valid area
						AreaSquare areaToSell = areasOwned.get(areaId - 1);

						// get development status of area and its field
						int areaNumOfDevelopments = areaToSell.getNumOfDevelopments();
						Field fieldToSell = areaToSell.getField();
						int totalNumOfDevelopmentsByField = fieldToSell.getTotalNumOfDevelopmentsInField();

						// check if player is a monopoly of the field to which areaToSell belongs
						// if player is not monopoly, or if player is monopoly and has no development in
						// the field, sell area
						// else, sell the remaining developments
						if (totalNumOfDevelopmentsByField == 0) {
							this.sellArea(areaToSell);
						} else {
							int numOfDevToSell = Integer.parseInt(playerDecisionSplit[1]);

							if (numOfDevToSell < 1 || numOfDevToSell > areaNumOfDevelopments) {
								System.err.println("Invalid number of development. Please try again.");
								continue;
							}

							// sell development
							areaToSell.sellDevelopment(this, numOfDevToSell);
						}
					} catch (NumberFormatException e) {
						System.err
								.println("Invalid area and number of development entries. Please enter valid numbers.");
					}
				} while (!playerDecision.equalsIgnoreCase("e"));

				// check if the new balance is enough to pay the obligation
				// + If no, continue to ask for player's decision to sell properties, if they
				// have any properties left
				// + If yes, end the sell and deduct player's balance for the obligation
				if (this.getBalance() >= obligationFee) {
					System.out.println("You have enough resource to pay for obligation. End of sell activity.");
					isAffordable = true;

				} else {
					System.out.println(
							"You don't have enough resource to pay for obligation yet. You need to sell more properties.");
				}
				scanner.close();
			} else {
				System.out.println(
						"You don't have any resources left to pay for your obligation. You are out of the game...");
				this.setAlive(false);
				break;
			}
		}
	}

	/**
	 * Player earns money. Print message to console informing player's new balance
	 * and the reason for the change in balance.
	 * 
	 * @param amount
	 * @param reason
	 */
	public void earnMoney(int amount, String reason) {
		this.balance += amount;
		System.out.printf("Player %s's balance increases by %d. Reason: %s.\nYour new balance is %d\n", this.name, amount, reason,
				this.balance);
	}

	/**
	 * Player pays money. Print message to console informing player's new balance
	 * and the reason for the change in balance.
	 * 
	 * @param amount
	 * @param reason
	 */
	public void payMoney(int amount, String reason) {
		this.balance -= amount;
		System.out.printf("Player %s's balance decreases by %d. Reason: %s.\nYour new balance is %d\n", this.name, amount,
				reason, this.balance);
	}
	
	public void showDetails() {
		System.out.println("Player Name: "+ this.name);
		System.out.println("Player Balance: "+this.balance);
		System.out.println("Player Areas Owned: ");
		for (AreaSquare area : areasOwned) {
			area.displayAreaDetails();
		}
	}

}
