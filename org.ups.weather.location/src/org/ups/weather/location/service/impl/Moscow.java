package org.ups.weather.location.service.impl;

import org.ups.weather.location.service.ILocation;

public class Moscow extends AbstractLocation implements ILocation {

	@Override
	public Float getLatitude() {
		return 55.7500f;
	}

	@Override
	public Float getLongitude() {
		return 37.6167f;
	}

}
