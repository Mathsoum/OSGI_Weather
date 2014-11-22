package org.ups.weather.application.client;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.location.service.ILocation;

public class LocationServiceCustomizer implements ServiceTrackerCustomizer<ILocation, ILocation> {
	
	private BundleContext bundleContext;
	
	public LocationServiceCustomizer(BundleContext _bundleContext) {
		bundleContext = _bundleContext;
	}

	@Override
	public ILocation addingService(ServiceReference<ILocation> reference) {
		ILocation location = (ILocation) bundleContext.getService(reference);
		System.out.println("Longitude : " + location.getLongitude() + " // Latitude : " + location.getLatitude());
		return location;
	}

	@Override
	public void modifiedService(ServiceReference<ILocation> reference,
			ILocation service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<ILocation> reference,
			ILocation service) {
		// TODO Auto-generated method stub
		
	}
}
