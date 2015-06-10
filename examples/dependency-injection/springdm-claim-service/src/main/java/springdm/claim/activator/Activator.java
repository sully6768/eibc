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
	private static final String CONFIG_PID = "SpringDMClaim";
	private ClaimService service;
	
	public void start(final BundleContext context) throws Exception {
		Hashtable<String, Object> properties = new Hashtable<String, Object>();
		properties.put(Constants.SERVICE_PID, CONFIG_PID);
		serviceRegistration = context.registerService(ManagedService.class.getName(), new ConfigUpdater() , properties);
	}

	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
		
	}
	
	
	public ClaimService getService() {
		return service;
	}

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

			
			if (service == null) {
				logger.warn("ClaimService not properly injected");
				service = new ClaimServiceImpl();
			} else {
				logger.info(service.toString());
			}
			String type = (String) config.get("claimType");
			
			if(type.equals(ClaimType.AUTO.toString())) {
				claim = new AutoClaim();
			} else if (type.equals(ClaimType.HOME.toString())) {
				claim = new HomeClaim();
			}
			logger.info("+++++++++++++++++++++++++++++++++++");
			if (claim != null) {
				logger.info(service.getClaimType(claim));
			} else {
				logger.warn("Unable to match property with valid claim type");
			}
			logger.info("+++++++++++++++++++++++++++++++++++");

		}
	}

}
