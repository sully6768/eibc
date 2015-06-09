package com.redhat.training.osgi.filtering.breakfix.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.filtering.breakfix.service.Greeting;
import com.redhat.training.osgi.filtering.breakfix.service.impl.EnglishGreeting;

public class FilteringBreakfixActivator implements BundleActivator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FilteringBreakfixActivator.class);

	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Filtering Breakfix B");
		
		Dictionary<String, String> englishProperties = new Hashtable<String,String>();
		englishProperties.put("Language", "English");

		context.registerService(Greeting.class.getName(), new EnglishGreeting(), englishProperties);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		LOGGER.info("Stopping Filtering Breakfix B");
		
	}

}
