package org.ups.weather.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.WeatherService;
import org.ups.weather.openstreetweather.service.IWeatherOpenData;
import org.ups.weather.randomweather.service.IRandomWeather;
import org.ups.weather.userinterface.service.IUserInterface;

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
		
		ServiceTrackerCustomizer<IWeatherOpenData, IWeatherOpenData> openDataServiceTrackerCustomizer =
				new RealWeatherServiceCustomizer(bundleContext, weatherServiceRegistration.getReference());
		ServiceTracker<IWeatherOpenData, IWeatherOpenData> openDataServiceTracker =
				new ServiceTracker<IWeatherOpenData, IWeatherOpenData>(bundleContext, IWeatherOpenData.class.getName(), openDataServiceTrackerCustomizer);
		openDataServiceTracker.open();
		
		ServiceTrackerCustomizer<IUserInterface, IUserInterface> userInterfaceServiceTrackerCustomizer =
				new UserInterfaceServiceCustomizer(bundleContext, weatherServiceRegistration.getReference());
		ServiceTracker<IUserInterface, IUserInterface> userInterfaceServiceTracker =
				new ServiceTracker<IUserInterface, IUserInterface>(bundleContext, IUserInterface.class.getName(), userInterfaceServiceTrackerCustomizer);
		userInterfaceServiceTracker.open();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
