package com.redhat.freelancer4j.gateway.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Specializes;

import com.redhat.freelancer4j.gateway.model.Project;
import com.redhat.freelancer4j.gateway.service.ProjectService;

@Specializes
public class MockProjectService extends ProjectService {

	@Override
	public Project getProject(String projectId) {
		Project p = new Project();
		p.setProjectId(projectId);
		p.setOwnerFirstName("mock-first-name");
		p.setOwnerLastName("mock-last-name");
		p.setOwnerEmail("mock@example.com");
		p.setProjectTitle("mock-title");
		p.setProjectDescription("mock-description");
		p.setProjectStatus("open");
		return p;
	}
	
	@Override
	public List<Project> getProjects() {
		Project p1 = new Project();
		p1.setProjectId("mock-1");
		p1.setOwnerFirstName("mock-first-name-1");
		p1.setOwnerLastName("mock-last-name-1");
		p1.setOwnerEmail("mock-1@example.com");
		p1.setProjectTitle("mock-title-1");
		p1.setProjectDescription("mock-description-1");
		p1.setProjectStatus("open");
		
		Project p2 = new Project();
		p2.setProjectId("mock-2");
		p2.setOwnerFirstName("mock-first-name-2");
		p2.setOwnerLastName("mock-last-name-2");
		p2.setOwnerEmail("mock-2@example.com");
		p2.setProjectTitle("mock-title-2");
		p2.setProjectDescription("mock-description-2");
		p2.setProjectStatus("completed");
		
		List<Project> projects = new ArrayList<>();
		projects.add(p1);
		projects.add(p2);
		return projects;
	}
	
	@Override
	public List<Project> getProjectsByStatus(String status) {
		Project p1 = new Project();
		p1.setProjectId("mock-1");
		p1.setOwnerFirstName("mock-first-name-1");
		p1.setOwnerLastName("mock-last-name-1");
		p1.setOwnerEmail("mock-1@example.com");
		p1.setProjectTitle("mock-title-1");
		p1.setProjectDescription("mock-description-1");
		p1.setProjectStatus(status);
		
		Project p2 = new Project();
		p2.setProjectId("mock-2");
		p2.setOwnerFirstName("mock-first-name-2");
		p2.setOwnerLastName("mock-last-name-2");
		p2.setOwnerEmail("mock-2@example.com");
		p2.setProjectTitle("mock-title-2");
		p2.setProjectDescription("mock-description-2");
		p2.setProjectStatus(status);
		
		List<Project> projects = new ArrayList<>();
		projects.add(p1);
		projects.add(p2);
		return projects;
	}
}