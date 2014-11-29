package org.ups.weather.application;

import java.util.Observer;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IRealWeatherListener;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.RealWeatherListener;
import org.ups.weather.openstreetweather.service.IWeatherOpenData;

public class RealWeatherServiceCustomizer implements ServiceTrackerCustomizer<IWeatherOpenData, IWeatherOpenData> {
	
	private BundleContext bundleContext;
	private IWeatherService weatherService;
	
	public RealWeatherServiceCustomizer(BundleContext _bundleContext, ServiceReference<IWeatherService> _weatherServiceReference) {
		bundleContext = _bundleContext;
		weatherService = (IWeatherService) bundleContext.getService(_weatherServiceReference);
	}

	@Override
	public IWeatherOpenData addingService(ServiceReference<IWeatherOpenData> reference) {
		IWeatherOpenData weatherOpenData = (IWeatherOpenData) bundleContext.getService(reference);
		IRealWeatherListener realWeatherListener = new RealWeatherListener();
		weatherOpenData.addObserver((Observer) realWeatherListener); //TODO Unregister this when stopping bundle... At least understand why this continue to run after shutting it off...
		realWeatherListener.addObserver((Observer) weatherService);
		
		weatherService.addWeatherListener(realWeatherListener, weatherOpenData.getLocation());
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
