package org.v2com.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestResponse;
import org.v2com.entities.UserEntity;

import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.findById;

@Path("/api/v1/user")
public class UserController {

    private Object User;

    @GET
    @Path("/finduser")
    public Uni<RestResponse<UserEntity>> getUser(long id){
        return Uni.createFrom().item(() -> findById(id))
                .map(user -> user != null
                        ? RestResponse.ok((UserEntity) user)
                        : RestResponse.status(RestResponse.Status.NOT_FOUND));
    }

}
