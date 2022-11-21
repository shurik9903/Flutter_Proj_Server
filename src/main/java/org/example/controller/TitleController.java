package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.example.model.description.IDescript;
import org.example.model.title.ITitle;
import org.example.model.token.ITokenKey;
import org.example.model.token.ITokenValidator;
import org.example.model.token.TokenKey;
import org.example.model.token.TokenValidator;

@Path("/title")
public class TitleController {

    @Inject
    private ITitle title;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response doPost(String titleData, @HeaderParam("Token") String UserToken, @HeaderParam("UserID") String UserID) {
        try {

            System.out.println("title: " + titleData + "\n" +
                    "token: " + UserToken + "\n" +
                    "id: " + UserID + "\n");

            try {
                ITokenKey tokenKey = new TokenKey();
                ITokenValidator tokenValidator = new TokenValidator(tokenKey.getKey());
                tokenValidator.validate(UserToken);
            } catch (Exception e) {
                return Response.status(Response.Status.FORBIDDEN).entity("|Error: " + e.getMessage()).build();
            }

            return title.inputTitle(UserID, titleData);
        }catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response doGet(@HeaderParam("Token") String UserToken, @HeaderParam("UserID") String UserID, @HeaderParam("TitleName") String TitleName) {
        try {

            try {
                ITokenKey tokenKey = new TokenKey();
                ITokenValidator tokenValidator = new TokenValidator(tokenKey.getKey());
                tokenValidator.validate(UserToken);
            } catch (Exception e) {
                return Response.status(Response.Status.FORBIDDEN).entity("|Error: " + e.getMessage()).build();
            }

            return title.getTitle(UserID, TitleName);
        }catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error: " + e.getMessage()).build();
        }
    }
}
