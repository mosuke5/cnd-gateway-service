package com.redhat.freelancer4j.gateway;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.redhat.freelancer4j.gateway.RestApplication;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class RestApiTest {

    private static String port = "18080";
    
    private Client client;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, RestApplication.class.getPackage())
                .addAsResource("project-local.yml", "project-defaults.yml")
                .addAsWebInfResource("test-beans.xml", "beans.xml");
    }

    @Before
    public void before() throws Exception {
        client = ClientBuilder.newClient();
    }

    @After
    public void after() throws Exception {
        client.close();
    }

    @Test
    @RunAsClient
    public void testGetFreelancer() throws Exception {
        WebTarget target = client.target("http://localhost:" + port).path("/gateway").path("/freelancers").path("123");
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), equalTo(new Integer(200)));
        JsonObject value = Json.parse(response.readEntity(String.class)).asObject();
        assertThat(value.getString("freelancerId", null), equalTo("123"));
        assertThat(value.getString("firstName", null), equalTo("mock-first-name"));
        assertThat(value.getString("lastName", null), equalTo("mock-last-name"));
        assertThat(value.getString("email", null), equalTo("mock@example.com"));
        assertThat(value.get("skills").asArray().get(0).asString(), equalTo("mock-skill-1"));
        assertThat(value.get("skills").asArray().get(1).asString(), equalTo("mock-skill-2"));
    }
    
    @Test
    @RunAsClient
    public void testGetFreelancers() throws Exception {
        WebTarget target = client.target("http://localhost:" + port).path("/gateway").path("/freelancers");
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), equalTo(new Integer(200)));
        JsonArray value = Json.parse(response.readEntity(String.class)).asArray();
        
        assertThat(value.get(0).asObject().getString("freelancerId", null), equalTo("mock-1"));
        assertThat(value.get(0).asObject().getString("firstName", null), equalTo("mock-first-name-1"));
        assertThat(value.get(1).asObject().getString("freelancerId", null), equalTo("mock-2"));
        assertThat(value.get(1).asObject().getString("firstName", null), equalTo("mock-first-name-2"));
    }

    @Test
    @RunAsClient
    public void testGetProject() throws Exception {
        WebTarget target = client.target("http://localhost:" + port).path("/gateway").path("/projects").path("123");
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), equalTo(new Integer(200)));
        JsonObject value = Json.parse(response.readEntity(String.class)).asObject();
        assertThat(value.getString("projectId", null), equalTo("123"));
        assertThat(value.getString("ownerFirstName", null), equalTo("mock-first-name"));
        assertThat(value.getString("ownerLastName", null), equalTo("mock-last-name"));
        assertThat(value.getString("ownerEmail", null), equalTo("mock@example.com"));
        assertThat(value.getString("projectTitle", null), equalTo("mock-title"));
        assertThat(value.getString("projectDescription", null), equalTo("mock-description"));
        assertThat(value.getString("projectStatus", null), equalTo("open"));
    }
    
    @Test
    @RunAsClient
    public void testGetProjects() throws Exception {
        WebTarget target = client.target("http://localhost:" + port).path("/gateway").path("/projects");
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), equalTo(new Integer(200)));
        JsonArray value = Json.parse(response.readEntity(String.class)).asArray();
        
        assertThat(value.get(0).asObject().getString("projectId", null), equalTo("mock-1"));
        assertThat(value.get(0).asObject().getString("ownerFirstName", null), equalTo("mock-first-name-1"));
        assertThat(value.get(0).asObject().getString("projectStatus", null), equalTo("open"));
        assertThat(value.get(1).asObject().getString("projectId", null), equalTo("mock-2"));
        assertThat(value.get(1).asObject().getString("ownerFirstName", null), equalTo("mock-first-name-2"));
        assertThat(value.get(1).asObject().getString("projectStatus", null), equalTo("completed"));
    }
    
    @Test
    @RunAsClient
    public void testGetProjectsByStatus() throws Exception {
        WebTarget target = client.target("http://localhost:" + port).path("/gateway").path("/projects").path("/status").path("/completed");
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), equalTo(new Integer(200)));
        JsonArray value = Json.parse(response.readEntity(String.class)).asArray();
        
        assertThat(value.get(0).asObject().getString("projectId", null), equalTo("mock-1"));
        assertThat(value.get(0).asObject().getString("ownerFirstName", null), equalTo("mock-first-name-1"));
        assertThat(value.get(0).asObject().getString("projectStatus", null), equalTo("completed"));
        assertThat(value.get(1).asObject().getString("projectId", null), equalTo("mock-2"));
        assertThat(value.get(1).asObject().getString("ownerFirstName", null), equalTo("mock-first-name-2"));
        assertThat(value.get(1).asObject().getString("projectStatus", null), equalTo("completed"));
    }
    
    @Test
    @RunAsClient
    public void testHealthCheck() throws Exception {
        WebTarget target = client.target("http://localhost:" + port).path("/health");
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), equalTo(new Integer(200)));
        JsonObject value = Json.parse(response.readEntity(String.class)).asObject();
        assertThat(value.getString("outcome", ""), equalTo("UP"));
        JsonArray checks = value.get("checks").asArray();
        assertThat(checks.size(), equalTo(new Integer(1)));
    }

}
