package org.akprogdevs.sconfig.classes;

import java.util.ArrayList;

/**
 * The Module class can contain a unique subset of Properties independent from the parent
 * Configuration that a Module is part of. Included are methods to get/set private attributes, toString, etc.
 * 
 * 
 * @author AK Program Developers
 *
 */
public class ModuleBackup extends PropertyContainer {

	private String moduleName = null;
	private String header = null;
	private String footer = null;
	
	
	private final static int DEFAULT_CAPACITY = 0;
	
	/**
	 * Default constructor that initializes a set or properties of size zero (0) 
	 * for this Module.
	 * 
	 * @param moduleName The name/ID of this Module
	 */
	public ModuleBackup(String moduleName) {
		this.moduleName = moduleName;
		updateModuleIdentifier();
		createProperties(DEFAULT_CAPACITY);
	}
	
	/**
	 * Constructor that initializes a set or properties of size specified for this Module.
	 * @param propertyCapacity Custom capacity to specify property list size
	 */
	public ModuleBackup(String moduleName, int propertyCapacity) {
		this.moduleName = moduleName;
		updateModuleIdentifier();
		createProperties(propertyCapacity);
	}
	
	/**
	 * Retrieve the module header.
	 * @return Module header string
	 */
	public String getHeader() {
		return this.header;
	}
	
	/**
	 * Retrieve the module footer.
	 * @return Module footer string
	 */
	public String getFooter() {
		return this.footer;
	}
	
	/**
	 * Retrieves the name of this Module.
	 * 
	 * @return This Module's name/ID
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * Sets the name of this module.
	 * 
	 * @param moduleName The module name to be set or changed.
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
		updateModuleIdentifier();
	}
	
	/**
	 * Adds a new property to the list of the Module's properties. 
	 * @param id The property's name, i.e. the ID
	 * @param value The property's value, i.e. the setting
	 * @return True if the property was added to the set successfully, or false if a duplicate ID was found on the list
	 */
	public boolean addProperty(String id, String value) {
		
		//Check to find any duplicate property IDs
		for(int i = 0; i < properties.size()-1; i++) {
			if(properties.get(i).getID().equals(id)) {
				return false;
			}
		}
		
		//Add the property, provided it passes above no-duplicate property test
		properties.add((properties.size()), (new Property(id, value)));
		return true;
	}
	
	
	/**
	 * Removes the property ID specified from this Module.
	 * @param id The property ID to be removed
	 * @return True if ID was found and removed, false if no matching ID was found
	 */
	public boolean removeProperty(String id) {
		
		//Search for an property ID, if found remove property and return true to caller
		//else return false
		for(int i = 0; i < properties.size()-1; i++) {
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
	 * properties for this Module
	 */
	public String[] retrieveModuleProperties() {
		
		String[] moduleProperties = null;
		
		if(properties.size()==0 || properties == null) {
			moduleProperties = new String[0];
		}
		else {
			
			moduleProperties = new String[properties.size()-1];
			
			for(int i = 0; i < properties.size()-1; i++) {
				moduleProperties[i]=properties.get(i).toString();
			}
			
		}
		
		return moduleProperties;
		
	}
	
	/**
	 * Retrieve the set of properties and it's data as a list of Property objects
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
			propertyList = new ArrayList<>(this.properties.size()-1);
			
			for(int i = 0; i < this.properties.size()-1; i++) {
				propertyList.add(i, this.properties.get(i));
			}
			
		}
		
		return propertyList;
	}
	
	/**
	 * Clears all the properties in this module.
	 */
	public void clearProperties() {
		createProperties(DEFAULT_CAPACITY);
	}
	
	/**
	 * Converts this Module into a readable listing for the configuration file.
	 * 
	 * @return This module's identifier and child properties, if applicable
	 */
	@Override
	public String toString() {
		
		//Return the identifier only if there are no properties
		if(this.properties.size() == 0 || this.properties == null) {
			return this.header + "%n%n" + this.footer;
		}
		else {
			
			String returnValue = this.header + "%n";
			
			for (int i = 0; i <= this.properties.size()-1;i++) {
				
				returnValue += (this.properties.get(i).toString() + "%n");			
			}	
			
			returnValue += this.footer;
			return returnValue;
		}
		
		
	}
	
	//Create the list of properties, can be used to clear the set as well
	private void createProperties(int capacity) {
				
		this.properties = new ArrayList<>(capacity);
			
	}
	
	private void updateModuleIdentifier() {
		this.header = "{" + this.moduleName + "}";
		this.footer = "{" + this.moduleName + ":end}";
	}
	
}
