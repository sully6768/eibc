package com.redhat.training.osgi.filtering.consumer.activator;


import java.util.Collection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.filtering.provider.service.Greeting;

public class ServiceFilteringConsumerActivator implements BundleActivator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFilteringConsumerActivator.class);
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Service Filter Consumer");
		
		String filterString = "(&("+Constants.OBJECTCLASS + "=" + Greeting.class.getName() + ")" 
				+ "(Language=Spanish))";
						
		Collection<ServiceReference<Greeting>> serviceReferences = context.getServiceReferences(Greeting.class, filterString);
		
		for(ServiceReference<Greeting> greetingReference : serviceReferences) {

			if(greetingReference != null) {
				Greeting greeting = context.getService(greetingReference);

				if(greeting != null) {
				
					try {
						LOGGER.info("Language: " + greeting.getLanguage());
						LOGGER.info("Hello Greeting: " + greeting.getHelloGreeting());
						LOGGER.info("Goodbye Greeting: " + greeting.getGoodbyeGreeting());
					}
					finally {
						context.ungetService(greetingReference);
					}
					
				}
			}
		}

		
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		LOGGER.info("Stopping Service Filter Consumer");
		
	}

}
