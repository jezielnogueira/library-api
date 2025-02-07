package org.v2com.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.v2com.entities.LoanEntity;
import org.v2com.entities.UserEntity;
import org.v2com.services.UserService;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @GET
    @Path("/list")
    public Response getAllUsers() {
        List<UserEntity> userEntities = userService.listAllUsers();
        return userEntities != null && !userEntities.isEmpty()
                ? Response.ok(userEntities).build()
                : Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("finduserbyid")
    public Response getUserById(@QueryParam("userId") UUID id){
        try {
            UserEntity userEntity = userService.findUserById(id);
            return Response.ok(userEntity).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found with ID: " + id)
                    .build();
        }
    }

    @GET
    @Path("/finduserbyname/{username}")
    public Response getUserByName(@PathParam("username") String name){
        UserEntity userEntity = userService.finUserByName(name);
        return userEntity != null
                ? Response.ok(userEntity).build()
                : Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/loans/")
    public Response getUserLoans(@QueryParam("userId") UUID id){
        List<LoanEntity> loanEntitiesList = userService.listUserLoans(userService.findUserById(id));
        return  loanEntitiesList != null && !loanEntitiesList.isEmpty()
                ? Response.ok(loanEntitiesList).build()
                : Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/create")
    public Response createUser(@Valid UserEntity userEntity) {
        userService.addUser(userEntity);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/")
    public Response updateBook(@Valid UserEntity userEntity){
        userService.updateUser(userEntity.getId(), userEntity);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/")
    public Response deleteUser(@QueryParam("id") UUID id) {

        userService.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
