package com.example.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String greetUser(){
        return "Hello, your application is running!\n";
    }
}
