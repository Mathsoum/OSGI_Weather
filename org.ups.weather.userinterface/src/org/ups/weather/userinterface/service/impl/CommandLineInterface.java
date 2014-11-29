package org.ups.weather.userinterface.service.impl;

import org.ups.weather.userinterface.service.ICommandLineInterface;

public class CommandLineInterface extends UserInterface implements ICommandLineInterface {

	@Override
	protected void print() {
		for(String location : weatherStatus.keySet()) {
			System.out.print(location);
			System.out.println(" : the weather is " + weatherStatus.get(location));
		}
	}
	
}
