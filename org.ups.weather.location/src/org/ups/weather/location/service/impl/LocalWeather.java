package org.ups.weather.location.service.impl;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import org.ups.weather.location.service.ILocalWeather;
import org.ups.weather.location.service.ILocation;
import org.ups.weather.location.service.WeatherType;

public abstract class LocalWeather extends Observable implements ILocalWeather {

	private static final long TWO_SECONDS = 2000;
	private static final long NOW = 0;
	
	private WeatherType localWeather;
	protected ILocation relativeLocation;
	private Timer weatherTimer;
	
	public LocalWeather() {
		localWeather = WeatherType.UNKNOWN;
		weatherTimer = new Timer(true);
		weatherTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				chooseRandomWeather();
				setChanged();
				notifyObservers(localWeather);
			}
			
		}, NOW, TWO_SECONDS);
	}
	
	@Override
	public WeatherType getLocalWeather() {
		return localWeather;
	}
	
	public void stopTimer() {
		weatherTimer.cancel();
		weatherTimer.purge();
	}
	
	private void chooseRandomWeather() {
		localWeather = WeatherType.values()[(localWeather.ordinal() + 1) % WeatherType.values().length];
	}
	
	@Override
	public ILocation getLocation() {
		return relativeLocation;
	}

}
