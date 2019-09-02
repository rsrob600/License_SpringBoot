package com.codingdojo.relationships.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="persons")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "VARCHAR(65)")
	@NotBlank(message = "Please enter a valid first name")
    @Size(min = 2, message = "First name must be at least 2 characters in length")
    private String firstName;
    
    @Column(columnDefinition = "VARCHAR(65)")
	@NotBlank(message = "Please enter a valid last name")
    @Size(min = 2, message = "Last name must be at least 2 characters in length")
    private String lastName;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @OneToOne(mappedBy="person", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private License license;
    
    public Person() {}
    
    public Person(String firstName,String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

	public Date getCreatedAt() {return createdAt;}
	public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

	public Date getUpdatedAt() {return updatedAt;}
	public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
}
