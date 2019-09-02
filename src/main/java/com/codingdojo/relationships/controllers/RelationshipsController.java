package com.codingdojo.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.relationships.models.License;
import com.codingdojo.relationships.models.Person;
import com.codingdojo.relationships.services.LicenseService;
import com.codingdojo.relationships.services.PersonService;

@Controller
public class RelationshipsController {
	private final PersonService personService;
	private final LicenseService licenseService;
	
	public RelationshipsController(PersonService personService, LicenseService licenseService) {
		this.personService = personService;
		this.licenseService = licenseService;
	}
	
	// Display NEW Person page route
	@RequestMapping("/persons/new")
	public String newPerson(@ModelAttribute("person")Person person) {
		return "views/addPerson.jsp";
	}
	
	// Save NEW Person data route
	@PostMapping("/addPerson")
	public String addPerson(@Valid @ModelAttribute("person") Person person, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "views/addPerson.jsp";
		} else {
			personService.addPerson(person);
			return "redirect:/persons/new";
		}
	}
	
	// Display NEW License page route
	@RequestMapping("/licenses/new")
	public String newLicense(Model model, @ModelAttribute("license")License license) {
		List<Person> persons = personService.allPersons();
		model.addAttribute("persons", persons);
		return "views/addLicense.jsp";
	}
	
	// Save NEW License data route
	@PostMapping("/addLicense")
	public String addLicense(@Valid @ModelAttribute("license") License license, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "views/addLicense.jsp";
		} else {
			licenseService.addLicense(license);
			return "redirect:/licenses/new";
		}
	}
	
	
	// Display a COMPLETE Person profile with combined License info
	@RequestMapping("/persons/{id}")
	public String showPerson(@PathVariable("id") Long id, Model model) {
		Person person = personService.findPerson(id);
		License license = licenseService.findLicense(id);
		model.addAttribute("person", person);
		model.addAttribute("license", license);
		return "views/showProfile.jsp";
	}
	
	
}
