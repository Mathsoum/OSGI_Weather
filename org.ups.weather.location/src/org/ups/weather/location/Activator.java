package org.ups.weather.location;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.ups.weather.location.service.ILocalWeather;
import org.ups.weather.location.service.impl.MoscowWeather;
import org.ups.weather.location.service.impl.NewYorkWeather;
import org.ups.weather.location.service.impl.ParisWeather;

public class Activator implements BundleActivator {

	private static BundleContext context;

	private ParisWeather parisWeather;
	private MoscowWeather moscowWeather;
	private NewYorkWeather newYorkWeather;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		parisWeather = new ParisWeather();
		moscowWeather = new MoscowWeather();
		newYorkWeather = new NewYorkWeather();
		bundleContext.registerService(ILocalWeather.class.getName(), parisWeather, null);
		bundleContext.registerService(ILocalWeather.class.getName(), moscowWeather, null);
		bundleContext.registerService(ILocalWeather.class.getName(), newYorkWeather, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		parisWeather.stopTimer();
		moscowWeather.stopTimer();
		newYorkWeather.stopTimer();
		
		Activator.context = null;
		
	}

}
