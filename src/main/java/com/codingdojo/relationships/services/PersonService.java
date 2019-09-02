package com.codingdojo.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.relationships.models.Person;
import com.codingdojo.relationships.repositories.PersonRepository;

@Service
public class PersonService {
	private final PersonRepository personRepository;
	
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	// get all persons
	public List<Person> allPersons(){
		return personRepository.findAll();
	}
	
	// get one person by id
	public Person findPerson(Long id) {
		Optional<Person> optionalPerson = personRepository.findById(id);
		if(optionalPerson.isPresent()) {
			return optionalPerson.get();
		} else {
			return null;
		}
	}
	
	// add a person
	public Person addPerson(Person p) {
		return personRepository.save(p);
	}
	
	// delete a person
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
	
	
}
