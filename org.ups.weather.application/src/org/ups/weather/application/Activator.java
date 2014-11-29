package org.ups.weather.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.WeatherService;
import org.ups.weather.commandlineinterface.service.ICommandLineInterface;
import org.ups.weather.graphicaluserinterface.service.IGraphicalUserInterface;
import org.ups.weather.openstreetweather.service.IOpenWeatherData;
import org.ups.weather.randomweather.service.IRandomWeather;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		@SuppressWarnings("unchecked")
		ServiceRegistration<IWeatherService> weatherServiceRegistration =
			(ServiceRegistration<IWeatherService>) context.registerService(
				IWeatherService.class.getName(),
				new WeatherService(),
				null
			);
		
		ServiceTrackerCustomizer<IRandomWeather, IRandomWeather> randomWeatherServiceTrackerCustomizer =
				new RandomWeatherServiceCustomizer(bundleContext, weatherServiceRegistration.getReference());
		ServiceTracker<IRandomWeather, IRandomWeather> randomWeatherServiceTracker =
				new ServiceTracker<IRandomWeather, IRandomWeather>(bundleContext, IRandomWeather.class.getName(), randomWeatherServiceTrackerCustomizer);
		randomWeatherServiceTracker.open();
		
		ServiceTrackerCustomizer<IOpenWeatherData, IOpenWeatherData> openWeatherDataServiceTrackerCustomizer =
				new RealWeatherServiceCustomizer(bundleContext, weatherServiceRegistration.getReference());
		ServiceTracker<IOpenWeatherData, IOpenWeatherData> openWeatherDataServiceTracker =
				new ServiceTracker<IOpenWeatherData, IOpenWeatherData>(bundleContext, IOpenWeatherData.class.getName(), openWeatherDataServiceTrackerCustomizer);
		openWeatherDataServiceTracker.open();
		
		ServiceTrackerCustomizer<ICommandLineInterface, ICommandLineInterface> commandLineInterfaceServiceTrackerCustomizer =
				new CommandLineInterfaceServiceCustomizer(bundleContext, weatherServiceRegistration.getReference());
		ServiceTracker<ICommandLineInterface, ICommandLineInterface> commandLineInterfaceServiceTracker =
				new ServiceTracker<ICommandLineInterface, ICommandLineInterface>(bundleContext, ICommandLineInterface.class.getName(), commandLineInterfaceServiceTrackerCustomizer);
		commandLineInterfaceServiceTracker.open();

		
		ServiceTrackerCustomizer<IGraphicalUserInterface, IGraphicalUserInterface> graphicalUserInterfaceServiceTrackerCustomizer =
				new GraphicalUserInterfaceServiceCustomizer(bundleContext, weatherServiceRegistration.getReference());
		ServiceTracker<IGraphicalUserInterface, IGraphicalUserInterface> grapĥicalUserInterfaceServiceTracker =
				new ServiceTracker<IGraphicalUserInterface, IGraphicalUserInterface>(bundleContext, IGraphicalUserInterface.class.getName(), graphicalUserInterfaceServiceTrackerCustomizer);
		grapĥicalUserInterfaceServiceTracker.open();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
