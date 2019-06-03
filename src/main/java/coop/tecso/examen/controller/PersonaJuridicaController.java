package coop.tecso.examen.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.PersonaJuridicaDto;
import coop.tecso.examen.model.PersonaJuridica;
import coop.tecso.examen.repository.PersonaJuridicaRepository;

@RestController
@RequestMapping("/personaJuridica")
public class PersonaJuridicaController {

	@Autowired
	private PersonaJuridicaRepository personaJurídicaRepository;
	
	//Delete all the legal persons 
	@GetMapping("/deleteAll")
	public String deleteAll() {
		personaJurídicaRepository.deleteAll();
		return new String("Se han eliminado todos los registros de personas juridicas con exito.");
	}
		
	//Delete a legal person by cuit 
	@GetMapping("/deleteByCuit")
	public String deleteByCuit(@RequestParam String cuit) {
		String result = "";
		Boolean existe = false;
		PersonaJuridica p = new PersonaJuridica();
		for (PersonaJuridica entity : personaJurídicaRepository.findAll()) {
			if(entity.getCuit().equals(cuit)) {
				p = entity;
				existe = true;
			}
		}
		if(!existe) {
			result = "No se puede eliminar la persona juridica debido a que esta no existe.";
		}else {
			personaJurídicaRepository.delete(p);
			result = "Persona juridica eliminada con exito";
		}		
		return result;
	}
	
	//Create a new legal person
	@GetMapping("/add")
	public String add( @RequestParam String socialReason, @RequestParam String fundationYear, @RequestParam String cuit) throws Exception {
		String result = "";
		Boolean existe = false;
		for (PersonaJuridica entity : personaJurídicaRepository.findAll()) {
			if(entity.getCuit().equals(cuit)) {
				existe = true;
			}
		}
		if(!existe) {
			if(socialReason.length()<=100) {
				PersonaJuridica p = new PersonaJuridica();
				p.setFundationYear(new SimpleDateFormat("yyyy").parse(fundationYear));
				p.setCuit(cuit);
				p.setSocialReason(socialReason);
				personaJurídicaRepository.save(p);
				result = "La persona juridica de cuit " + cuit + " ha sido agregada exitosamente";
			}
			else {
				result ="La razon social no puede superar los 100 caracteres, caracteres actuales: " + socialReason.length();
			}
		}else {
			result="La persona juridica de cuit " + cuit + " ya existe, intente con otro cuit.";
		}
		return result;
	}
	
	// Modify an existing legal person by cuit
	@GetMapping("/updateByCuit")
	public String updateByCuit( @RequestParam String socialReason, @RequestParam String fundationYear, @RequestParam String cuit) throws Exception {
		String result = "";
		Boolean existe = false;
		PersonaJuridica p = new PersonaJuridica();
		for (PersonaJuridica entity : personaJurídicaRepository.findAll()) {
			if(entity.getCuit().equals(cuit)) {
				existe = true;
				p.setId(entity.getId());
			}
		}
		if(!existe) {
			result="La persona juridica a modificar de cuit " + cuit + " no existe, intente con otro cuit.";
		}else {
			if(socialReason.length()>100) {
				result ="La razon social no puede superar los 100 caracteres, caracteres actuales: " + socialReason.length();
			}else {
				p.setFundationYear(new SimpleDateFormat("yyyy").parse(fundationYear));
				p.setCuit(cuit);
				p.setSocialReason(socialReason);
				personaJurídicaRepository.save(p);
				result = "La persona juridica de cuit " + cuit + " ha sido modificada exitosamente";
			}
		}
		return result;
	}
	// Get All the legal persons
	@GetMapping("/findAll")
	public List<PersonaJuridicaDto> findAll() {
		
		List<PersonaJuridicaDto> result = new ArrayList<>();
		for (PersonaJuridica entity : personaJurídicaRepository.findAll()) {
			PersonaJuridicaDto dto = new PersonaJuridicaDto();
			dto.setId(entity.getId());
			dto.setFundationYear(entity.getFundationYear());
			dto.setSocialReason(entity.getSocialReason());
			dto.setCuit(entity.getCuit());
			
			result.add(dto);
		}		
	    return result;
	}
	
	// Get a legal person by CUIT
	@GetMapping("/findByCuit")
	public String findByCuit(@RequestParam String cuit) {
		
		String result = "La persona juridica de cuit " + cuit + " no existe.";
		for (PersonaJuridica entity : personaJurídicaRepository.findAll()) {
			if(entity.getCuit().equals(cuit)) {
			result="Id: " + entity.getId() + ", fundacion: " + entity.getFundationYear() + ", razon social: " + entity.getSocialReason() + ", Cuit: " + entity.getCuit();
			}
		}		
	    return result;
	}
	
}
