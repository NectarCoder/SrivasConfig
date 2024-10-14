package org.akprogdevs.sconfig.write;

import java.util.ArrayList;

import org.akprogdevs.sconfig.classes.*;
import org.akprogdevs.sconfig.classes.Module;

/**
 * The Builder class is a helper class which will build an existing 
 * Configuration to help manage the parts of a Configuration without focusing on the data. 
 * 
 * @author AK Program Developers
 *
 */
public class Builder {

	private Configuration config = null;
	
	/**
	 * Default constructor that builds a brand-new Configuration.
	 * 
	 */
	public Builder() {
		this.config = new Configuration();
	}
	
	/**
	 * Constructor to assign a Configuration to a Builder
	 * 
	 * @param config The configuration object
	 */
	public Builder(Configuration config) {
		this.config = config;
	}
	
	/**
	 * Retrieves the pointer to this Builder's Configuration.
	 * 
	 * @return A Configuration object
	 */
	public Configuration retrieveConfig() {
		return this.config;
	}
	
	/**
	 * Retrieves an array of pointers to the Modules of the Configuration.
	 * 
	 * @return ArrayList of Modules
	 */
	public ArrayList<Module> retrieveConfigModules() {
		return this.config.retrieveModules();
	}
	
	/**
	 * Retrieves an array of pointers to the Properties of the Configuration.
	 * 
	 * @return ArrayList of Properties
	 */
	public ArrayList<Property> retrieveConfigProperties() {
		return this.config.retrieveProperties();
	}
	
	/**
	 * Gets the number of Modules present in the Configuration.
	 * 
	 * @return int number of Modules
	 */
	public int getModuleCount() {
		return retrieveConfigModules().size();
	}
	
	/**
	 * Gets the number of Properties present in the Configuration.
	 * 
	 * @return int number of Properties
	 */
	public int getPropertyCount() {
		return retrieveConfigProperties().size();
	}
	
	/**
	 * Converts the exported Configuration to array of bytes. Useful for writing to
	 * a file.
	 * 
	 * @return Byte array of exported Configuration
	 */
	public byte[] toByteArray() {
		String exportedConfig = config.toString();
		return exportedConfig.getBytes();
	}
	
	@Override
	public String toString() {
		return this.config.toString();
	}
	
	

}
