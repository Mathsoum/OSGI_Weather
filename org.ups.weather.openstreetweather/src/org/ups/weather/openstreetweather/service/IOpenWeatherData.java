package org.ups.weather.openstreetweather.service;

import java.util.Observer;

import org.ups.weather.location.service.ILocation;

public interface IOpenWeatherData {
	
	public ILocation getLocation();
	
	public void setLocation(ILocation location);

	public String getWeather();
	
	public void addObserver(Observer observer);

	
}
