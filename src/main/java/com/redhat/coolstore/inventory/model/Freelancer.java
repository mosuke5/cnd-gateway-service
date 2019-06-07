package com.redhat.coolstore.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Freelancer {

    private String freelancerId;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String[] skills;

    public String getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(String freelancerId) {
		this.freelancerId = freelancerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmailAddress(String email) {
		this.email = email;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}
}