package com.example.api.resource;

import com.example.api.dao.UserDAO;
import com.example.api.domain.User;
import com.mysql.jdbc.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {
    private static final String INVALID_PARAMETERS_ERROR = "Invalid parameters. Please provide username and password.";
    private final UserDAO userDAO;
    public UserResource(UserDAO userDao){
        this.userDAO = userDao;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@QueryParam("username") String username, @QueryParam("password") String password) {
        User user = userDAO.getUser(username, password);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        if (StringUtils.isNullOrEmpty(user.getUsername()) || StringUtils.isNullOrEmpty(user.getPassword())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(INVALID_PARAMETERS_ERROR).build();
        }
        userDAO.createUser(user.getUsername(), user.getPassword());
        return Response.status(Response.Status.CREATED).build();
    }
}
