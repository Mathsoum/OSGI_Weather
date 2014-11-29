package org.ups.weather.commandlineinterface.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.ups.weather.commandlineinterface.service.ICommandLineInterface;

public class CommandLineInterface implements ICommandLineInterface {
	
	protected Map<String, String> weatherStatus;
	
	public CommandLineInterface() {
		weatherStatus = new HashMap<>();

		System.out.println("The weather channel !");
		System.out.println("---------------------");
	}

	@Override
	public void setWeatherStatus(Map<String, String> _weatherStatus) {
		weatherStatus = _weatherStatus;
		print();
	}
	
	protected void print() {
		for(String location : weatherStatus.keySet()) {
			System.out.print(location);
			System.out.println(" : the weather is " + weatherStatus.get(location));
		}
	}

	@Override
	public void update(Observable observable, Object paramFromObservable) {
		if(paramFromObservable instanceof Map<?, ?>) {
			@SuppressWarnings("unchecked")
			Map<String, String> weatherStatus = (Map<String, String>) paramFromObservable;
			setWeatherStatus(weatherStatus);
		}
	}
	
	

}
