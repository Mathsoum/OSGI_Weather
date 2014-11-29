package org.ups.weather.application;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.WeatherService;
import org.ups.weather.commandlineinterface.service.ICommandLineInterface;

public class CommandLineInterfaceServiceCustomizer implements ServiceTrackerCustomizer<ICommandLineInterface, ICommandLineInterface> {
	
	private BundleContext bundleContext;
	private WeatherService weatherService;

	public CommandLineInterfaceServiceCustomizer(BundleContext _bundleContext, ServiceReference<IWeatherService> serviceReference) {
		bundleContext = _bundleContext;
		weatherService = (WeatherService) bundleContext.getService(serviceReference);
	}
	
	@Override
	public ICommandLineInterface addingService(ServiceReference<ICommandLineInterface> reference) {
		ICommandLineInterface userInterface = (ICommandLineInterface) bundleContext.getService(reference);
		weatherService.addObserver(userInterface);
		
		return userInterface;
	}

	@Override
	public void modifiedService(ServiceReference<ICommandLineInterface> reference, ICommandLineInterface service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference<ICommandLineInterface> reference, ICommandLineInterface service) {
		// TODO Auto-generated method stub

	}

}
