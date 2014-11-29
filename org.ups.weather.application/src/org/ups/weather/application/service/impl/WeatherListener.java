package org.ups.weather.application.service.impl;

import java.util.Observable;
import java.util.Observer;

import org.ups.weather.application.service.IWeatherListener;
import org.ups.weather.randomweather.service.WeatherType;

public class WeatherListener extends Observable implements IWeatherListener, Observer {
	
	private WeatherType currentWeatherType;
	
	public WeatherListener() {
		currentWeatherType = WeatherType.UNKNOWN;
	}

	@Override
	public void weatherChanged(WeatherType weather) {
		currentWeatherType = weather;
		
		setChanged();
		notifyObservers(currentWeatherType);
	}

	@Override
	public void update(Observable observable, Object paramFromObservable) {
		if (paramFromObservable instanceof WeatherType) {
			weatherChanged((WeatherType) paramFromObservable);
		}
	}

}
