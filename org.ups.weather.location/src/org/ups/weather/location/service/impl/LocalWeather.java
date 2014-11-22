package org.ups.weather.location.service.impl;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import org.ups.weather.location.service.ILocalWeather;
import org.ups.weather.location.service.ILocation;
import org.ups.weather.location.service.WeatherType;

public abstract class LocalWeather extends Observable implements ILocalWeather {
	
	private WeatherType localWeather;
	protected ILocation relativeLocation;
	
	public LocalWeather() {
		localWeather = WeatherType.UNKNOWN;
		Timer weatherTimer = new Timer();
		weatherTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				chooseRandomWeather();
				setChanged();
				notifyObservers();
			}
		}, 0, 2000); // Start now, then run every 2 secs (2000 millisecs)
	}
	
	@Override
	public WeatherType getLocalWeather() {
		return localWeather;
	}
	
	private void chooseRandomWeather() {
		localWeather = WeatherType.values()[(localWeather.ordinal() + 1) % WeatherType.values().length];
	}
	
	@Override
	public ILocation getLocation() {
		// TODO Auto-generated method stub
		return relativeLocation;
	}

}
