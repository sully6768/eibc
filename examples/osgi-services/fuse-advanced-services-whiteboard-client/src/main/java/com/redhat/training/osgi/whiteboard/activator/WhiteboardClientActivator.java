package com.redhat.training.osgi.whiteboard.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.whiteboard.api.Greeting;

public class WhiteboardClientActivator implements BundleActivator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WhiteboardClientActivator.class);
	private ServiceTracker<Greeting, Greeting> serviceTracker;
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Whiteboard Client");
		
		String filterString = "(&("+Constants.OBJECTCLASS + "=" + Greeting.class.getName() + ")" 
				+ "(Language=English))";	
		
		Filter filter = context.createFilter(filterString);
		
		serviceTracker = new ServiceTracker<Greeting,Greeting>(context, filter, null);
		
		serviceTracker.open();
						
		Greeting greeting = serviceTracker.getService();
		
		if(greeting != null) {
				
			LOGGER.info("Language: " +greeting.getLanguage());
			LOGGER.info("Hello: " + greeting.getHelloGreeting());
			LOGGER.info("Goodbye: " + greeting.getGoodbyeGreeting());
					
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		LOGGER.info("Stopping Whiteboard Client");
		
		serviceTracker.close();
		
	}

}
