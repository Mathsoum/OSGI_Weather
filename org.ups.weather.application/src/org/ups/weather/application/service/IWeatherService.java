package org.ups.weather.application.service;

import java.util.List;

import org.ups.weather.location.service.ILocation;

public interface IWeatherService {

	public void addWeatherListener(IWeatherListener weatherListener, ILocation location);
	
	public List<ILocation> getListAvailableLocation();
}
