package claim.model.auto;

import claim.model.Claim;
import claim.model.ClaimType;

public class AutoClaim extends Claim {
	
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ClaimType getType() {
		return ClaimType.AUTO;
	}
	public void setType(ClaimType type) {
		this.type = type;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	
}
