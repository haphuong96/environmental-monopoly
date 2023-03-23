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
	public void activateEvent(Player player) {
		int oldBalance = player.getBalance();
		
		player.decreaseBalance(this.taxAmount);
		
		player.displayChangeInBalance(oldBalance, "You have been taxed : "+this.taxAmount);
		
	}
	
	

}
