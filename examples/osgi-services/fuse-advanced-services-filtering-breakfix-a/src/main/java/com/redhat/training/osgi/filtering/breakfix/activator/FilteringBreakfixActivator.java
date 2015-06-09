package com.redhat.training.osgi.filtering.breakfix.activator;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.filtering.breakfix.service.Greeting;
import com.redhat.training.osgi.filtering.breakfix.service.impl.SpanishGreeting;

public class FilteringBreakfixActivator implements BundleActivator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FilteringBreakfixActivator.class);
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Filtering Breakfix A");
		
		Dictionary<String, String> spanishProperties = new Hashtable<String,String>();
		spanishProperties.put("Language", "Spanish");

		context.registerService(Greeting.class.getName(), new SpanishGreeting(), spanishProperties);

		String filterString = "(&("+Constants.OBJECTCLASS + "=" + Greeting.class.getName() + ")" 
				+ "(Language=English))";		
		
		Collection<ServiceReference<Greeting>> serviceReferences = context.getServiceReferences(Greeting.class, filterString);
		
		for(ServiceReference<Greeting> greetingReference : serviceReferences) {
			
			if(greetingReference != null) {
				Greeting greeting = context.getService(greetingReference);

				if(greeting != null) {
				
					LOGGER.info("Language: " +greeting.getLanguage());
					LOGGER.info("Hello: " + greeting.getHelloGreeting());
					LOGGER.info("Goodbye: " + greeting.getGoodbyeGreeting());
					
				}

				context.ungetService(greetingReference);

			}
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		LOGGER.info("Stopping Filtering Breakfix A");
		
	}

}
