/**
 * 
 */
package gamebuild;

import java.util.Comparator;

import game.Square;

/**
 * @author HA PHUONG
 *
 */
public class CompareSquareByLocation implements Comparator<Square> {

	@Override
	public int compare(Square s1, Square s2) {
		return s1.getIndex() - s2.getIndex();
	}

}
