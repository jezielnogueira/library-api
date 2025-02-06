package org.v2com.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.v2com.entities.UserEntity;
import org.v2com.enuns.UserStatus;

import java.util.List;
import java.util.UUID;

public class UserRepository implements PanacheRepository<UserEntity> {

    @Inject
    EntityManager entityManager;

    public UserEntity findByName(String name) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.name = :username", UserEntity.class)
                .setParameter("username", name)
                .getSingleResult();
    }

    public UserEntity findbyId(UUID id){
        return entityManager.find(UserEntity.class, id);
    }

    public List<UserEntity> getAllUsers(){
        return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
    }

    public void persist(UserEntity userEntity){
        entityManager.persist(userEntity);
    }

    public void deleteById(UUID id){
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        if(userEntity != null){
            entityManager.remove(userEntity);
        }
    }

    public UserEntity changeUserStatus(UserEntity userEntity, UserStatus status){
        userEntity = findbyId(userEntity.id);
        userEntity.setStatus(status);
        entityManager.merge(userEntity);
        return userEntity;
    }
}
