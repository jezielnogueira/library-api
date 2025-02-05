package org.v2com.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.persistence.EntityManager;
import org.v2com.entities.MyUser;

public class MyUserRepository implements PanacheRepository<MyUser> {
    EntityManager entityManager;

    public Integer countUsers(){
        return entityManager.createQuery("SELECT COUNT(u) FROM MyUser WHERE Status = {}", Integer.class).getSingleResult();
    }
}
