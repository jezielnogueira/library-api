package org.v2com.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.persistence.EntityManager;
import org.v2com.entities.UserEntity;

public class UserRepository implements PanacheRepository<UserEntity> {
    EntityManager entityManager;

    public Integer countUsers(){
        return entityManager.createQuery("SELECT COUNT(u) FROM UserEntity WHERE Status = {}", Integer.class).getSingleResult();
    }
}
