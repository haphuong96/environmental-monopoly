package gamebuild;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import game.DonationSquare;
import game.Square;
import game.TaxSquare;

public class BuildTaxSquare extends BuildSquare {
	
	private static final String TAX_SQUARE_INPUT_FILE = "TaxSquare.csv";
	
	public BuildTaxSquare(List<Square> squareBuildList) {
		super(squareBuildList);
	}

	@Override
	public void run() {
		File File = new File(TAX_SQUARE_INPUT_FILE);
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
				int taxAmount = Integer.parseInt(lineSplit[2]);
				
				TaxSquare newSq = new TaxSquare(name, location);
				newSq.setTaxAmount(taxAmount);
				
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
