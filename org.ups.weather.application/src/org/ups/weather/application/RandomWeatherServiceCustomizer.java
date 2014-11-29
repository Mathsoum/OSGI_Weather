package org.ups.weather.application;

import java.util.Observer;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.WeatherListener;
import org.ups.weather.randomweather.service.IRandomWeather;

public class RandomWeatherServiceCustomizer implements ServiceTrackerCustomizer<IRandomWeather, IRandomWeather> {
	
	private BundleContext bundleContext;
	private IWeatherService weatherService;
	
	public RandomWeatherServiceCustomizer(BundleContext _bundleContext, ServiceReference<IWeatherService> _weatherServiceReference) {
		bundleContext = _bundleContext;
		weatherService = (IWeatherService) bundleContext.getService(_weatherServiceReference);
	}

	@Override
	public IRandomWeather addingService(ServiceReference<IRandomWeather> reference) {
		IRandomWeather localWeather = (IRandomWeather) bundleContext.getService(reference);
		WeatherListener weatherListener = new WeatherListener();
		localWeather.addObserver((Observer) weatherListener); //TODO Unregister this when stopping bundle... At least understand why this continue to run after shutting it off...
		weatherListener.addObserver((Observer) weatherService);
		
		weatherService.addWeatherListener(weatherListener, localWeather.getLocation());
		return localWeather;
	}

	@Override
	public void modifiedService(ServiceReference<IRandomWeather> reference,
			IRandomWeather service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<IRandomWeather> reference,
			IRandomWeather service) {
		// TODO Auto-generated method stub
	}
}
