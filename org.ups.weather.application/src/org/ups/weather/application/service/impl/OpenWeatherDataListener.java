package org.ups.weather.application.service.impl;

import java.util.Observable;
import java.util.Observer;

import org.ups.weather.application.service.IOpenWeatherDataListener;
import org.ups.weather.randomweather.service.WeatherType;

public class OpenWeatherDataListener extends Observable implements IOpenWeatherDataListener, Observer {
	
	private String currentWeatherData;

	@Override
	public void weatherChanged(String weatherData) {

		currentWeatherData = weatherData;
		setChanged();
		notifyObservers(currentWeatherData);
	}

	@Override
	public void update(Observable observable, Object paramFromObservable) {
		if (paramFromObservable instanceof String) {
			weatherChanged((String) paramFromObservable);
		}
	}

	@Override
	public void weatherChanged(WeatherType weather) {
		// Nothing because of String weather date
	}

}
