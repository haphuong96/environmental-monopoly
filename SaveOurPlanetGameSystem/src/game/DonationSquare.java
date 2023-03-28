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
	public void activate(Player player, Board board) {
		player.earnMoney(this.donationAmount, "Earn Donation From Government For Environmental Campaigns.");
	}
	
}
