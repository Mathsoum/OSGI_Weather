package org.ups.weather.userinterface;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.ups.weather.userinterface.service.IUserInterface;
import org.ups.weather.userinterface.service.impl.CommandLineInterface;

/**
 * The activator class controls the plug-in life cycle
 */
//TODO Extract GUI bundle, even if it doen't work.
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

		context.registerService(IUserInterface.class.getName(), new CommandLineInterface(), null);
//		context.registerService(IUserInterface.class.getName(), new GraphicalUserInterface(), null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
}
