package org.ups.weather.application.service;

import org.ups.weather.randomweather.service.WeatherType;


public interface IWeatherListener {

	public void weatherChanged(WeatherType weather);
}
