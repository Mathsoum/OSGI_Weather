package org.ups.weather.openstreetweather;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.weather.location.service.ILocation;
import org.ups.weather.openstreetweather.service.IWeatherOpenData;
import org.ups.weather.openstreetweather.service.impl.WeatherOpenData;

public class LocationTrackerCustomizer implements
		ServiceTrackerCustomizer<ILocation, ILocation> {
	
	private BundleContext bundleContext;
	
	public LocationTrackerCustomizer(BundleContext _bundleContext) {
		bundleContext = _bundleContext;
	}

	@Override
	public ILocation addingService(ServiceReference<ILocation> reference) {
		ILocation location = (ILocation) bundleContext.getService(reference);
		
		WeatherOpenData weatherOpenData = new WeatherOpenData();
		weatherOpenData.setLocation(location);
		bundleContext.registerService(IWeatherOpenData.class.getName(), weatherOpenData, null);
		
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
