package com.redhat.training.osgi.servicelistener.listener;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.basic.provider.service.Greeting;

public class GreetingServiceListener implements ServiceListener {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GreetingServiceListener.class);
	
	private BundleContext bundleContext;
	
	public GreetingServiceListener(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
	

	@Override
	public void serviceChanged(ServiceEvent event) {

		ServiceReference<?> greetingServiceReference = event.getServiceReference();			
		Greeting greeting = (Greeting) bundleContext.getService(greetingServiceReference);
		
		switch (event.getType()) {
		case ServiceEvent.REGISTERED:						
			LOGGER.info("Greeting Service Found: " + greeting.getClass());
			LOGGER.info("Language: " + greeting.getLanguage());
			LOGGER.info("Hello Greeting: " + greeting.getHelloGreeting());
			LOGGER.info("Goodbye Greeting: " + greeting.getGoodbyeGreeting());	
			break;
		case ServiceEvent.MODIFIED:
			LOGGER.info("Greeting Service Modified: " + greeting.getClass());
			break;
		case ServiceEvent.UNREGISTERING:
			LOGGER.info("Greeting Service Unregistered: " + greeting.getClass());
			break;
		}

	}

}
