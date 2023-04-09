/**
 * 
 */
package gamebuild;

import java.util.List;

import game.Square;

/**
 * @author HA PHUONG
 *
 */
public abstract class BuildSquare implements Runnable {
	private List<Square> squareList;
	
	public BuildSquare(List<Square> squareBuildList) {
		this.setSquareList(squareBuildList);
	}

	public List<Square> getSquareList() {
		return squareList;
	}

	public void setSquareList(List<Square> squareList) {
		this.squareList = squareList;
	}
	
	public void addSquareList(Square square) {
		this.squareList.add(square);
	}
	
}
