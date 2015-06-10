package springdm.claim.service.impl;

import springdm.claim.service.ClaimService;
import claim.model.Claim;

public class ClaimServiceImpl implements ClaimService {

	public Long getClaimValue(Claim claim) {
		return claim.getValue();
		
	}

	public String getClaimStatus(Claim claim) {
		return claim.getStatus().toString();	
		
	}

	public String getClaimType(Claim claim) {
		return claim.getType().toString();
		
	}

	public String getClaimAssigedTo(Claim claim) {
		return claim.getAssignedTo();
		
	}

	@Override
	public void setClaimStatus(Claim claim, String status) {
		claim.setStatus(status);
		
	}

}
