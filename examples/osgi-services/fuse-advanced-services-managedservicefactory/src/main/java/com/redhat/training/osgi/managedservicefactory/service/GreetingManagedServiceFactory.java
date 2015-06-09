package com.redhat.training.osgi.managedservicefactory.service;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.osgi.basic.provider.service.Greeting;
import com.redhat.training.osgi.managedservicefactory.service.impl.ManagedServiceFactoryFrenchGreeting;

public class GreetingManagedServiceFactory implements ManagedServiceFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingManagedServiceFactory.class);
	
	private Map<String,ServiceRegistration<Greeting>> services = new HashMap<String,ServiceRegistration<Greeting>>();
	
	private BundleContext bundleContext;
	
	private static final String NAME_PROP = "name";

	
	public GreetingManagedServiceFactory(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
	
	@Override
	public String getName() {
		
		return "com.redhat.training.osgi.managedservicefactory";
	}

	@Override
	public void updated(String pid, Dictionary<String, ?> properties)
			throws ConfigurationException {
		
		final String name;

		
		unregister(pid);
		
		if(properties != null) {
			name = (String) properties.get(NAME_PROP);
			
			if(name == null) {
				throw new ConfigurationException(NAME_PROP, "Name Property is Required");
			}
			else {
				ServiceRegistration<Greeting> mathServiceOperationRegistration = bundleContext.registerService(Greeting.class, new ManagedServiceFactoryFrenchGreeting(name), properties);
				services.put(pid, mathServiceOperationRegistration);
			}
		}
		else {
			throw new ConfigurationException(null,"No Configuration File Found");
		}
		
		LOGGER.info("Greeting Managed Service Factory Configuration Loaded Successfully. Name: "+ name);
				
		
	}

	@Override
	public void deleted(String pid) {
		unregister(pid);
	}
	
	private void unregister(String pid) {
		ServiceRegistration<Greeting> reg = services.get(pid);
		
		if(reg != null) {
			reg.unregister();
			services.remove(pid);
		}
	}
	
	public void unregister() {
		
		for(Entry<String,ServiceRegistration<Greeting>> entry : services.entrySet()) {			
			entry.getValue().unregister();
		}
		
		services.clear();
		
	}
}
