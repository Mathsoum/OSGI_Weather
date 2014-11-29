package org.ups.weather.application.service;

import java.util.Observer;

public interface IOpenWeatherDataListener extends IWeatherListener {
	
	public void weatherChanged(String weatherData);

	public void addObserver(Observer weatherService);

}
