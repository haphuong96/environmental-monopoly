/**
 * 
 */
package game;

/**
 * @author HA PHUONG
 *
 */
public class TaxSquare extends Square {
	private int taxAmount;

	/**
	 * 
	 */
	public TaxSquare(String name, int taxAmount) {
		this.taxAmount = taxAmount;
		this.setName(name);
	}
	
	

	public int getTaxAmount() {
		return taxAmount;
	}



	public void setTaxAmount(int taxAmount) {
		this.taxAmount = taxAmount;
	}



	@Override
	public void activate(Player player, Board board) {
		if (player.getBalance() < this.taxAmount) {
			player.offerToSell(taxAmount);
		}
		
		if (player.isAlive()) {
			player.payMoney(this.taxAmount, "Pay Tax");
		}
		
	}
	
	

}
