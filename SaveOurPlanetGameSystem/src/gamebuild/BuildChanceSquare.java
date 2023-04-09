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

import game.BlankSquare;
import game.BuyAreaChance;
import game.Chance;
import game.ChanceSquare;
import game.MoveRandomChance;
import game.Square;

/**
 * @author HA PHUONG
 *
 */
public class BuildChanceSquare extends BuildSquare {
	
	private static final String CHANCE_SQUARE_INPUT_FILE = "ChanceSquare.csv";
	
	/**
	 * @param squareBuildList
	 */
	public BuildChanceSquare(List<Square> squareBuildList) {
		super(squareBuildList);

	}

	@Override
	public void run() {
		File File = new File(CHANCE_SQUARE_INPUT_FILE);
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
				ChanceSquare newSq = new ChanceSquare(name, location);
				
				// build chance decks && add to new square
				Chance[] chancePool = new Chance[2];
				chancePool[0] = new BuyAreaChance();
				chancePool[1] = new MoveRandomChance();
				
				newSq.setChancePool(chancePool);
				
				//add to square list
				this.addSquareList(newSq);
				
				line = br.readLine();
			}
			
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
