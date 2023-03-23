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
	 * The player moves on the board to a new position, given the number of moves.
	 * 
	 * @param numOfMoves
	 */
	public void move(int numOfMoves, Board board) {
		
		int newPostition;
		
		newPostition = this.position + numOfMoves;
		
		//adds 200 to the balance every time the player passes start
		while (newPostition >= board.getBoardLength()) {
			newPostition -= board.getBoardLength();
			this.balance += PASS_START_BONUS;
		}
		
		this.setPosition(newPostition);
		
	}
	

	/**
	 * The player starts a development in a field they own.
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
			// 		If it's an invalid choice, print error message
			// 		If it's a valid choice, check conditions for player's balance budget and area development status.
			
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
				
				//if player enter 'E', end the opportunity
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
				
				// User shall select a field id and area id corresponding to id suggested by system.
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
						playerChoice = "e";
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
	 * @param monopolyFields
	 */
	private void displayPlayerMonopoly(ArrayList<Field> monopolyFields) {
		System.out.println("You are in charge of the following fields: ");

		for (int fieldId = 1; fieldId <= monopolyFields.size(); fieldId++) {
			Field field = monopolyFields.get(fieldId);
			System.out.println("Field id: " + fieldId);
			System.out.println("Field name: " + field.getName());
			System.out.println();

			AreaSquare[] areas = field.getAreas();
			for (int areaId = 1; areaId <= areas.length; areaId++) {
				System.out.printf("Area id: " + areaId);
				areas[areaId].displayDevelopmentDetails();
			}
		}
	}
	
	/**
	 * Display change in player's balance everytime the balance changes
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
