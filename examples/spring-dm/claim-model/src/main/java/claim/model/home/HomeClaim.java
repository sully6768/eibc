package claim.model.home;

import claim.model.Claim;
import claim.model.ClaimType;

public class HomeClaim extends Claim {
	
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
		return ClaimType.HOME;
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
