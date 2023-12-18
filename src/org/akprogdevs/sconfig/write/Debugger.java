package org.akprogdevs.sconfig.write;

import java.io.IOException;

import org.akprogdevs.sconfig.classes.Configuration;

public class Debugger {

	
	
	public static void main(String args[]) {
		
		Writer wtr = new Writer(new Builder());
		Builder bldr = wtr.retrieveBuilder();
		Configuration conf = bldr.retrieveConfig();
		
		conf.addProperty("prop1", "val1");
		conf.addModule("mod1");
		conf.addModule("mod2");
		conf.retrieveModules().get(0).addProperty("prop2", "val2");
		conf.retrieveModules().get(0).addProperty("prop3", "val3");
		conf.retrieveModules().get(1).addProperty("prop4", "val4");
		conf.addProperty("prop5", "val5");
		conf.addProperty("prop6", "val6");
		
		try	{
			
			wtr.printWriter("test\\test");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
}
