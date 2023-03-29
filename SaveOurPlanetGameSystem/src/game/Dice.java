/**
 * 
 */
package game;

import java.util.Random;

/**
 * @author HA PHUONG
 *
 */
public class Dice {
	private static final int MIN_FACE_VALUE = 1;
	private static final int MAX_FACE_VALUE = 6;
	
	private int faceValue;
	
	/**
	 * Default constructor
	 */
	public Dice() {
		
	}

	/**
	 * @return the faceValue
	 */
	public int getFaceValue() {
		return faceValue;
	}
	
	/**
	 * Roll the dice. Each dice roll will return a random face value ranging from 1 to 6.
	 */
	public void roll() {
		Random random = new Random();
		
		this.faceValue =  random.nextInt(MAX_FACE_VALUE) + MIN_FACE_VALUE;
	}

}
