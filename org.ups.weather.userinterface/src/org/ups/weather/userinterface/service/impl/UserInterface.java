package org.ups.weather.userinterface.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.ups.weather.userinterface.service.IUserInterface;

public abstract class UserInterface implements IUserInterface {
	
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
	
	protected abstract void print();

	@Override
	public void update(Observable observable, Object paramFromObservable) {
		if(paramFromObservable instanceof Map<?, ?>) {
			@SuppressWarnings("unchecked")
			Map<String, String> weatherStatus = (Map<String, String>) paramFromObservable;
			setWeatherStatus(weatherStatus);
		}
	}
	
	

}
