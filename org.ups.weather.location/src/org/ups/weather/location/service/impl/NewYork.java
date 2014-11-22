package org.ups.weather.location.service.impl;

import org.ups.weather.location.service.ILocation;

public class NewYork implements ILocation {

	@Override
	public Float getLatitude() {
		return 40.7127f;
	}

	@Override
	public Float getLongitude() {
		return 74.0059f;
	}

}
