package org.akprogdevs.sconfig.classes;

/**
 * The Module class can contain a unique subset of Properties independent from the parent
 * Configuration that a Module is part of. Included are methods to get/set private attributes, toString, etc.
 * 
 * 
 * @author AK Program Developers
 *
 */
public final class Module extends PropertyContainer {

	private String moduleName = null;
	private String header = null;
	private String footer = null;
	
	/**
	 * Default constructor that initializes a set of properties of size zero (0) 
	 * for this Module. Spaces, if any, will be eliminated automatically from the module
	 * name specified.
	 * 
	 * @param moduleName The name/ID of this Module
	 */
	public Module(String moduleName) {
		super();
		setModuleName(moduleName);
		updateModuleIdentifier();
	}
	
	/**
	 * Constructor that initializes a set of properties of size specified for this Module.
	 * Spaces, if any, will be eliminated automatically from the module name specified.
	 * 
	 * @param propertyCapacity Custom capacity to specify property list size
	 */
	public Module(String moduleName, int propertyCapacity) {
		super(propertyCapacity);
		setModuleName(moduleName);
		updateModuleIdentifier();
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
	 * Sets the name of this module. Spaces, if any, will be eliminated automatically.
	 * 
	 * @param moduleName The module name to be set or changed.
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName.replaceAll(" ", "");
		updateModuleIdentifier();
	}
		
	/**
	 * Converts this Module into a readable listing for the configuration file.
	 * 
	 * @return This module's identifier and child properties, if applicable
	 */
	@Override
	public String toString() {
		
		//Return the identifier only if there are no properties
		if(properties.size() == 0 || properties == null) {
			return String.format("  " + this.header + "%n%n" + "  " + this.footer);
		}
		
		else {
			String returnValue = String.format("  " + this.header + "%n%n");
			returnValue += super.moreSpaces(4);
			returnValue += String.format("%n" + "  " + this.footer);
			
			return returnValue;
		}
	}
	
	//Update the identifiers for this module each time the module name is updated
	private void updateModuleIdentifier() {
		this.header = "{" + this.moduleName + "}";
		this.footer = "{" + this.moduleName + ":end}";
	}
	
}
