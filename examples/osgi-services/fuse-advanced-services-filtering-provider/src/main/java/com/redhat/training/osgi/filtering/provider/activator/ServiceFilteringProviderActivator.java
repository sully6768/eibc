package com.redhat.training.osgi.filtering.provider.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.filtering.provider.service.Greeting;
import com.redhat.training.osgi.filtering.provider.service.impl.EnglishGreeting;
import com.redhat.training.osgi.filtering.provider.service.impl.SpanishGreeting;

public class ServiceFilteringProviderActivator implements BundleActivator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFilteringProviderActivator.class);
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Service Filter Provider");
				
		Dictionary<String, String> englishProperties = new Hashtable<String,String>();
		englishProperties.put("Language", "English");

		context.registerService(Greeting.class.getName(), new EnglishGreeting(), englishProperties);
		
		Dictionary<String, String> spanishProperties = new Hashtable<String,String>();
		spanishProperties.put("Language", "Spanish");
		
		context.registerService(Greeting.class.getName(), new SpanishGreeting(), spanishProperties);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		LOGGER.info("Stopping Service Filter Proivder");
		
	}

}
