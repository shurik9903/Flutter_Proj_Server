package org.example.controller;

import org.example.model.registration.IRegistration;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/registration")
public class RegistrationController{

    @Inject
    private IRegistration reg;

    @GET
    @Path("/ping")
    public String ping() {
        return "Ping";
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response doPost(String RegData) {
        try {
            System.out.println("Test: " + RegData);
            return reg.RegistrFunc(RegData);
        }catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
        }
    }



}


