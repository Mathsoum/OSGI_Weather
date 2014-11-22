package org.ups.weather.location;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.ups.weather.location.service.ILocation;
import org.ups.weather.location.service.impl.Location;
import org.ups.weather.location.service.impl.Moscow;
import org.ups.weather.location.service.impl.NewYork;
import org.ups.weather.location.service.impl.Paris;

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
		bundleContext.registerService(ILocation.class.getName(), new Location(), null);
		bundleContext.registerService(ILocation.class.getName(), new Paris(), null);
		bundleContext.registerService(ILocation.class.getName(), new Moscow(), null);
		bundleContext.registerService(ILocation.class.getName(), new NewYork(), null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
