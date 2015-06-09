package com.redhat.training.osgi.service.tracker.customizer;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.basic.provider.service.Greeting;

public class GreetingServiceTrackerCustomizer implements ServiceTrackerCustomizer<Greeting, Greeting> {

	private final BundleContext bundleContext;
	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingServiceTrackerCustomizer.class);
	
	public GreetingServiceTrackerCustomizer(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
	
	@Override
	public Greeting addingService(ServiceReference<Greeting> serviceReference) {
		
		
		Greeting greeting = bundleContext.getService(serviceReference);
		
		LOGGER.info("Service Added: " +greeting.getClass());
		LOGGER.info("Language: " + greeting.getLanguage());
		LOGGER.info("Hello Greeting: " + greeting.getHelloGreeting());
		LOGGER.info("Goodbye Greeting: " + greeting.getGoodbyeGreeting());

		
		return greeting;
	}

	@Override
	public void modifiedService(ServiceReference<Greeting> greeetingServiceReference,
			Greeting greetingService) {
		
		LOGGER.info("Service Modified: " +greetingService.getClass());
		
		
	}

	@Override
	public void removedService(ServiceReference<Greeting> greetingServiceReference,
			Greeting greetingService) {
		
		LOGGER.info("Service Removed: " +greetingService.getClass());
		
	}

}
