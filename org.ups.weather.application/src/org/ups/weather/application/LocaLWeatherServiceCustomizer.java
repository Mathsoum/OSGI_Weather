package org.ups.weather.application;

import java.util.Observer;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.WeatherListener;
import org.ups.weather.location.service.ILocalWeather;

public class LocalWeatherServiceCustomizer implements ServiceTrackerCustomizer<ILocalWeather, ILocalWeather> {
	
	private BundleContext bundleContext;
	private IWeatherService weatherService;
	
	public LocalWeatherServiceCustomizer(BundleContext _bundleContext, ServiceReference<IWeatherService> _weatherServiceReference) {
		bundleContext = _bundleContext;
		weatherService = (IWeatherService) bundleContext.getService(_weatherServiceReference);
	}

	@Override
	public ILocalWeather addingService(ServiceReference<ILocalWeather> reference) {
		ILocalWeather localWeather = (ILocalWeather) bundleContext.getService(reference);
		WeatherListener weatherListener = new WeatherListener();
		localWeather.addObserver((Observer) weatherListener); //TODO Unregister this when stopping bundle... At least understand why this continue to run after shutting it off...
		weatherListener.addObserver((Observer) weatherService);
		
		weatherService.addWeatherListener(weatherListener, localWeather.getLocation());
		return localWeather;
	}

	@Override
	public void modifiedService(ServiceReference<ILocalWeather> reference,
			ILocalWeather service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<ILocalWeather> reference,
			ILocalWeather service) {
		// TODO Auto-generated method stub
	}
}
