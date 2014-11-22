package org.ups.weather.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.application.service.IWeatherService;
import org.ups.weather.application.service.impl.WeatherService;
import org.ups.weather.location.service.ILocalWeather;

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
		
		ServiceTrackerCustomizer<ILocalWeather, ILocalWeather> serviceTrackerCustomizer =
				new LocaLWeatherServiceCustomizer(bundleContext, weatherServiceRegistration.getReference());
		ServiceTracker<ILocalWeather, ILocalWeather> serviceTracker =
				new ServiceTracker<ILocalWeather, ILocalWeather>(bundleContext, ILocalWeather.class.getName(), serviceTrackerCustomizer);
		serviceTracker.open();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
