package coop.tecso.examen.dto;

import java.io.Serializable;
import java.util.Date;

public class PersonaJuridicaDto  implements Serializable {

	private static final long serialVersionUID = -8901155893511467206L;
	
	private String cuit;
	private String socialReason;
	private Date fundationYear;
	private Long id;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getCuit() {
		return cuit;
	}
	
	public void setCuit(String cuit1) {
		cuit = cuit1;
	}
	
	public Date getFundationYear() {
		return fundationYear;
	}
	
	public void setFundationYear(Date fundationYear1) {
		fundationYear = fundationYear1;
	}
	
	public String getSocialReason() {
		return socialReason;
	}
	public void setSocialReason(String socialReason1) {
		socialReason = socialReason1;
	}
	
}
