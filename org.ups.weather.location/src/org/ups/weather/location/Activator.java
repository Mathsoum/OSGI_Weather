package org.ups.weather.location;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.ups.weather.location.service.ILocalWeather;
import org.ups.weather.location.service.impl.MoscowWeather;
import org.ups.weather.location.service.impl.NewYorkWeather;
import org.ups.weather.location.service.impl.ParisWeather;

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

		bundleContext.registerService(ILocalWeather.class.getName(), new ParisWeather(), null);
		bundleContext.registerService(ILocalWeather.class.getName(), new MoscowWeather(), null);
		bundleContext.registerService(ILocalWeather.class.getName(), new NewYorkWeather(), null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
