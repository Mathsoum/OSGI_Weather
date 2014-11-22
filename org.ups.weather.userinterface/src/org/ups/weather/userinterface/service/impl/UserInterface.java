package org.ups.weather.userinterface.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.ups.weather.location.service.ILocation;
import org.ups.weather.location.service.WeatherType;
import org.ups.weather.userinterface.service.IUserInterface;

public class UserInterface implements IUserInterface {
	
	Map<ILocation, WeatherType> weatherStatus;
	
	public UserInterface() {
		weatherStatus = new HashMap<>();

		System.out.println("The weather channel !");
		System.out.println("---------------------");
	}

	@Override
	public void setWeatherStatus(Map<ILocation, WeatherType> _weatherStatus) {
		weatherStatus = _weatherStatus;
		print();
	}
	
	private void print() {
		for(ILocation location : weatherStatus.keySet()) {
			System.out.print("(" + location.getLatitude() + " - " + location.getLongitude() + ")");
			System.out.println(" : the weather is " + weatherStatus.get(location));
		}
	}

	@Override
	public void update(Observable observable, Object paramFromObservable) {
		if(paramFromObservable instanceof Map<?, ?>) {
			@SuppressWarnings("unchecked")
			Map<ILocation, WeatherType> weatherStatus = (Map<ILocation, WeatherType>) paramFromObservable;
			setWeatherStatus(weatherStatus);
		}
	}
	
	

}
