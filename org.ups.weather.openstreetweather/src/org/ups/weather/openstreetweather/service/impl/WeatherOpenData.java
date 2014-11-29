package org.ups.weather.openstreetweather.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.ups.weather.location.service.ILocation;
import org.ups.weather.openstreetweather.service.IWeatherOpenData;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//TODO Don't forget to rename classes relative to open weather data to keep consistency !
public class WeatherOpenData extends Observable implements IWeatherOpenData {
	
	private String currentWeather;
	private ILocation relativeLocation = new ILocation() {
		
		@Override
		public Float getLongitude() {
			return 2.3508f;
		}
		
		@Override
		public Float getLatitude() {
			return 48.8567f;
		}
	};
	
	public static String WEATHER_API_URL = new String("http://api.openweathermap.org/data/2.5/weather");
	
	public WeatherOpenData() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				updateWeather();
				setChanged();
				notifyObservers(currentWeather);
			}
		}, 0, 3000);
	}

	protected void updateWeather() {
		String xmlData = getXmlWeather();
		currentWeather = extractWeatherFromXml(xmlData);
	}

	private String extractWeatherFromXml(String xmlData) {
		String weatherData = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlData.getBytes());
			Document document = dBuilder.parse(byteArrayInputStream);
			
			NodeList nodeList = document.getElementsByTagName("weather");
			weatherData = nodeList.item(0).getAttributes().getNamedItem("value").getTextContent();
			weatherData = weatherData.substring(0, 1).toUpperCase() + weatherData.substring(1);
		} catch (Exception e) {
			weatherData = "Unkown";
		}
		
		return weatherData;
	}

	@Override
	public String getXmlWeather() {
		return execute(WEATHER_API_URL);
	}
	
	private String execute(String targetURL) {
		URL url;
		HttpURLConnection connection = null;
		
		try {
		  //Create connection
			StringBuilder urlBuilder = new StringBuilder();
			urlBuilder.append(targetURL);
			urlBuilder.append("?lat=" + relativeLocation.getLatitude());
			urlBuilder.append("&lon=" + relativeLocation.getLongitude());
			urlBuilder.append("&mode=xml");
			
			url = new URL(urlBuilder.toString());
			connection = (HttpURLConnection) url.openConnection();
		  	connection.setRequestMethod("GET");
		  
		  	connection.setDoOutput(true);
		  	connection.setDoInput(true);
		
		  	//Send request
		  	DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
		  	wr.flush ();
		  	wr.close ();
		
		  	//Get Response
		  	InputStream is = connection.getInputStream();
		  	BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		  	String line;
		  	StringBuffer response = new StringBuffer(); 
		  	while((line = rd.readLine()) != null) {
			  response.append(line);
		  	}
		  	rd.close();
		  	return response.toString();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		  	return null;
		
		} finally {
		
			if(connection != null) {
				connection.disconnect();
			}
		}
	}

	@Override
	public ILocation getLocation() {
		return relativeLocation;
	}

	@Override
	public void setLocation(ILocation location) {
		relativeLocation = location;
	}

}
