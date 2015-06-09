package com.redhat.training.osgi.basic.consumer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.basic.provider.service.Greeting;

public class BasicServiceConsumerActivator implements BundleActivator {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BasicServiceConsumerActivator.class);

	@Override
	public void start(BundleContext context) throws Exception {

		LOGGER.info("Starting Basic Consumer Bundle");
		
		ServiceReference<Greeting> greetingServiceReference = context
				.getServiceReference(Greeting.class);
		
		if (greetingServiceReference != null) {

			Greeting greeting = context
					.getService(greetingServiceReference);

			if (greetingServiceReference != null) {
				if (greeting != null) {
					try {

						LOGGER.info("Greeting Service Found");
						LOGGER.info("Language: " + greeting.getLanguage());
						LOGGER.info("Hello Greeting: " + greeting.getHelloGreeting());
						LOGGER.info("Goodbye Greeting: " + greeting.getGoodbyeGreeting());
					
					} finally {
						context.ungetService(greetingServiceReference);
					}
				} else {
					throw new Exception("Greeting Service not found");
				}
			}
		} else {
			throw new Exception("Greeting ServiceReference not found");
		}

	}

	@Override
	public void stop(BundleContext context) throws Exception {

		LOGGER.info("Stopping Basic Consumer Bundle");
		
	}

}
