package org.ups.weather.graphicaluserinterface.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.ups.weather.graphicaluserinterface.service.IGraphicalUserInterface;

public class GraphicalUserInterface implements IGraphicalUserInterface, Observer {

	private Display display;
	private Shell shell;
	
	private Map<Label, Label> ui_weatherStatus;
	private Map<String, String> weatherStatus;
	
	public GraphicalUserInterface() {
		display = new Display();
		shell = new Shell(display);

		shell.pack();
		shell.open();
		
		ui_weatherStatus = new HashMap<>();
		
		printBundleStatus();
		
		// run the event loop as long as the window is open
		while (!shell.isDisposed()) {
		    // read the next OS event queue and transfer it to a SWT event 
		  if (!display.readAndDispatch())
		   {
		  // if there are currently no other OS event to process
		  // sleep until the next OS event is available 
		    display.sleep();
		   }
		}

		// disposes all associated windows and their components
		display.dispose();		
	}

	@Override
	public void update(Observable observable, Object paramFromObservable) {
		if(paramFromObservable instanceof Map<?, ?>) {
			@SuppressWarnings("unchecked")
			Map<String, String> weatherStatus = (Map<String, String>) paramFromObservable;
			setWeatherStatus(weatherStatus);
		}
	}

	@SuppressWarnings("unused")
	private void updateView() {
		for(String location : weatherStatus.keySet()) {
			boolean exists = false;
			Label locationLabel = null;
			for(Label label : ui_weatherStatus.keySet()) {
				if(label.getText().equals(location.toString())) {
					exists = true;
					locationLabel = label;
					break;
				}
			}
			
			if (!exists) {
				Label newLocation = new Label(shell, SWT.NONE);
				newLocation.setText("");
				Label newWeather = new Label(shell, SWT.NONE);
				newLocation.setText("");

				locationLabel = newLocation;
				ui_weatherStatus.put(newLocation, newWeather);
			}
			
			if(!weatherStatus.get(location).toString().equals(ui_weatherStatus.get(locationLabel).getText())) {
				locationLabel.setText(location.toString());
				ui_weatherStatus.get(locationLabel).setText(weatherStatus.get(location));
			}
		}
		
		int count = 0;
		for(Label label : ui_weatherStatus.keySet()) {
			Label weatherLabel = ui_weatherStatus.get(label);
			int marginTop = (100/ui_weatherStatus.size()) * count;
			
			FormData locFormData = new FormData();
			locFormData.top = new FormAttachment(marginTop,0);
			locFormData.left = new FormAttachment(0,0);
			label.setLayoutData(locFormData);

			FormData weatherFormData = new FormData();
			weatherFormData.top = new FormAttachment(marginTop,0);
			weatherFormData.left = new FormAttachment(50,0);
			weatherLabel.setLayoutData(weatherFormData);
			
			label.pack();
			weatherLabel.pack();
			count++;
		}
		
		display.update();
		shell.update();
	}

	protected void printBundleStatus() {
		System.err.println("GUI bundle broken !");
		System.err.println("This bundle has not been completed.");
		System.err.println("The main reason why this bundle has been kept"
				+ "in the app is just to show that I've tried SWT. "
				+ "And well... I failed... But the bundle's still there !");
	}
	
	@Override
	public void setWeatherStatus(Map<String, String> _weatherStatus) {
		weatherStatus = _weatherStatus;
//		updateView();
	}
}
