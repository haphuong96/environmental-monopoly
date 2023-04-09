/**
 * 
 */
package gamebuild;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import game.DonationSquare;
import game.Square;

/**
 * @author HA PHUONG
 *
 */
public class BuildDonationSquare extends BuildSquare {
	
	private static final String DONATION_SQUARE_INPUT_FILE = "DonationSquare.csv";
	/**
	 * @param squareBuildList
	 */
	public BuildDonationSquare(List<Square> squareBuildList) {
		super(squareBuildList);
	}

	@Override
	public void run() {
		File File = new File(DONATION_SQUARE_INPUT_FILE);
		try {
			FileReader fr = new FileReader(File);
			BufferedReader br = new BufferedReader(fr);
			
			// skip headers
			br.readLine();
			
			// read
			String line = br.readLine();
			
			while (line != null) {
				String[] lineSplit = line.split(",");
				//create new square
				int location = Integer.parseInt(lineSplit[0]);
				String name = lineSplit[1];
				int donationAmount = Integer.parseInt(lineSplit[2]);
				
				DonationSquare newSq = new DonationSquare(name, location);
				newSq.setDonationAmount(donationAmount);
				
				//add to square list
				this.addSquareList(newSq);
				
				line = br.readLine();
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
