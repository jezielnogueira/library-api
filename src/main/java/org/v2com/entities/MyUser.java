package org.v2com.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class MyUser extends PanacheEntity {


    @NotNull
    @Size(min = 3, max = 50)
    public String name;

    @NotNull
    @Size(min = 3, max = 50)
    public String email;

    @Size(min = 3, max = 100)
    public String address;

    @Size(min = 8, max = 20)
    public String phone;

    public String password;
    public String role;

}
