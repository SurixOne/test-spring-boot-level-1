package coop.tecso.examen.model;

import javax.persistence.Entity;

@Entity
public class PersonaFísica extends AbstractPersistentObject {

	private static final long serialVersionUID = -8901155893511467206L;
	
	private String cuit;
	private String name;
	private String surname;
	private String dni;

	public String getCuit() {
		return cuit;
	}
	
	public void setCuit(String cuit1) {
		cuit = cuit1;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name1) {
		name = name1;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname1) {
		surname = surname1;
	}

	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni1) {
		dni = dni1;
	}
}
