package org.akprogdevs.sconfig.classes;

import java.util.ArrayList;

abstract class PropertyContainer {

	//List of properties ArrayList
	protected ArrayList<Property> properties;
	private final static int DEFAULT_CAPACITY = 0;

	PropertyContainer() {
		createProperties(DEFAULT_CAPACITY);
	}
	
	PropertyContainer(int propertyCapacity) {
		createProperties(propertyCapacity);
	}
	
	/**
	 * Creates a new property and adds it to the list of properties. 
	 * 
	 * @param id The property's name, i.e. the ID
	 * @param value The property's value, i.e. the setting
	 * 
	 * @return True if the property was added to the set successfully, 
	 * or false if a duplicate ID was found on the list
	 */
	public boolean addProperty(String id, String value) {
		
		//Check to find any duplicate property IDs
		for(int i = 0; i < properties.size(); i++) {
			if(properties.get(i).getID().equals(id)) {
				return false;
			}
		}
		
		//Add the property, provided it passes above no-duplicate property test
		properties.add((properties.size()), (new Property(id, value)));
		return true;
	}
	
	/**
	 * Adds an existing Property object to the list of properties.
	 * 
	 * @param property Property object to be added
	 * @return True if the property was added to the set successfully, 
	 * or false if a duplicate ID was found on the list
	 */
	public boolean addExistingProperty(Property property) {
		
		//Check to find any duplicate property IDs
		for(int i = 0; i < properties.size(); i++) {
			if(properties.get(i).getID().equals(property.getID())) {
				return false;
			}
		}
		
		//Add the property, provided it passes above no-duplicate property test
		properties.add((properties.size()), property);
		return true;
	}
	
	
	/**
	 * Removes the property ID specified from the list of properties.
	 * @param id The property ID to be removed
	 * @return True if ID was found and removed, false if no matching ID was found
	 */
	public boolean removeProperty(String id) {
		
		//Search for an property ID, if found remove property and return true to caller
		//else return false
		for(int i = 0; i < properties.size(); i++) {
			if(properties.get(i).getID().equals(id)) {
				properties.remove(i);
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Retrieves the full list of properties and the corresponding data (ID, value, and valid values) 
	 * in a format to write in the configuration file. 
	 * 
	 * @return String array of all properties, or empty string array if there are no
	 * properties in the list
	 */
	public String[] retrieveExportProperties() {
		
		String[] configProperties = null;
		
		if(properties.size()==0 || properties == null) {
			configProperties = new String[0];
		}
		else {
			
			configProperties = new String[properties.size()];
			
			for(int i = 0; i < properties.size(); i++) {
				configProperties[i]=properties.get(i).toString();
			}			
		}
		
		return configProperties;
		
	}
	
	/**
	 * Retrieve the pointer to the set of properties and it's data as a 
	 * list of Property objects. Any modifications to the returned ArrayList will
	 * affect this container's list of properties.
	 * 
	 * @return An ArrayList of Property objects,
	 * or if there are no properties, return an empty ArrayList
	 */
	public ArrayList<Property> retrieveProperties() {
		
		ArrayList<Property> propertyList = null;
		
		//If empty, return an empty ArrayList
		if(properties.size()==0 || properties == null) {
			propertyList = new ArrayList<>(0);
		}
		else {
			propertyList = new ArrayList<>(properties.size());
			
			for(int i = 0; i < properties.size(); i++) {
				propertyList.add(i, properties.get(i));
			}
			
		}
		
		return propertyList;
	}
	
	/**
	 * Set a pre-prepared list of Property objects to this list.
	 * 
	 * @param properties Input ArrayList of properties to be set
	 */
	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}
	
	/**
	 * Clears all the properties in this module.
	 */
	public void clearProperties() {
		createProperties(DEFAULT_CAPACITY);
	}
	
	@Override
	public String toString() {
		return this.moreSpaces(2);
	}
	
	/**
	 * 
	 * @param numOfSpaces Number of spaces to be added BEFORE each property.
	 */
	String moreSpaces(int numOfSpaces) {
		String spaces = "";
		for(int i = 0; i < numOfSpaces; i++) {
			spaces += " ";
		}
		
		String returnValue = "";
		String[] props = this.retrieveExportProperties();
		for (int i = 0; i < props.length; i++) {
			
			returnValue += (String.format(spaces + props[i] + "%n"));			
		}
		return returnValue;
	}
	
	//Create the list of properties, can be used to clear the set as well
	private void createProperties(int capacity) {
					
		this.properties = new ArrayList<>(capacity);
				
	}
	
} //End Class
