package org.akprogdevs.sconfig.write;

import java.io.*;

import org.akprogdevs.sconfig.classes.Configuration;

/**
 * This class contains a few writer methods to help write configuration files to a
 * specified destination. It is not required to use these to write configuration files,
 * the exported/formatted Configuration can be obtained via a {@code toString} method
 *  - however, these additional methods are designed to increase workflow without needing
 *  to worry about file writing.
 * 
 * @author AK Program Developers
 *
 */
public class Writer {

	private Builder bldr = null;
	
	/**
	 * Default constructor that initializes a Builder with an empty Configuration.
	 */
	public Writer() {
		this.bldr = new Builder();
	}
	
	/**
	 * Constructor that accepts a Builder object (which in turn, contains a built 
	 * Configuration).
	 * 
	 * @param bldr Built Configuration to write to file from
	 */
	public Writer(Builder bldr) {
		this.bldr = bldr;
	}
	
	/**
	 * Constructor that accepts a Configuration object, which will be built locally.
	 * 
	 * @param cnf Configuration to be written from
	 */
	public Writer(Configuration cnf) {
		this.bldr = new Builder(cnf);
	}
	
	public Builder retrieveBuilder() {
		return this.bldr;
	}
	
	/**
	 * 
	 * Writes the Configuration file via a {@code BufferedWriter} to the destination (path) specified. The path should
	 * meet the following conditions:
	 * 
	 * <ul>
	 * 	<li>The path can be either relative or absolute,</li>
	 * 	<li>The path must specify the file name,</li>
	 *	<li>The file name at the end of the path does not need an extension specified</li>
	 * </ul>
	 * 
	 * Note that this method will simply write to the destination - without checking for
	 * existing files.
	 * 
	 * @param path Path of the destination configuration file
	 * @throws IOException
	 */
	public void bufferedWriter(String path) throws IOException {
		
		String output = this.bldr.toString();
		BufferedWriter writer = new BufferedWriter(new FileWriter(path + ".scnfg"));
		      
		writer.write(output);
		writer.close();
	}
	
	/**
	 * 
	 * Writes the Configuration file via a {@code PrintWriter} to the destination (path) specified. 
	 * The path should meet the following conditions:
	 * 
	 * <ul>
	 * 	<li>The path can be either relative or absolute,</li>
	 * 	<li>The path must specify the file name,</li>
	 *	<li>The file name at the end of the path does not need an extension specified</li>
	 * </ul>
	 * 
	 * Note that this method will simply write to the destination - without checking for
	 * existing files.
	 * 
	 * @param path Path of the destination configuration file
	 * @throws IOException
	 */
	public void printWriter(String path) throws IOException{
		
		String output = this.bldr.toString();
		
		File file = new File(path + ".scnfg");
		file.getParentFile().mkdirs();
		PrintWriter pw = new PrintWriter(new FileWriter(file));
		
		pw.print(output);
		pw.close();
		
	}
	
	
	
	
}
