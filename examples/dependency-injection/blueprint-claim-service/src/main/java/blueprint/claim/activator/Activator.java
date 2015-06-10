package blueprint.claim.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blueprint.claim.service.ClaimService;
import claim.model.auto.AutoClaim;

public class Activator implements BundleActivator {
	
	private static final Logger logger = LoggerFactory.getLogger(Activator.class);
	
	public void start(final BundleContext context) throws Exception {
		logger.info("Blueprint Bundle starting...");
	}

	public void stop(BundleContext context) throws Exception {
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

}
