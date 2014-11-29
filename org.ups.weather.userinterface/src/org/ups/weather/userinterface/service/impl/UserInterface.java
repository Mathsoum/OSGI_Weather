package org.ups.weather.userinterface.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.ups.weather.userinterface.service.IUserInterface;

//TODO What is this take a String instead of some WeatheType. Could be more generic that way.
public class UserInterface implements IUserInterface {
	
	protected Map<String, String> weatherStatus;
	
	public UserInterface() {
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
