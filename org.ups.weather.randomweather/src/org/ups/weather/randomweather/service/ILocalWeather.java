package org.ups.weather.randomweather.service;

import java.util.Observer;

import org.ups.weather.location.service.ILocation;

public interface ILocalWeather {
	public ILocation getLocation();

	public void setLocation(ILocation location);
	
	public WeatherType getLocalWeather();
	
	public void addObserver(Observer observer);
}
