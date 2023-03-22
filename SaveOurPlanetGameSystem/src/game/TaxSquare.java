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
		player.decreaseBalance(this.taxAmount);
		System.out.println("You have been taxed : "+this.taxAmount);
		
	}
	
	

}
