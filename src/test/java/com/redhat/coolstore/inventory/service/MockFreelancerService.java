package com.redhat.coolstore.inventory.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Specializes;

import com.redhat.coolstore.inventory.model.Freelancer;

@Specializes
public class MockFreelancerService extends FreelancerService {

	@Override
	public Freelancer getFreelancer(String freelancerId) {
		Freelancer f = new Freelancer();
		f.setFreelancerId(freelancerId);
		f.setFirstName("mock-first-name");
		f.setLastName("mock-last-name");
		f.setEmailAddress("mock@example.com");
		String skills[] = {"mock-skill-1", "mock-skill-2"};
		f.setSkills(skills);
		return f;
	}
	
	@Override
	public List<Freelancer> getFreelancers() {
		Freelancer f1 = new Freelancer();
		f1.setFreelancerId("mock-1");
		f1.setFirstName("mock-first-name-1");
		f1.setLastName("mock-last-name-1");
		f1.setEmailAddress("mock-1@example.com");
		String skills1[] = {"mock-skill-1", "mock-skill-2"};
		f1.setSkills(skills1);
		
		Freelancer f2 = new Freelancer();
		f2.setFreelancerId("mock-2");
		f2.setFirstName("mock-first-name-2");
		f2.setLastName("mock-last-name-2");
		f2.setEmailAddress("mock-2@example.com");
		String skills2[] = {"mock-skill-3", "mock-skill-4"};
		f2.setSkills(skills2);
		
		List<Freelancer> freelancers = new ArrayList<>();
		freelancers.add(f1);
		freelancers.add(f2);
		
		return freelancers;
	}
}