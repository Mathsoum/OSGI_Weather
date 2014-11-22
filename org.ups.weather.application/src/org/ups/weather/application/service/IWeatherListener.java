package org.ups.weather.application.service;

import org.ups.weather.location.service.WeatherType;


public interface IWeatherListener {

	public void weatherChanged(WeatherType weather);
}
