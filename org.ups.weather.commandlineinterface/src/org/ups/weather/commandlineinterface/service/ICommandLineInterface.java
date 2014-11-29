package org.ups.weather.commandlineinterface.service;

import java.util.Map;
import java.util.Observer;

public interface ICommandLineInterface extends Observer {
	public void setWeatherStatus(Map<String, String> weatherStatus);
}
