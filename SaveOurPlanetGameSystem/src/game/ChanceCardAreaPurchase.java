/**
 * 
 */
package game;

import java.util.List;
import java.util.Scanner;

/**
 * @author HA PHUONG
 *
 */
public class ChanceCardAreaPurchase extends ChanceCard {

	@Override
	public void activateChance(Player player, Board board) {
		System.out.printf("You draw %s. You will have the opportunity to purchase any available area on the board.\n",
				this.getName());
		
		// check for available areas
		List<AreaSquare> availableAreas =  board.getAvailableAreas();
		
		if (!availableAreas.isEmpty()) {
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Please look at the following list of areas:");

			board.displayListAreaDetails();
			
			System.out.println();
			
			System.out.println("Available areas are:");
			
			for (int areaId = 1; areaId <= availableAreas.size(); areaId++) {
				System.out.printf("id: %d, area: %s\n", areaId, availableAreas.get(areaId - 1).getName());
			}
			
			String playerDecision;
			do {
				System.out.println("Please enter your decision as instructions below:");
				System.out.println("1. Enter 'E' - I don't want to take this opportunity.");
				System.out.println("2. Enter a valid area id that you want to purchase from the list of available areas.");
				
				playerDecision = scanner.nextLine();
				
				// select option 1
				if (playerDecision.equalsIgnoreCase("e")) {
					System.out.println("Opportunity ends.");
					break;
				}
				
				// select option 2
				// Analyze player input
				try {
					int playerDecisionInt = Integer.parseInt(playerDecision);
					
					if (playerDecisionInt < 1 || playerDecisionInt > availableAreas.size()) {
						System.err.println("Invalid id. Please select a valid area id from the list of available areas.");
						continue;
					}
					
					// process buy area
					AreaSquare areaToPurchase = availableAreas.get(playerDecisionInt - 1);
					if (player.getBalance() < areaToPurchase.getCost()) {
						System.err.println("You don't have enough resources to purchase this area. Please select another area.");
						continue;
					}
					
					areaToPurchase.buyArea(player);
					
					
				} catch (NumberFormatException e) {
					System.err.println("Invalid entries. Please enter a valid number.");
					continue;
				}
				
			} while (!playerDecision.equalsIgnoreCase("e"));
		} else {
			System.out.println("There's no available area.");
		}
	}

}
