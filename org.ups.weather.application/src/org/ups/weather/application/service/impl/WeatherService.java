package org.ups.weather.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ups.weather.application.service.IWeatherListener;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.location.service.ILocation;

public class WeatherService implements IWeatherService {
	
	Map<ILocation, IWeatherListener> weatherListeners;
	
	public WeatherService() {
		weatherListeners = new HashMap<>();
	}

	@Override
	public void addWeatherListener(IWeatherListener weatherListener,
			ILocation location) {
		System.out.println("WeatherService : addWeatherListener for " + location.getLatitude() + " : " + location.getLongitude());
		weatherListeners.put(location, weatherListener);
	}

	@Override
	public List<ILocation> getListAvailableLocation() {
		System.out.println("getListAvailableLocation");
		List<ILocation> locations = new ArrayList<>();
		locations.addAll(weatherListeners.keySet());
		return locations;
	}

}
