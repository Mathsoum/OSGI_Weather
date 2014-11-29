package org.ups.weather.application;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.WeatherService;
import org.ups.weather.graphicaluserinterface.service.IGraphicalUserInterface;

public class GraphicalUserInterfaceServiceCustomizer implements ServiceTrackerCustomizer<IGraphicalUserInterface, IGraphicalUserInterface> {
	
	private BundleContext bundleContext;
	private WeatherService weatherService;

	public GraphicalUserInterfaceServiceCustomizer(BundleContext _bundleContext, ServiceReference<IWeatherService> serviceReference) {
		bundleContext = _bundleContext;
		weatherService = (WeatherService) bundleContext.getService(serviceReference);
	}
	
	@Override
	public IGraphicalUserInterface addingService(ServiceReference<IGraphicalUserInterface> reference) {
		IGraphicalUserInterface userInterface = (IGraphicalUserInterface) bundleContext.getService(reference);
		weatherService.addObserver(userInterface);  //TODO Must be unregistered when stopping bundle... And i don't know how to do it...
		
		return userInterface;
	}

	@Override
	public void modifiedService(ServiceReference<IGraphicalUserInterface> reference, IGraphicalUserInterface service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference<IGraphicalUserInterface> reference, IGraphicalUserInterface service) {
		// TODO Auto-generated method stub

	}

}
