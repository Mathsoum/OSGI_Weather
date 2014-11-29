package org.ups.weather.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.ups.weather.application.service.IWeatherListener;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.location.service.ILocation;
import org.ups.weather.randomweather.service.WeatherType;

public class WeatherService extends Observable implements IWeatherService, Observer {
	
	Map<ILocation, Set<IWeatherListener>> weatherListeners;
	
	public WeatherService() {
		weatherListeners = new HashMap<>();
	}

	@Override
	public void addWeatherListener(IWeatherListener weatherListener,
			ILocation location) {
		System.out.println("WeatherService : addWeatherListener for " + location.toString());
		if (weatherListeners.get(location) == null) {
			weatherListeners.put(location, new HashSet<IWeatherListener>());
		}
		
		weatherListeners.get(location).add(weatherListener);
	}

	@Override
	public List<ILocation> getListAvailableLocation() {
		System.out.println("getListAvailableLocation");
		List<ILocation> locations = new ArrayList<>();
		locations.addAll(weatherListeners.keySet());
		return locations;
	}

	//TODO OMG ! Nested block... Refactor ?
	@Override
	public void update(Observable observable, Object paramFromObserver) {
		if(observable instanceof IWeatherListener) {
			IWeatherListener weatherListener = (IWeatherListener) observable;
			String weatherData = null;
			
			if(paramFromObserver instanceof WeatherType) {
				WeatherType weatherType = (WeatherType) paramFromObserver;
				weatherData = weatherType.name().substring(0, 1).toUpperCase()
						+ weatherType.name().substring(1).toLowerCase();
			} else if (paramFromObserver instanceof String) {
				weatherData = (String) paramFromObserver;
			}

			Map<String, String> message = new HashMap<>();
			for (ILocation it : weatherListeners.keySet()) {
				if (weatherListeners.get(it).contains(weatherListener)) {
					message.put(it.toString(), weatherData);
					break;
				}
			}

			setChanged();
			notifyObservers(message);
		}
	}

}
