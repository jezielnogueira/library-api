package org.v2com.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.RestResponse;
import org.v2com.entities.LoanEntity;
import org.v2com.entities.UserEntity;
import org.v2com.services.UserService;

import java.util.List;

@Path("/api/v1/user")
public class UserController {

    @Inject
    UserService userService;

    private Object User;

    @GET
    @Path("/list")
    public RestResponse<List<UserEntity>> getAllLoan() {
        List<UserEntity> userEntities = userService.listAllUsers();
        return userEntities != null && !userEntities.isEmpty()
                ? RestResponse.ok(userEntities)
                : RestResponse.status(RestResponse.Status.NOT_FOUND);}

    @GET
    @Path("/finduserbyid/{userId}")
    public RestResponse<UserEntity> getUserById(@PathParam("userId") long id){
       return RestResponse.ok(userService.findUserById(id));
    }

    @GET
    @Path("/finduserbyname/{username}")
    public RestResponse<UserEntity> getUserByName(@PathParam("username") String name){
        return RestResponse.ok(userService.finUserByName(name));
    }

    @GET
    @Path("/loans/{userId}")
    public RestResponse<List<LoanEntity>> getUserLoans(@PathParam("userId") long id){
        UserEntity userEntity = userService.findUserById(id);
        return RestResponse.ok(userService.listUserLoans(userEntity));
    }

    @POST
    @Path("/create")
    public RestResponse<UserEntity> createUser(@Valid UserEntity userEntity) {
        UserEntity user = userService.addUser(userEntity);
        return RestResponse.ok(user);
    }

    @PUT
    @Path("/")
    public RestResponse<UserEntity> updateBook(@Valid UserEntity userEntity){
        userEntity = userService.updateUser(userEntity.getId(), userEntity);
        return RestResponse.ok(userEntity);
    }

    @DELETE
    @Path("/{id}")
    public RestResponse<Void> deleteUser(@PathParam("id") String idStr) {

        userService.deleteUser(Long.parseLong(idStr));
        return RestResponse.noContent();
    }



}
