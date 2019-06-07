package com.redhat.freelancer4j.gateway.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.redhat.freelancer4j.gateway.model.Freelancer;
import com.redhat.freelancer4j.gateway.model.Project;
import com.redhat.freelancer4j.gateway.service.FreelancerService;
import com.redhat.freelancer4j.gateway.service.ProjectService;

@Path("/gateway")
@RequestScoped
public class GatewayResource {

    @Inject
    private ProjectService projectService;

    @Inject
    private FreelancerService freelancerService;

    @GET
    @Path("/projects")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjects() {
    	List<Project> projectList = projectService.getProjects();
        return projectList;
    }

    @GET
    @Path("/projects/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProject(@PathParam("projectId") String projectId) {
    	Project project = projectService.getProject(projectId);
        if (project == null) {
            throw new NotFoundException();
        } else {
            return project;
        }
    }

    @GET
    @Path("/projects/status/{theStatus}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectsByStatus(@PathParam("theStatus") String status) {
    	List<Project> projectList = projectService.getProjectsByStatus(status);
        return projectList;
    }

    @GET
    @Path("/freelancers/{freelancerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Freelancer getFreelancer(@PathParam("freelancerId") String freelancerId) {
    	Freelancer freelancer = freelancerService.getFreelancer(freelancerId);
        if (freelancer == null) {
            throw new NotFoundException();
        } else {
            return freelancer;
        }
    }
    
    @GET
    @Path("/freelancers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Freelancer> getFreelancers() {
    	List<Freelancer> freelancerList = freelancerService.getFreelancers();
        return freelancerList;
    }
}