package blueprint.claim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import claim.model.Claim;
import claim.model.ClaimType;
import claim.model.auto.AutoClaim;
import claim.model.home.HomeClaim;
import blueprint.claim.service.ClaimService;

public class ClaimServiceImpl implements ClaimService {
	
	private String claimType;
	private Claim claim;
	
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	private static final Logger logger = LoggerFactory.getLogger(ClaimServiceImpl.class);

	public Long getClaimValue(Claim claim) {
		return claim.getValue();
		
	}

	public String getClaimStatus(Claim claim) {
		return claim.getStatus().toString();	
		
	}

	public String getClaimType(Claim claim) {
		return claim.getType().toString();
		
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public String getClaimType() {
		return claimType;
	}

	public String getClaimAssigedTo(Claim claim) {
		return claim.getAssignedTo();
		
	}

	
	public void createClaimAndPrint() {
		logger.info("Creating Claim of type " + claimType);
		claim = null;
		if (claimType.equals(ClaimType.AUTO.toString())) {
			claim = new AutoClaim();
			logger.info("Created Claim of type " + claimType);
		} else if (claimType.equals(ClaimType.HOME.toString())) {
			claim = new HomeClaim();
			logger.info("Created Claim of type " + claimType);
		} else {
			logger.warn("Unable to match property with valid claim type");
		}
		
	}


}
