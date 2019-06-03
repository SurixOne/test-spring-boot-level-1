package coop.tecso.examen.dto;

import java.io.Serializable;

public class PersonaFísicaDto  implements Serializable {

	private static final long serialVersionUID = -8901155893511467206L;
	
	private String cuit;
	private Long id;
	private String name;
	private String surname;
	private String dni;
	
	public String getCuit() {
		return cuit;
	}
	
	public void setCuit(String cuit1) {
		cuit = cuit1;
	}	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname1) {
		this.surname = surname1;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni1) {
		dni = dni1;
	}
}
