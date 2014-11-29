package org.ups.weather.application.service;

import java.util.Observer;

public interface IRealWeatherListener extends IWeatherListener {
	
	public void weatherChanged(String weatherData);

	public void addObserver(Observer weatherService);

}
