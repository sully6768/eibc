package com.redhat.training.osgi.managedservicefactory.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ManagedServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.managedservicefactory.service.GreetingManagedServiceFactory;

public class ManagedServiceFactoryActivator implements BundleActivator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagedServiceFactoryActivator.class);
	
	private ServiceRegistration<?> mathOperationServiceReference;
	
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Managed ServiceFactory");
		
		Dictionary<String,String> props = new Hashtable<String,String>();
		
		props.put(Constants.SERVICE_PID, "com.redhat.training.osgi.managedservicefactory");
		
		GreetingManagedServiceFactory mathOperationManagedServiceFactory = new GreetingManagedServiceFactory(context);

						
		mathOperationServiceReference = context.registerService(ManagedServiceFactory.class, mathOperationManagedServiceFactory, props);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		LOGGER.info("Stopping Managed ServiceFactory");
		
		if(mathOperationServiceReference != null) {
			
			GreetingManagedServiceFactory mathOperationManagedServiceFactory = (GreetingManagedServiceFactory) context.getService(mathOperationServiceReference.getReference());
			
			if(mathOperationManagedServiceFactory != null) {
				mathOperationManagedServiceFactory.unregister();
			}
			
			mathOperationServiceReference.unregister();
			
			mathOperationServiceReference = null;
			
		}
		
		
		
	}

}
