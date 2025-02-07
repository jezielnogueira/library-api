package org.v2com.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.v2com.entities.UserEntity;
import org.v2com.enuns.UserStatus;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserRepository {

    @Inject
    EntityManager entityManager;

    public UserEntity findByName(String name) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.name = :username", UserEntity.class)
                .setParameter("username", name)
                .getSingleResult();
    }

    public UserEntity findById(UUID id){
        return entityManager.find(UserEntity.class, id);
    }

    public List<UserEntity> getAllUsers(){
        return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
    }

    public void persist(UserEntity userEntity){
        entityManager.persist(userEntity);
    }

    public void deleteUserById(UUID id){
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        if(userEntity != null){
            entityManager.remove(userEntity);
        }
    }

    public UserEntity changeUserStatus(UserEntity userEntity, UserStatus status){
        userEntity = findById(userEntity.id);
        userEntity.setStatus(status);
        entityManager.merge(userEntity);
        return userEntity;
    }
}
