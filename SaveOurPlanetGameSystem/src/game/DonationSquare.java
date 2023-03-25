/**
 * 
 */
package game;

/**
 * @author HA PHUONG
 *
 */
public class DonationSquare extends Square {
	private int donationAmount;
	
	public DonationSquare(int donationAmount) {
		this.donationAmount = donationAmount;
	}


	public int getDonationAmount() {
		return donationAmount;
	}



	public void setDonationAmount(int donationAmount) {
		this.donationAmount = donationAmount;
	}



	@Override
	public void activateEvent(Player player, Board board) {
		int oldBalance = player.getBalance();
		
		player.increaseBalance(this.donationAmount);
		
		player.displayChangeInBalance(oldBalance, "You have been donated : "+this.donationAmount);

	}
	
}
