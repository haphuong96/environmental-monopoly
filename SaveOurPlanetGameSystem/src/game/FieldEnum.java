/**
 * 
 */
package game;

/**
 * @author HA PHUONG
 *
 */
public enum FieldEnum {
	HYDROELECTRIC_ENERGY("Hydroelectric Energy"), FOREST("Forest"), WIND_ENERGY("Wind Energy"), SOLAR_ENERGY("Solar Energy");
	
	private String fieldName;
	
	FieldEnum(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

}
