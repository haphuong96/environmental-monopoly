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
	public TaxSquare(int taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	

	public int getTaxAmount() {
		return taxAmount;
	}



	public void setTaxAmount(int taxAmount) {
		this.taxAmount = taxAmount;
	}



	@Override
	public void activate(Player player, Board board) {
		player.payMoney(this.taxAmount, "Pay Tax");
	}
	
	

}
