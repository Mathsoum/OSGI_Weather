package org.ups.weather.openstreetweather.service;

import java.util.Observer;

import org.ups.weather.location.service.ILocation;

public interface IWeatherOpenData {
	
	public ILocation getLocation();
	
	public void setLocation(ILocation location);
	
	public void addObserver(Observer observer);

	public	String getXmlWeather();
}
