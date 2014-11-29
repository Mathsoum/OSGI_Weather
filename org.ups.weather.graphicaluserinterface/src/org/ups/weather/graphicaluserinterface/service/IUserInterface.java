package org.ups.weather.graphicaluserinterface.service;

import java.util.Map;
import java.util.Observer;

public interface IUserInterface extends Observer {
	public void setWeatherStatus(Map<String, String> weatherStatus);
}
