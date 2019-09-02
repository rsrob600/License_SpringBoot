package com.codingdojo.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.relationships.models.License;
import com.codingdojo.relationships.repositories.LicenseRepository;

@Service
public class LicenseService {
	private final LicenseRepository licenseRepository;
	
	// System generated number to be incremented (static class specific)
	private static String sysNumber = "000000";
	
	public LicenseService(LicenseRepository licenseRepository) {
		this.licenseRepository = licenseRepository;
	}
	
	// get all licenses
	public List<License> allLicenses(){
		return licenseRepository.findAll();
	}
	
	// get one license by id
	public License findLicense(Long id) {
		Optional<License> optionalLicense = licenseRepository.findById(id);
		if(optionalLicense.isPresent()) {
			return optionalLicense.get();
		} else {
			return null;
		}
	}
	
	// Increment system license number and save it
	public License addLicense(License l) {
		sysNumber = String.format("%06d", Integer.parseInt(sysNumber) + 1);
		l.setNumber(sysNumber);
		////this.number = String.format("%06d", this.id);
		return licenseRepository.save(l);
	}
	
	// delete a license
	public void deleteLicense(Long id) {
		licenseRepository.deleteById(id);;
	}
	
}
