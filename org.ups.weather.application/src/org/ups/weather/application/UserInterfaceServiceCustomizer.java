package org.ups.weather.application;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.WeatherService;
import org.ups.weather.userinterface.service.IUserInterface;

public class UserInterfaceServiceCustomizer implements ServiceTrackerCustomizer<IUserInterface, IUserInterface> {
	
	private BundleContext bundleContext;
	private WeatherService weatherService;

	public UserInterfaceServiceCustomizer(BundleContext _bundleContext, ServiceReference<IWeatherService> serviceReference) {
		bundleContext = _bundleContext;
		weatherService = (WeatherService) bundleContext.getService(serviceReference);
	}
	
	@Override
	public IUserInterface addingService(ServiceReference<IUserInterface> reference) {
		IUserInterface userInterface = (IUserInterface) bundleContext.getService(reference);
		weatherService.addObserver(userInterface);
		
		return userInterface;
	}

	@Override
	public void modifiedService(ServiceReference<IUserInterface> reference, IUserInterface service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference<IUserInterface> reference, IUserInterface service) {
		// TODO Auto-generated method stub

	}

}
