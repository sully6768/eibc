package com.redhat.training.osgi.servicelistener.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.basic.provider.service.Greeting;
import com.redhat.training.osgi.servicelistener.listener.GreetingServiceListener;

public class ServiceListenerActivator implements BundleActivator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceListenerActivator.class);
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Service Listener");
		
		GreetingServiceListener listener = new GreetingServiceListener(context);
		String filterString = "("+Constants.OBJECTCLASS + "=" + Greeting.class.getName() + ")";
		
		context.addServiceListener(listener, filterString);
		
		ServiceReference<?> references[] = context.getAllServiceReferences(null, filterString);
		
		if(references != null) {

			for(ServiceReference<?> reference : references) {
				listener.serviceChanged(new ServiceEvent(ServiceEvent.REGISTERED, reference));
			}
	
		}
				
	}

	@Override
	public void stop(BundleContext context) throws Exception {

		LOGGER.info("Stopping Service Listener");

	}

}
