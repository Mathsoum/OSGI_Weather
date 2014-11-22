package org.ups.weather.application.service.impl;

import java.util.Observable;
import java.util.Observer;

import org.ups.weather.application.service.IWeatherListener;
import org.ups.weather.location.service.WeatherType;

public class WeatherListener implements IWeatherListener, Observer { //TODO What if this is Observable and TextPrinter subscribe to it ?
	
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
		//TODO For now, print instead of emit something. Debug.
		System.out.println("Weather changed ! " + currentWeatherType);
	}

	@Override
	public void update(Observable observable, Object paramFromObservable) {
		if (paramFromObservable instanceof WeatherType) {
			weatherChanged((WeatherType) paramFromObservable);
		}
	}

}
