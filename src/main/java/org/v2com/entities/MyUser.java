package org.v2com.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.v2com.enuns.UserStatus;

import java.util.UUID;

@Entity
public class MyUser extends PanacheEntityBase {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    public UUID id;

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
//
//    @Column(name = "status", columnDefinition = "varchar(20)")
//    public UserStatus Status;

    public String password;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }


}
