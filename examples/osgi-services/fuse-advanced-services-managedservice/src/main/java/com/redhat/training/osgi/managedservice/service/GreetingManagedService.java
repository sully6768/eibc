package com.redhat.training.osgi.managedservice.service;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.managedservice.service.impl.ManagedServiceEnglishGreeting;
import com.redhat.training.osgi.whiteboard.api.Greeting;

public class GreetingManagedService implements ManagedService {
		
	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingManagedService.class);

	private ServiceRegistration<Greeting> greetingServiceRegistration;
	private BundleContext context;
	
	private static final String NAME_PROP = "name";
	
	public GreetingManagedService(BundleContext context) {
		this.context = context;
	}
	
	
	@Override
	public synchronized void updated(Dictionary<String, ?> properties)
			throws ConfigurationException {
		
		final String name;
		
		unregister();
		
		if(properties != null) {
			name = (String) properties.get(NAME_PROP);
			
			if(name == null) {
				throw new ConfigurationException(NAME_PROP, "Name Property is Required");
			}
			else {
				
				Greeting greeting = new ManagedServiceEnglishGreeting(name);
				
				greetingServiceRegistration = context.registerService(Greeting.class, greeting, null);

			}

		}
		else {
			throw new ConfigurationException(null,"No Configuration File Found");
		}
		
		LOGGER.info("Math Managed Service Configuration Loaded Successfully. Name: "+ name);
		
	}
	
	public synchronized void unregister() {
		if(greetingServiceRegistration != null) {
			greetingServiceRegistration.unregister();
			
			greetingServiceRegistration = null;
			
		}
	}

}
