package coop.tecso.examen.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.PersonaFísicaDto;
import coop.tecso.examen.model.PersonaFísica;
import coop.tecso.examen.repository.PersonaFísicaRepository;

@RestController
@RequestMapping("/personaFisica")
public class PersonaFísicaController {

	@Autowired
	private PersonaFísicaRepository personaFísicaRepository;
	
	//Delete all the physical persons 
		@GetMapping("/deleteAll")
		public String deleteAll() {
			personaFísicaRepository.deleteAll();
			return new String("Se han eliminado todos los registros de personas fisicas con exito.");
		}
			
		//Delete a physical person by cuit 
		@GetMapping("/deleteByCuit")
		public String deleteByCuit(@RequestParam String cuit) {
			String result = "";
			Boolean existe = false;
			PersonaFísica p = new PersonaFísica();
			for (PersonaFísica entity : personaFísicaRepository.findAll()) {
				if(entity.getCuit().equals(cuit)) {
					p = entity;
					existe = true;
				}
			}
			if(!existe) {
				result = "No se puede eliminar la persona fisica debido a que esta no existe.";
			}else {
				personaFísicaRepository.delete(p);
				result = "Persona fisica eliminada con exito";
			}		
			return result;
		}
		
		//Create a new physical person
		@GetMapping("/add")
		public String add( @RequestParam String dni, @RequestParam String name, @RequestParam String surname, @RequestParam String cuit) throws Exception {
			String result = "";
			Boolean existe = false;
			for (PersonaFísica entity : personaFísicaRepository.findAll()) {
				if(entity.getCuit().equals(cuit)) {
					existe = true;
				}
			}
			if(!existe) {
				if(name.length()>80 || surname.length()>250) {
					result ="El nombre no puede superar los 80 caracteres y el largo del apellido no puede superar los 250 caracteres, caracteres actuales - nombre: " + name.length() + ", apellido: "+ surname.length();
				}
				else {
					PersonaFísica p = new PersonaFísica();
					p.setDni(dni);
					p.setCuit(cuit);
					p.setName(name);
					p.setSurname(surname);
					personaFísicaRepository.save(p);
					result = "La persona fisica de cuit " + cuit + " ha sido agregada exitosamente";
				}
			}else {
				result="La persona fisica de cuit " + cuit + " ya existe, intente con otro cuit.";
			}
			return result;
		}
		
		// Modify an existing physical person by cuit
		@GetMapping("/updateByCuit")
		public String updateByCuit( @RequestParam String dni, @RequestParam String name, @RequestParam String surname, @RequestParam String cuit) throws Exception {
			String result = "";
			Boolean existe = false;
			PersonaFísica p = new PersonaFísica();
			for (PersonaFísica entity : personaFísicaRepository.findAll()) {
				if(entity.getCuit().equals(cuit)) {
					existe = true;
					p.setId(entity.getId());
				}
			}
			if(!existe) {
				result = "La persona fisica a modificar de cuit " + cuit + " no existe, intente con otro cuit.";
			}else {
				if(name.length()>80|| surname.length() > 250) {
					result ="El nombre no puede superar los 80 caracteres y el largo del apellido no debe superar los 250 caracteres, caracteres actuales - nombre: " + name.length() + ", apellido: "+ surname.length();
				}else {
					p.setDni(dni);
					p.setCuit(cuit);
					p.setName(name);
					p.setSurname(surname);
					personaFísicaRepository.save(p);
					result = "La persona fisica de cuit " + cuit + " ha sido modificada exitosamente";
				}
			}
			return result;
		}
		// Get All physical persons
		@GetMapping("/findAll")
		public List<PersonaFísicaDto> findAll() {
			
			List<PersonaFísicaDto> result = new ArrayList<>();
			for (PersonaFísica entity : personaFísicaRepository.findAll()) {
				PersonaFísicaDto dto = new PersonaFísicaDto();
				dto.setId(entity.getId());
				dto.setDni(entity.getDni());
				dto.setName(entity.getName());
				dto.setSurname(entity.getSurname());
				dto.setCuit(entity.getCuit());
				
				result.add(dto);
			}		
		    return result;
		}
		
		// Get a physical person by CUIT
		@GetMapping("/findByCuit")
		public String findByCuit(@RequestParam String cuit) {
			
			String result = "La persona fisica de cuit " + cuit + " no existe.";
			for (PersonaFísica entity : personaFísicaRepository.findAll()) {
				if(entity.getCuit().equals(cuit)) {
				result="Id: " + entity.getId() + ", nombre: " + entity.getName() + ", Apellido: " + entity.getSurname() + ", Dni: " + entity.getDni() + ", Cuit: " + entity.getCuit();
				}
			}		
		    return result;
		}
	
}
