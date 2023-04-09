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
	
	/**
	 * 
	 */
	public DonationSquare(String name, int index) {
		super(name, index);
	}


	public int getDonationAmount() {
		return donationAmount;
	}



	public void setDonationAmount(int donationAmount) {
		this.donationAmount = donationAmount;
	}



	@Override
	public void activate(Player player, Board board) {
		super.activate(player, board);
		player.earnMoney(this.donationAmount, "Earn Donation From Government For Environmental Campaigns.");
	}
	
}
