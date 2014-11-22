package org.ups.weather.userinterface.service;

import java.util.Map;
import java.util.Observer;

import org.ups.weather.location.service.ILocation;
import org.ups.weather.location.service.WeatherType;

public interface IUserInterface extends Observer {
	public void setWeatherStatus(Map<ILocation, WeatherType> weatherStatus);
}
