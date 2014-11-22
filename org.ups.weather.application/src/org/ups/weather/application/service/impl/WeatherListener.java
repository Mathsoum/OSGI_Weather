package org.ups.weather.application.service.impl;

import java.util.Observable;
import java.util.Observer;

import org.ups.weather.application.service.IWeatherListener;
import org.ups.weather.location.service.WeatherType;

public class WeatherListener implements IWeatherListener, Observer {
	
	private WeatherType currentWeatherType;
	
	public WeatherListener() {
		currentWeatherType = WeatherType.UNKNOWN;
	}
	
	public WeatherType getCurrentWeatherType() {
		return currentWeatherType;
	}

	@Override
	public void weatherChanged(WeatherType weather) {
		currentWeatherType = weather;
	}

	@Override
	public void update(Observable observable, Object paramFromObservable) {
		if (paramFromObservable instanceof WeatherType) {
			weatherChanged((WeatherType) paramFromObservable);
		}
	}

}
