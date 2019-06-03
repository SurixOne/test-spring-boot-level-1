package coop.tecso.examen.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.PersonaFisicaDto;
import coop.tecso.examen.model.PersonaFisica;
import coop.tecso.examen.repository.PersonaFisicaRepository;

@RestController
@RequestMapping("/personaFisica")
public class PersonaFisicaController {

	@Autowired
	private PersonaFisicaRepository personaFísicaRepository;
	
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
			PersonaFisica p = new PersonaFisica();
			for (PersonaFisica entity : personaFísicaRepository.findAll()) {
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
			for (PersonaFisica entity : personaFísicaRepository.findAll()) {
				if(entity.getCuit().equals(cuit)) {
					existe = true;
				}
			}
			if(!existe) {
				if(name.length()>80 || surname.length()>250) {
					result ="El nombre no puede superar los 80 caracteres y el largo del apellido no puede superar los 250 caracteres, caracteres actuales - nombre: " + name.length() + ", apellido: "+ surname.length();
				}
				else {
					PersonaFisica p = new PersonaFisica();
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
			PersonaFisica p = new PersonaFisica();
			for (PersonaFisica entity : personaFísicaRepository.findAll()) {
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
		public List<PersonaFisicaDto> findAll() {
			
			List<PersonaFisicaDto> result = new ArrayList<>();
			for (PersonaFisica entity : personaFísicaRepository.findAll()) {
				PersonaFisicaDto dto = new PersonaFisicaDto();
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
			for (PersonaFisica entity : personaFísicaRepository.findAll()) {
				if(entity.getCuit().equals(cuit)) {
				result="Id: " + entity.getId() + ", nombre: " + entity.getName() + ", Apellido: " + entity.getSurname() + ", Dni: " + entity.getDni() + ", Cuit: " + entity.getCuit();
				}
			}		
		    return result;
		}
	
}
