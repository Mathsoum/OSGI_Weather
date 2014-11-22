package org.ups.weather.textclient;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IWeatherListener;

public class WeatherListenerCustomizer implements
		ServiceTrackerCustomizer<IWeatherListener, IWeatherListener> {

	private BundleContext bundleContext;
	
	public WeatherListenerCustomizer(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
	
	@Override
	public IWeatherListener addingService(ServiceReference<IWeatherListener> reference) {
		// TODO Auto-generated method stub
		IWeatherListener weatherListener = (IWeatherListener) bundleContext.getService(reference);
		
		return weatherListener;
	}

	@Override
	public void modifiedService(ServiceReference<IWeatherListener> reference, IWeatherListener service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference<IWeatherListener> reference, IWeatherListener service) {
		// TODO Auto-generated method stub

	}

}
