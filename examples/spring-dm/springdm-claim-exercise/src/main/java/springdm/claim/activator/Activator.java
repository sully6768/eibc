package springdm.claim.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import springdm.claim.service.ClaimService;
import springdm.claim.service.impl.ClaimServiceImpl;
import claim.model.Claim;
import claim.model.ClaimType;
import claim.model.auto.AutoClaim;
import claim.model.home.HomeClaim;

public class Activator implements BundleActivator {
	
	private static final Logger logger = LoggerFactory.getLogger(Activator.class);
	
	private ServiceRegistration serviceRegistration;
	
	/*
	 * TODO - SpringDM 1.a
	 * Configure the static string below with the configuration id matching 
	 * the accompanying cfg file in FUSE_HOME/etc
	 */
	private static final String CONFIG_PID = "";
	private ClaimService service;
	
	public void start(final BundleContext context) throws Exception {
		/* TODO - SpringDM 1.b
		 * 
		 * Write the start of the bundle to register a configuration with 
		 * the bundle
		 */
	}

	public void stop(BundleContext context) throws Exception {
		// On stop of the bundle, unregister the service registration
		serviceRegistration.unregister();
		
	}
	
	public ClaimService getService() {
		return service;
	}

	/**
	 * Setter used to inject the Claim service used for this exercise
	 * @param service
	 */
	public void setService(ClaimService service) {
		this.service = service;
	}


	private final class ConfigUpdater implements ManagedService {

		private Claim claim;
		
		@SuppressWarnings("rawtypes")
		@Override
		public void updated(Dictionary config) throws ConfigurationException {
			if (config == null) {
				return;
			}
			
			/* TODO SpringDM 1.c
			 * 
			 * Using the "claimType" property in the configuration admin,
			 * Call an appropriate service routine to create a claim and log
			 * the type of claim created.
			 * 
			 */

		}
	}

}
