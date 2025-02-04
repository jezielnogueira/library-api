package org.v2com.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestResponse;
import org.v2com.entities.MyUser;

import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.findById;

@Path("/api/v1/user")
public class UserController {

    private Object User;

    @GET
    @Path("/finduser")
    public Uni<RestResponse<MyUser>> getUser(long id){
        return Uni.createFrom().item(() -> findById(id))
                .map(user -> user != null
                        ? RestResponse.ok((MyUser) user)
                        : RestResponse.status(RestResponse.Status.NOT_FOUND));
    }

}
