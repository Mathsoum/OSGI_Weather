package org.ups.weather.location.service.impl;

import org.ups.weather.location.service.ILocation;

public class Paris extends AbstractLocation implements ILocation {

	@Override
	public Float getLatitude() {
		return 48.8567f;
	}

	@Override
	public Float getLongitude() {
		return 2.3508f;
	}

}
