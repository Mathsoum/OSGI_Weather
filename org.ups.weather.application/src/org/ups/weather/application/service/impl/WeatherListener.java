package org.ups.weather.application.service.impl;

import org.ups.weather.application.service.IWeatherListener;
import org.ups.weather.application.service.WeatherType;

public class WeatherListener implements IWeatherListener {

	@Override
	public void weatherChanged(WeatherType wheather) {
		System.out.println("weatherChanged");
	}

}
