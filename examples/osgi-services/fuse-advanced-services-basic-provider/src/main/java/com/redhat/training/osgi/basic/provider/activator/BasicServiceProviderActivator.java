package com.redhat.training.osgi.basic.provider.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.basic.provider.service.Greeting;
import com.redhat.training.osgi.basic.provider.service.impl.EnglishGreeting;

public class BasicServiceProviderActivator implements BundleActivator {

	private static final Logger LOGGER = LoggerFactory.getLogger(BasicServiceProviderActivator.class);
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Basic Provider Bundle");
			
		context.registerService(Greeting.class.getName(), new EnglishGreeting(), null);
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		LOGGER.info("Stopping Basic Provider Bundle");
		
	}

}
