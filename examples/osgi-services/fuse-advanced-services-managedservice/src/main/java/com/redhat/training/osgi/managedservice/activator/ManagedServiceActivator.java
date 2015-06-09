package com.redhat.training.osgi.managedservice.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.managedservice.service.GreetingManagedService;

public class ManagedServiceActivator implements BundleActivator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagedServiceActivator.class);
			
	@Override
	public void start(BundleContext context) throws Exception {
		
		LOGGER.info("Starting Managed Service");
		
		Dictionary<String,String> props = new Hashtable<String,String>();
		
		props.put(Constants.SERVICE_PID, "com.redhat.training.osgi.managedservice");
		
		GreetingManagedService mathOperationManagedService = new GreetingManagedService(context);
						
		context.registerService(ManagedService.class, mathOperationManagedService, props);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		LOGGER.info("Stopping Managed Service");
	
	}

}
