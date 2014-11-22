package org.ups.weather.location.service;

import java.util.Observer;

public interface ILocalWeather {
	public ILocation getLocation();
	
	public WeatherType getLocalWeather();
	
	public void addObserver(Observer observer);
}
