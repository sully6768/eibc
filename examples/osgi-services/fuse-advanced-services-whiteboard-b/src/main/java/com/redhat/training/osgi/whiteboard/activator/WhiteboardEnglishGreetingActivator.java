package com.redhat.training.osgi.whiteboard.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.whiteboard.api.Greeting;
import com.redhat.training.osgi.whiteboard.service.impl.EnglishGreeting;

public class WhiteboardEnglishGreetingActivator implements BundleActivator {

	private static final Logger LOGGER = LoggerFactory.getLogger(WhiteboardEnglishGreetingActivator.class);
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Whiteboard English Bundle");
		
		Dictionary<String, String> englishProperties = new Hashtable<String,String>();
		englishProperties.put("Language", "English");

		context.registerService(Greeting.class.getName(), new EnglishGreeting(), englishProperties);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		LOGGER.info("Stopping Whiteboard English Bundle");
		
	}

}
