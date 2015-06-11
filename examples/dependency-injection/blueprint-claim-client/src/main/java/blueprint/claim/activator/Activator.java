package blueprint.claim.activator;

import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blueprint.claim.service.ClaimService;
import claim.model.auto.AutoClaim;

public class Activator {
	
	private static final Logger logger = LoggerFactory.getLogger(Activator.class);
	
	private BundleContext bundleContext;
	
	public void start() throws Exception {
		logger.info("Blueprint Bundle starting...");
	}

	public void stop() throws Exception {
		logger.info("Blueprint Bundle stopping...");
	}
	
	public void onBindServiceTestAutoClaims(final ClaimService claimService) {
		if (claimService == null) {
			logger.warn("Claim service is down");
		} else {
			
			logger.info("Test of Auto Claims: " + claimService.getClaimType(new AutoClaim()));
		}
	}
	
	public void onUnbindService(final ClaimService claimService) {
		logger.info("Service unbinding...");
	}

	/**
	 * @param bundleContext the bundleContext to set
	 */
	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

}
