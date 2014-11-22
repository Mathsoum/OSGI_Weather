package org.ups.weather.location.service;

import java.util.Observer;

public interface ILocalWeather {
	public WeatherType getLocalWeather();
	
	public void addObserver(Observer observer);
}
