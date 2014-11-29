package org.ups.weather.location.service.impl;

import org.ups.weather.location.service.ILocation;

public abstract class AbstractLocation implements ILocation {

	@Override
	public abstract Float getLatitude();

	@Override
	public abstract Float getLongitude();

	@Override
	public String toString() {
		return "(" + getLatitude() + " -- " + getLongitude() + ")";
	}

}
