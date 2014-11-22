package org.ups.weather.textclient.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ups.weather.application.service.IWeatherListener;
import org.ups.weather.location.service.ILocation;
import org.ups.weather.textclient.service.IWeatherService;

public class WeatherService implements IWeatherService {

	@Override
	public void addWeatherListener(IWeatherListener weatherListener,
			ILocation location) {
		System.out.println("addWeatherListener");

	}

	@Override
	public List<ILocation> getListAvailableLocation() {
		System.out.println("getListAvailableLocation");
		List<ILocation> locationList = new ArrayList<ILocation>();
		return locationList;
	}

}
