package org.akprogdevs.sconfig.classes;

import java.util.ArrayList;

/**
 * The main Configuration class (SrivasConfig), with methods to add, remove, and search for properties and modules.
 * @author AK Program Developers
 *
 */
public class ConfigurationBackup {
	
	/**
	 * The version of this Configuration
	 */
	public final static double version = 1;
	
	protected String header = "{SrivasConfig-scnfg-version:" + version + "}";
	private ArrayList<Property> properties;
	private ArrayList<Module> modules;
	private final static int DEFAULT_CAPACITY = 0;
	
	
	/**
	 * Default constructor that initializes a set of properties and modules 
	 * of size zero (0) for this Configuration.
	 */
	public ConfigurationBackup() {
		createProperties(DEFAULT_CAPACITY);
		createModules(DEFAULT_CAPACITY);
	}
	
	/**
	 * Constructor that initializes a set of properties and modules 
	 * of size specified for this Configuration.
	 * @param propertyCapacity Custom capacity to specify property list size
	 * @param moduleCapacity Custom capacity to specify number of modules
	 */
	public ConfigurationBackup(int propertyCapacity, int moduleCapacity) {
		createProperties(propertyCapacity);
		createModules(moduleCapacity);
	}
	
	/**
	 * Adds a new property to the list of the Configuration's properties. 
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
	 * Removes the property ID specified from the Configuration.
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
	 * properties for this Configuration
	 */
	public String[] retrieveConfigProperties() {
		
		String[] configProperties = null;
		
		if(properties.size()==0 || properties == null) {
			configProperties = new String[0];
		}
		else {
			
			configProperties = new String[properties.size()-1];
			
			for(int i = 0; i < properties.size()-1; i++) {
				configProperties[i]=properties.get(i).toString();
			}
			
		}
		
		return configProperties;
		
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
	 * Set a pre-prepared list of Property objects to this Configuration.
	 * 
	 * @param properties Input ArrayList of properties to be set
	 */
	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}
	
	/**
	 * Clears all the properties in this configuration.
	 */
	public void clearProperties() {
		createProperties(DEFAULT_CAPACITY);
	}
	
	/**
	 * Adds a new Module to the list of modules for this Configuration.
	 * 
	 * @param moduleID Name of module to be added
	 * 
	 * @return True if module was successfully added or false if duplicate module ID was found
	 */
	public boolean addModule(String moduleID) {
		
		//Check to find any duplicate module IDs, return false if duplicate is found
		for(int i = 0; i < modules.size(); i++) {
			if(modules.get(i).getModuleName().equals(moduleID)) {
				return false;
			}
		}
		
		this.modules.add((this.modules.size()),new Module(moduleID));
		
		return true;
		
	}
	
	/**
	 * Removes a module from this Configuration - removing a Module WILL remove the child
	 * properties of the to-be deleted Module. Use the methods retrieveModuleProperties()
	 * or retrieveProperties() to obtain the child properties before deletion,
	 * if necessary.
	 * 
	 * @param moduleID Name/ID of module to be removed
	 * 
	 * @return True if module is removed successfully or false if the module does not exist
	 */
	public boolean removeModule(String moduleID) {
		
		//Search for an module ID, if found remove module and return true to caller
		//else return false
		for(int i = 0; i < modules.size()-1; i++) {
			if(modules.get(i).getModuleName().equals(moduleID)) {
				modules.remove(i);
				return true;
			}
		}
				
		return false;
		
	}
	
	/**
	 * Retrieves the full list of modules and the child properties
	 * in a format to write in the configuration file. 
	 * 
	 * @return String array of all modules, or empty string array if there are no
	 * modules for this Configuration
	 */
	public String[] retrieveConfigModules() {
		
		String[] configModules = null;
		
		if(modules.size()==0 || modules == null) {
			configModules = new String[0];
		}
		else {
			
			configModules = new String[modules.size()-1];
			
			for(int i = 0; i < modules.size()-1; i++) {
				configModules[i]=modules.get(i).toString();
			}
			
		}
		
		return configModules;
	}
	
	/**
	 * Retrieve the set of modules and it's data as a list of Module objects
	 * 
	 * @return An ArrayList of Module objects,
	 * or if there are no modules, return an empty ArrayList
	 */
	public ArrayList<Module> retrieveModules() {
		
		ArrayList<Module> moduleList = null;
		
		//If empty, return an empty ArrayList
		if(modules.size()==0 || modules == null) {
			moduleList = new ArrayList<>(0);
		}
		else {
			moduleList = new ArrayList<>(this.modules.size()-1);
			
			for(int i = 0; i < this.modules.size()-1; i++) {
				moduleList.add(i, this.modules.get(i));
			}
			
		}
		
		return moduleList;
		
	}
	
	/**
	 * Clears all the properties in this configuration.
	 */
	public void clearModules() {
		createModules(DEFAULT_CAPACITY);
	}
	
	//Create the list of properties, can be used to clear the set as well
	private void createProperties(int capacity) {
				
		this.properties = new ArrayList<>(capacity);
		
	}
	
	//Create the list of modules, can be used to clear the set as well
	private void createModules(int capacity) {
						
		this.modules = new ArrayList<>(capacity);
		
	}
	
	
	
	
	
	
	
}
