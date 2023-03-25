/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author
 *
 */
public class Player {

	private static final int PASS_START_BONUS = 200;

	private String name;
	private int position;
	private int balance;
	private boolean isAlive;

	/**
	 * Default constructor
	 */
	public Player() {

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
	 * The player moves forward on the board to a new position, given the number of
	 * moves.
	 * 
	 * @param numOfMoves
	 */
	public void move(int numOfMoves, Board board) {

		int newPostition;

		newPostition = this.position + numOfMoves;

		// adds 200 to the balance every time the player passes start
		while (newPostition >= board.getBoardLength()) {
			newPostition -= board.getBoardLength();
			this.balance += PASS_START_BONUS;
		}

		this.setPosition(newPostition);

	}

	public void moveBackward(int numOfMoves, Board board) {

		int newPosition = this.position - numOfMoves;

		while (newPosition < 0) {
			newPosition += board.getBoardLength();
		}

		this.setPosition(newPosition);
	}

	/**
	 * The player starts a development in a field they own.
	 * 
	 * @param board
	 */
	public void startDevelopment(Board board) {
		// get list of monopolies a player owns.
		ArrayList<Field> monopolyFields = board.getMonopolyFields(this);

		if (!monopolyFields.isEmpty()) {
			Scanner scanner = new Scanner(System.in);

			// display player's monopolies
			displayPlayerMonopoly(monopolyFields);

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
				if (fieldChoice < 1 || fieldChoice > monopolyFields.size()) {
					System.err.println("Invalid field id. Please try again.");
					continue;
				}

				// get the list of areas by user field choice.
				AreaSquare[] areas = monopolyFields.get(fieldChoice).getAreas();

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

				if (!areaSelected.isMajorDevelopment()) {
					if (areaSelected.getNextDevelopmentCost() <= this.balance) {
						// Player's qualified to do development, so process the development!
						processAreaDevelopment(areaSelected);
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
	 * The player to develop an area, once all conditions are met
	 * 
	 * @param area
	 */
	private void processAreaDevelopment(AreaSquare area) {
		int oldBalance = this.balance;
		int cost = area.getNextDevelopmentCost();

		// update area number of development;
		area.incrementDevelopments();
		// update player's balance
		this.decreaseBalance(cost);

		System.out.println("Successfully complete a development!");
		area.displayDevelopmentDetails();

		// display player's change in balance with reasons.
		this.displayChangeInBalance(oldBalance, "Cost of development for area " + area.getName());
	}

	/**
	 * Display player's monopolies for development consideration
	 * 
	 * @param monopolyFields
	 */
	private void displayPlayerMonopoly(ArrayList<Field> monopolyFields) {
		System.out.println("You are in charge of the following fields: ");

		for (int fieldId = 1; fieldId <= monopolyFields.size(); fieldId++) {
			Field field = monopolyFields.get(fieldId - 1);
			System.out.println("Field id: " + fieldId);
			System.out.println("Field name: " + field.getName());
			System.out.println();

			AreaSquare[] areas = field.getAreas();
			for (int areaId = 1; areaId <= areas.length; areaId++) {
				System.out.printf("Area id: " + areaId);
				areas[areaId - 1].displayDevelopmentDetails();
			}
		}
	}

	/**
	 * Player sell their property.
	 * 
	 * @param board
	 */
	public void sell(Board board, int obligationFee) {
		System.out.println(
				"Since you don't have enough resources to pay for obligation fee, you must sell your properties.");

		// use linked list to store incharge. List of incharge will be modified as user
		// sell off their area
		LinkedList<AreaSquare> incharge = new LinkedList<>(board.getAreasIncharge(this));

		if (!incharge.isEmpty()) {
			Scanner scanner = new Scanner(System.in);

			// display all areas player is in charge
			System.out.println("You're currently in charge of the following areas: ");
			for (int areaId = 1; areaId <= incharge.size(); areaId++) {
				incharge.get(areaId - 1).displayAreaDetails();
			}

			String playerDecision;
			do {
				System.out.println("Please enter your decision as instructions below.");
				System.out.println(
						"Enter 'e' - I want to reject this opportunity. If you choose this option, you will be out of the game.");
				System.out
						.println("Enter area id and the number of developments you want to sell, separated by a space. "
								+ "Please make sure that you shall sell all of your developments first before attempting to sell your areas."
								+ "A major development is also counted to the number of developments you want to sell. For i.e, you can enter '4' to sell all developments and the major development if the area is fully developed."
								+ "If major development is activated, the major development will be sold out first, then the rest will be counted towards the number of developments."
								+ "If you select an area without any development, you will automatically sell the area off without any consideration for the number of developments you enter.");

				playerDecision = scanner.nextLine();

				if (playerDecision.equalsIgnoreCase("e")) {
					System.out.println(
							"You have chosen to reject the opportunity to sell your resources to pay for your obligation. You are out of the game...");
					this.isAlive = false;
					break;
				}

				String[] playerDecisionSplit = playerDecision.split(" ");

				try {
					// process area id
					int areaId = Integer.parseInt(playerDecisionSplit[0]);

					// check area id
					if (areaId < 1 || areaId > incharge.size()) {
						System.out.println("Invalid area id. Please select an area id from the list above.");
						continue;
					}

					// get area
					AreaSquare areaToSell = incharge.get(areaId - 1);

					// get development status of area and its field
					int developments = areaToSell.getNumOfDevelopments();
					boolean majorDevelopment = areaToSell.isMajorDevelopment();
					Field fieldToSell = areaToSell.getField();
					boolean isFieldMonopoly = fieldToSell.isMonopoly(this);
					int totalNumOfDevelopmentsByField = fieldToSell.getTotalNumOfDevelopmentsInField();
					
					// check if player is a monopoly of the field to which areaToSell belongs
					// if player is not monopoly, or if player is monopoly and has no development in the field, sell area
					// else, sell the remaining developments
					if (!isFieldMonopoly || totalNumOfDevelopmentsByField == 0) {
						areaToSell.setOwner(null);
						incharge.remove(areaToSell);
						int oldBalance = this.balance;
						this.increaseBalance(areaToSell.getSellPrice());
						displayChangeInBalance(oldBalance, "Sell area " + areaToSell.getName());
					} else {
						int numOfDevToSell = Integer.parseInt(playerDecisionSplit[1]);
						if (numOfDevToSell < 1 || numOfDevToSell > 4) {
							System.err.println("Invalid number of development. Please try again.");
							continue;
						}

						// if major development is activated, sell major development first. Then count
						// the rest to the number of developments.
						int toSellTracker = numOfDevToSell;
						// sell major dev
						if (majorDevelopment) {
							areaToSell.setMajorDevelopment(false);
							int majorDevPrice = areaToSell.getField().getSellMajorDevelopmentPrice();
							this.balance += majorDevPrice;
							toSellTracker--;
						}

						// sell dev
						int resultNumOfDevs = areaToSell.getNumOfDevelopments() - toSellTracker;
						areaToSell.setNumOfDevelopments(resultNumOfDevs);
						int devPrice = areaToSell.getField().getSellDevelopmentPrice();
						this.balance += toSellTracker * devPrice;
					}
					
					// check if the new balance is enough to pay the obligation
					// + If no, continue to ask for player's decision to sell properties, if they have any properties left
					// + If yes, end the sell and deduct player's balance for the obligation
					if (this.balance >= obligationFee) {
						System.out.println("You have enough resource to pay for obligation. End of sell activity.");
						this.balance -= obligationFee;
						// display change in balance
						
						break;
					} else {
						System.out.println("You don't have enough resource to pay for obligation yet.");
						// check if player has any properties left. If no, the player is out of the game
						if (incharge.isEmpty()) {
							System.out.println("You have no property left to sell. You're out of the game.");
							// endgame stuffs
							this.isAlive = false;
							// clear any properties and make them available
							break;
						} else {
							System.out.println("You need to sell more properties. Please evaluate and continue with the selling.");
						}
					}

				} catch (NumberFormatException e) {
					System.err.println("Invalid area and number of development entries. Please enter valid numbers.");
				}
			} while (!playerDecision.equalsIgnoreCase("e"));
		} else {
			System.out.println("You don't have any resources to pay for your obligation. You are out of the game...");
			this.isAlive = false;
		}

	}

	/**
	 * Display change in player's balance everytime the balance changes
	 * 
	 * @param oldBalance
	 * @param reason
	 */
	public void displayChangeInBalance(int oldBalance, String reason) {
		// if old balance > current balance, it incurred cost and balance decreased.
		if (oldBalance > this.balance) {
			System.out.println("Your balance decreases by " + (oldBalance - this.balance));
			// else balance increased.
		} else {
			System.out.println("Your balance increases by " + (this.balance - oldBalance));
		}

		// display reason
		System.out.println("Reason: " + reason);

	}

	public void increaseBalance(int amount) {
		this.balance += amount;
	}

	public void decreaseBalance(int amount) {
		this.balance -= amount;
	}

}
