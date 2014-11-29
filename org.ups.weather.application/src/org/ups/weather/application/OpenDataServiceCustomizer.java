package org.ups.weather.application;

import java.util.Observer;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.WeatherListener;
import org.ups.weather.openstreetweather.service.IWeatherOpenData;

public class OpenDataServiceCustomizer implements ServiceTrackerCustomizer<IWeatherOpenData, IWeatherOpenData> {

	private BundleContext bundleContext;
	private IWeatherService weatherService;
	
	public OpenDataServiceCustomizer(BundleContext _bundleContext, ServiceReference<IWeatherService> _weatherServiceReference) {
		bundleContext = _bundleContext;
		weatherService = (IWeatherService) bundleContext.getService(_weatherServiceReference);
	}

	@Override
	public IWeatherOpenData addingService(ServiceReference<IWeatherOpenData> reference) {
		IWeatherOpenData weatherOpenData = (IWeatherOpenData) bundleContext.getService(reference);
		WeatherListener weatherListener = new WeatherListener();
		weatherOpenData.addObserver((Observer) weatherListener); //TODO Unregister this when stopping bundle... At least understand why this continue to run after shutting it off...
		weatherListener.addObserver((Observer) weatherService);
		
		weatherService.addWeatherListener(weatherListener, weatherOpenData.getLocation());
		return weatherOpenData;
	}

	@Override
	public void modifiedService(ServiceReference<IWeatherOpenData> reference,
			IWeatherOpenData service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference<IWeatherOpenData> reference,
			IWeatherOpenData service) {
		// TODO Auto-generated method stub

	}

}
