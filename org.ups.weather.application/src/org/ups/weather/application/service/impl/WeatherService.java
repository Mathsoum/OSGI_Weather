package org.ups.weather.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.ups.weather.application.service.IWeatherListener;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.location.service.ILocation;
import org.ups.weather.randomweather.service.WeatherType;

public class WeatherService extends Observable implements IWeatherService, Observer {
	
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

	//TODO OMG ! Nested block... Refactor ?
	@Override
	public void update(Observable observable, Object paramFromObserver) {
		if(observable instanceof IWeatherListener) {
			IWeatherListener weatherListener = (IWeatherListener) observable;
			if(paramFromObserver instanceof WeatherType) {
				WeatherType weatherType = (WeatherType) paramFromObserver;
				Map<ILocation, WeatherType> message = new HashMap<>();
				for (Map.Entry<ILocation, IWeatherListener> it : weatherListeners.entrySet()) {
					if (it.getValue() == weatherListener) {
						message.put(it.getKey(), weatherType);
						break;
					}
				}
				
				setChanged();
				notifyObservers(message);
			}
		}
	}

}
