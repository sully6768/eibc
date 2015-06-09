package com.redhat.training.osgi.whiteboard.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.whiteboard.api.Greeting;
import com.redhat.training.osgi.whiteboard.service.impl.SpanishGreeting;

public class WhiteboardSpanishGreetingActivator implements BundleActivator {

	private static final Logger LOGGER = LoggerFactory.getLogger(WhiteboardSpanishGreetingActivator.class);
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Whiteboard Spanish Bundle");
		
		Dictionary<String, String> spanishProperties = new Hashtable<String,String>();
		spanishProperties.put("Language", "Spanish");

		context.registerService(Greeting.class.getName(), new SpanishGreeting(), spanishProperties);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Whiteboard Spanish Bundle");
		
	}

}
