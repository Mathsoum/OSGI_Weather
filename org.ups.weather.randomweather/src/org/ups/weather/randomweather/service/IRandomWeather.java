package org.ups.weather.randomweather.service;

import java.util.Observer;

import org.ups.weather.location.service.ILocation;

public interface IRandomWeather {
	
	public ILocation getLocation();
	
	public void setLocation(ILocation location);

	public WeatherType getWeather();
	
	public void addObserver(Observer observer);
	
	public void stop();
}
