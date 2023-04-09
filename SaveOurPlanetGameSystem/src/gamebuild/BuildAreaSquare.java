/**
 * 
 */
package gamebuild;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.AreaSquare;
import game.Field;
import game.Square;

/**
 * @author HA PHUONG
 *
 */
public class BuildAreaSquare extends BuildSquare {
	
	private static final String AREA_SQUARE_INPUT_FILE = "AreaSquare.csv";
	private static final String FIELD_INPUT_FILE = "Fields.csv";
	
	private List<AreaSquare> areaSquareList;
	
	/**
	 * @param squareBuildList
	 */
	public BuildAreaSquare(List<Square> squareBuildList, List<AreaSquare> areaSquareList) {
		super(squareBuildList);
		this.areaSquareList = areaSquareList;
	}

	@Override
	public void run() {
		// build Field and store in a map
		Map<Integer, Field> fields = new HashMap<>();
		
		File fieldFile = new File(FIELD_INPUT_FILE);
		try {
			FileReader fieldFr = new FileReader(fieldFile);
			BufferedReader fieldBr = new BufferedReader(fieldFr);
			
			// skip headers
			fieldBr.readLine();
			
			// read
			String line = fieldBr.readLine();
			
			while (line != null) {
				String[] lineSplit = line.split(",");
				//create new field and store in map
				String fieldName = lineSplit[1];
				int fieldId = Integer.parseInt(lineSplit[0]);
				
				Field field = new Field(fieldName);
				fields.put(fieldId, field);
				
				line = fieldBr.readLine();
			}
			
			fieldBr.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// build list of areaSquare and records areas to Field
		Map<Field, List<AreaSquare>> fieldAreasMap = new HashMap<>();
		
		File areaFile = new File(AREA_SQUARE_INPUT_FILE);
		
		FileReader areaFr;
		try {
			areaFr = new FileReader(areaFile);
			BufferedReader areaBr = new BufferedReader(areaFr);
			
			areaBr.readLine();
			
			//read areas detail
			String line = areaBr.readLine();
			
			while(line != null) {
				String[] lineSplit = line.split(",");
				AreaSquare newArea = new AreaSquare(lineSplit[1], Integer.parseInt(lineSplit[0]));
				
				newArea.setField(fields.get(Integer.parseInt(lineSplit[2])));
				newArea.setCost(Integer.parseInt(lineSplit[3]));
				newArea.setDevelopmentCost(Integer.parseInt(lineSplit[4]));
				newArea.setMajorDevelopmentCost(Integer.parseInt(lineSplit[5]));
				newArea.setBasicEntranceFee(Integer.parseInt(lineSplit[6]));
				
				// entrance fee with development:
				int[] devCollectFees = {
						Integer.parseInt(lineSplit[7]),
						Integer.parseInt(lineSplit[8]),
						Integer.parseInt(lineSplit[9])
						};
				
				newArea.setEntraceFeeWithDevelopment(devCollectFees);
				newArea.setEntranceFeeWithMajorDevelopment(Integer.parseInt(lineSplit[10]));
				
				// Add to areas list for each Field
				// Add the area to the list of squares of Field.
				Field newAreaField = newArea.getField();
				
				if (fieldAreasMap.containsKey(newAreaField)) {
					fieldAreasMap.get(newAreaField).add(newArea);
				} else {
					ArrayList<AreaSquare> areasInField = new ArrayList<>();
					areasInField.add(newArea);
					fieldAreasMap.put(newAreaField, areasInField);
				}
				
				// Add to square list
				this.addSquareList(newArea);
				
				// Add to area square list
				areaSquareList.add(newArea);
				
				line = areaBr.readLine();
			}
			
			// set areas for each Field
			for (Map.Entry<Field, List<AreaSquare>> entry : fieldAreasMap.entrySet()) {
				AreaSquare[] areas = new AreaSquare[entry.getValue().size()];
				entry.getKey().setAreas(entry.getValue().toArray(areas));
			}
			
			areaBr.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
