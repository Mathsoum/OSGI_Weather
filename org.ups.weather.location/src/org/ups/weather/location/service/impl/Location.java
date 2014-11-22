package org.ups.weather.location.service.impl;

import org.ups.weather.location.service.ILocation;

public class Location implements ILocation {

	@Override
	public Float getLatitude() {
		return 42f;
	}

	@Override
	public Float getLongitude() {
		return 42f;
	}

}
