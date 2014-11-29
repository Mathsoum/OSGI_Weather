package org.ups.weather.weatherdata.service;

import java.util.Observer;

import org.ups.weather.location.service.ILocation;

public interface ILocalWeather {
	
	public ILocation getLocation();
	
	public void setLocation(ILocation location);

	public String getWeather();
	
	public void addObserver(Observer observer);

}
