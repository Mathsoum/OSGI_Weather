package org.ups.weather.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.WeatherService;
import org.ups.weather.openstreetweather.service.IWeatherOpenData;
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
		
//		ServiceTrackerCustomizer<ILocalWeather, ILocalWeather> weatherServiceTrackerCustomizer =
//				new LocalWeatherServiceCustomizer(bundleContext, weatherServiceRegistration.getReference());
//		ServiceTracker<ILocalWeather, ILocalWeather> weatherServiceTracker =
//				new ServiceTracker<ILocalWeather, ILocalWeather>(bundleContext, ILocalWeather.class.getName(), weatherServiceTrackerCustomizer);
//		weatherServiceTracker.open();
		
		ServiceTrackerCustomizer<IWeatherOpenData, IWeatherOpenData> openDataServiceTrackerCustomizer =
				new OpenDataServiceCustomizer(bundleContext, weatherServiceRegistration.getReference());
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
