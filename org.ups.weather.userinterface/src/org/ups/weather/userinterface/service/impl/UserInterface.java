package org.ups.weather.userinterface.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.ups.weather.location.service.ILocation;
import org.ups.weather.location.service.WeatherType;
import org.ups.weather.userinterface.service.IUserInterface;

public class UserInterface implements IUserInterface {
	
	Map<ILocation, WeatherType> weatherStatus;
	
	public UserInterface() {
		weatherStatus = new HashMap<>();
	}

	@Override
	public void setDataSet(Map<ILocation, WeatherType> _weatherStatus) {
		weatherStatus = _weatherStatus;
		print();
	}
	
	private void print() {
		System.out.println("The weather channel !");
		for(ILocation location : weatherStatus.keySet()) {
			System.out.print("(" + location.getLatitude() + " - " + location.getLongitude() + ")");
			System.out.println(" : the weather is " + weatherStatus.get(location));
		}
	}
	
	

}
