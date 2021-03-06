package org.ups.weather.randomweather.service.impl;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import org.ups.weather.location.service.ILocation;
import org.ups.weather.randomweather.service.IRandomWeather;
import org.ups.weather.randomweather.service.WeatherType;

public class RandomWeather extends Observable implements IRandomWeather {

	private static final long EVERY_TWO_SECONDS = 2000;
	private static final long START_NOW = 0;
	
	private WeatherType localWeather;
	protected ILocation relativeLocation;
	private Timer weatherTimer;
	
	public RandomWeather() {
		localWeather = WeatherType.UNKNOWN;
		weatherTimer = new Timer(true);
		weatherTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				chooseRandomWeather();
				setChanged();
				notifyObservers(localWeather);
			}
			
		}, START_NOW, EVERY_TWO_SECONDS);
	}
	
	@Override
	public WeatherType getWeather() {
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
	
	@Override
	public void setLocation(ILocation location) {
		relativeLocation = location;
	}

	@Override
	public void stop() {
		weatherTimer.cancel();
	}

}
