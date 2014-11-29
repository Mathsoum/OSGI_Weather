package org.ups.weather.application;

import java.util.Observer;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IOpenWeatherDataListener;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.OpenWeatherDataListener;
import org.ups.weather.openstreetweather.service.IOpenWeatherData;

public class RealWeatherServiceCustomizer implements ServiceTrackerCustomizer<IOpenWeatherData, IOpenWeatherData> {
	
	private BundleContext bundleContext;
	private IWeatherService weatherService;
	
	public RealWeatherServiceCustomizer(BundleContext _bundleContext, ServiceReference<IWeatherService> _weatherServiceReference) {
		bundleContext = _bundleContext;
		weatherService = (IWeatherService) bundleContext.getService(_weatherServiceReference);
	}

	@Override
	public IOpenWeatherData addingService(ServiceReference<IOpenWeatherData> reference) {
		IOpenWeatherData weatherOpenData = (IOpenWeatherData) bundleContext.getService(reference);
		IOpenWeatherDataListener realWeatherListener = new OpenWeatherDataListener();
		weatherOpenData.addObserver((Observer) realWeatherListener); //TODO Unregister this when stopping bundle... At least understand why this continue to run after shutting it off...
		realWeatherListener.addObserver((Observer) weatherService);
		
		weatherService.addWeatherListener(realWeatherListener, weatherOpenData.getLocation());
		return weatherOpenData;
	}

	@Override
	public void modifiedService(ServiceReference<IOpenWeatherData> reference,
			IOpenWeatherData service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<IOpenWeatherData> reference,
			IOpenWeatherData service) {
		// TODO Auto-generated method stub
	}
}
