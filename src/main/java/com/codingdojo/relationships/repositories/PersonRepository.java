package com.codingdojo.relationships.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.relationships.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{

	// this method retrieves all the persons from the database copy
	List<Person> findAll();
	
	// this method retrieves ONLY the persons from the database copy without related License records
	List<Person> findByLicenseIsNull();
	
}
