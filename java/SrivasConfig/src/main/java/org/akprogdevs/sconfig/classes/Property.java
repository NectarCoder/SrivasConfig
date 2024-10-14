package org.akprogdevs.sconfig.classes;

import java.util.ArrayList;

/**
 * The Property class, contains a matching pair of property name/ID and its corresponding
 * value, and an optional list of valid values. Included are methods to get a Property's ID and value,
 * a method to set valid values, and a toString to format
 * the output property in the configuration file. 
 * 
 * @author AK Program Developers
 *
 */
public final class Property {

	private String propertyID;
	private String value;
	private ArrayList<String> validValues = new ArrayList<>(0);
	
	/**
	 * Constructor that accepts an ID and its corresponding value. Spaces, if any, in the
	 * id parameter will be eliminated.
	 * @param id The property ID
	 * @param value The property value
	 */
	public Property(String id, String value) {
	
		this.setID(id);;
		this.setValue(value);;
		
	}
	
	/**
	 * Constructor that accepts an ID, its corresponding value, and a list of
	 * valid property values. Spaces, if any, in the id parameter will be eliminated.
	 * @param id The property ID
	 * @param value The property value
	 * @param validValues The string array of valid values
	 */
	public Property(String id, String value, String[] validValues) {
	
		this.setID(id);;
		this.setValue(value);;
		setValidValues(validValues);
	}
	
	/**
	 * Retrieves the ID name of this property.
	 * 
	 * @return The property ID
	 */
	public String getID() {
		return this.propertyID;
	}
	
	/**
	 * Sets this Property's ID to the name specified during call. Spaces, 
	 * if any, in the id parameter will be eliminated.
	 * @param id The property ID to be set
	 */
	public void setID(String id) {
		
		this.propertyID = id.replaceAll(" ", "");
	}
	
	/**
	 * Retrieves the value of this property.
	 * 
	 * @return The property value
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Sets this Property's value to the value specified during call.
	 * @param value The property value to be set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Returns a string array of the valid values of this Property.
	 * @return The string array of valid values
	 */
	public String[] getValidValues() {
		
		String[] returnArray = new String[this.validValues.size()];
		
		for(int i = 0; i < this.validValues.size(); i++) {
			returnArray[i] = this.validValues.get(i);
			//System.out.println(this.validValues.get(i));
		}
		
		return returnArray;
	}
	
	/**
	 * Sets all the valid values for this Property by accepting a string array of
	 * values from the caller.
	 * 
	 * @param validValues String array of valid values for this property value
	 */
	public void setValidValues(String[] validValues) {
		clearValidValues();
		
		for(int i = 0; i < validValues.length; i++) {
			this.validValues.add(validValues[i]);
		}		
		
	}
	
	/**
	 * Adds a specific string value to the list of valid values for this Property's
	 * value.
	 * 
	 * @param validValue The value to be added to the list of valid values
	 * @return True if successfully added element, or false if a duplicate valid value
	 * 			was found
	 */
	public boolean addValidValue(String validValue) {
		
		for(int i = 0; i < validValues.size(); i++) {
			if(validValues.get(i).equals(validValue)) {
				return false;
			}
		}
		
		validValues.add(validValue);
		return true;
		
	}
	
	/**
	 * Removes the specified string value from the list of valid values for this 
	 * Property's value.
	 *  
	 * @param validValue Value to be removed
	 * @return True if removal was successful, or false if the specified element is missing
	 */
	public boolean removeValidValue(String validValue) {
		
		for(int i = 0; i < validValues.size(); i++) {
			if(validValues.get(i).equals(validValue)) {
				this.validValues.remove(i);
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Clears all the settings in the valid values list.
	 * 
	 */
	public void clearValidValues() {
		this.validValues = new ArrayList<>(0);
	}
	
	/**
	 * 
	 * Converts this Property into a readable listing for the configuration file.
	 * 
	 * @return Export property string, along with valid values, if applicable.
	 */
	@Override
	public String toString() {
		
		if(validValues == null || validValues.size() <= 0) {
			return "<"+ this.propertyID + ":" + this.value +">";
		}
		else {
			
			String returnValue = "<"+ this.propertyID + ":" + this.value + "[";
			
			for(int i = 0; i < validValues.size(); i++) {
				
				if(i < validValues.size()- 1) {
					returnValue += validValues.get(i) + ",";
				}
				else if(i == validValues.size() - 1) {
					returnValue += validValues.get(i) + "]>";
				}
				
			}
			
			return returnValue;
		}
		
	}
	
	
}
