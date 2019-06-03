package coop.tecso.examen.model;

import java.util.Date;

import javax.persistence.Entity;


@Entity
public class PersonaJurídica extends AbstractPersistentObject {

	private static final long serialVersionUID = -8901155893511467206L;
	

	private String cuit;
	private String socialReason;
	private Date fundationYear;

	public String getCuit() {
		return cuit;
	}
	
	public void setCuit(String cuit1) {
		this.cuit = cuit1;
	}
	
	public String getSocialReason() {
		return socialReason;
	}
	public void setSocialReason(String socialReason1) {
		socialReason = socialReason1;
	}
	public Date getFundationYear() {
		return fundationYear;
	}
	public void setFundationYear(Date añoF) {
		fundationYear = añoF;
	}
	
}
