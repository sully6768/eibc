package claim.model;

/**
 * Claim model (could be linked to either XSD model via JAX-B 
 * or DB via Hibernate annotations
 * 
 * @author jcohler
 *
 */
public abstract class Claim {

	protected Long value;
	protected String status;
	protected ClaimType type;
	protected String assignedTo;
	
	public abstract Long getValue();
	public abstract void setValue(Long value);
	public abstract String getStatus();
	public abstract void setStatus(String status);
	public abstract ClaimType getType();
	public abstract void setType(ClaimType type);
	public abstract String getAssignedTo();
	public abstract void setAssignedTo(String assignedTo);
	
}
