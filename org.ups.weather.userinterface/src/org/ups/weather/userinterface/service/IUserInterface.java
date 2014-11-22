package org.ups.weather.userinterface.service;

import java.util.Map;

import org.ups.weather.location.service.ILocation;
import org.ups.weather.location.service.WeatherType;

public interface IUserInterface {
	public void setDataSet(Map<ILocation, WeatherType> weatherStatus);
}
