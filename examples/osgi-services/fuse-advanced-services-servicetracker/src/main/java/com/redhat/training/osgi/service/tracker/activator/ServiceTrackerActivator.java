package com.redhat.training.osgi.service.tracker.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.basic.provider.service.Greeting;
import com.redhat.training.osgi.service.tracker.customizer.GreetingServiceTrackerCustomizer;

public class ServiceTrackerActivator implements BundleActivator {

	private ServiceTracker<Greeting, Greeting> serviceTracker;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTrackerActivator.class);
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Greeting Service Tracker");
		
		GreetingServiceTrackerCustomizer customizer = new GreetingServiceTrackerCustomizer(context);
				
		serviceTracker = new ServiceTracker<Greeting, Greeting>(context, Greeting.class.getName(), customizer);
			
		serviceTracker.open();			
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		LOGGER.info("Stopping Greeting Service Tracker");
		
		serviceTracker.close();
	}

}
