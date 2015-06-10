package springdm.claim.service;

import claim.model.Claim;

public interface ClaimService {
	
	public Long getClaimValue(Claim claim);
	
	public String getClaimStatus(Claim claim);
	
	public String getClaimType(Claim claim);
	
	public String getClaimAssigedTo(Claim claim);
	
	public void setClaimStatus(Claim claim, String status);
	
}
