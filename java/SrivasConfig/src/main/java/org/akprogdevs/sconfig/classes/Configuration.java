package org.akprogdevs.sconfig.classes;

import java.util.ArrayList;

/**
 * The main Configuration class (SrivasConfig), with methods to add, remove, and search for properties and modules.
 * @author AK Program Developers
 *
 */
public final class Configuration extends PropertyContainer {
	
	/**
	 * The version of this Configuration
	 */
	public final static double version = 1;
	
	protected String header = "{SrivasConfig-scnfg-version:" + version + "}";
	protected String footer = "{scnfg:end}";
	private ArrayList<Module> modules;
	private final static int DEFAULT_CAPACITY = 0;
	
	
	/**
	 * Default constructor that initializes a set of properties and modules 
	 * of size zero (0) for this Configuration.
	 */
	public Configuration() {
		super();
		createModules(DEFAULT_CAPACITY);
	}
	
	/**
	 * Constructor that initializes a set of properties and modules 
	 * of size specified for this Configuration.
	 * @param propertyCapacity Custom capacity to specify property list size
	 * @param moduleCapacity Custom capacity to specify number of modules
	 */
	public Configuration(int propertyCapacity, int moduleCapacity) {
		super(propertyCapacity);
		createModules(moduleCapacity);
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
		for(int i = 0; i < modules.size(); i++) {
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
			
			configModules = new String[modules.size()];
			
			for(int i = 0; i < modules.size(); i++) {
				configModules[i]=modules.get(i).toString();
			}
			
		}
		
		return configModules;
	}
	
	/**
	 * Retrieve the pointer to the set of modules and it's data as a list of 
	 * Module objects. Any modifications to the returned ArrayList will affect 
	 * this Configuration's list of modules.
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
			moduleList = new ArrayList<>(this.modules.size());
			
			for(int i = 0; i < this.modules.size(); i++) {
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
	
	@Override
	public String toString() {
		
		//Add header
		String returnValue = String.format(this.header + "%n%n");
		
		if( !(properties.size() == 0 || properties == null) ) {
			returnValue += super.toString();
		}
		
		//Extra line between the properties and modules
		returnValue += String.format("%n");
		
		if( !(modules.size() == 0 || modules == null) ) {
			String[] mods = this.retrieveConfigModules();
			for (int i = 0; i < mods.length; i++) {
				returnValue += String.format(mods[i] + "%n%n");
			}			
		}
		
		//Add the footer
		returnValue += String.format("%n" + this.footer);
		
		
		return returnValue;
	}
	
	//Create the list of modules, can be used to clear the set as well
	private void createModules(int capacity) {
						
		this.modules = new ArrayList<>(capacity);
		
	}
	
	
	
	
	
	
	
}
