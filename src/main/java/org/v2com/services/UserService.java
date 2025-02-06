package org.v2com.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.v2com.entities.UserEntity;

import java.util.List;
import java.util.UUID;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class UserService {


    public List<UserEntity> findAllUsers() {
        return UserEntity.listAll();
    }

    public UserEntity findUserById(long id) {
        return UserEntity.findById(id);
    }

    public UserEntity findUserByEmail(String email) {
        return UserEntity.find("email", email).firstResult();
    }

    public UserEntity finUserByName(String name){
        return UserEntity.find("name", name).firstResult();
    }

    @Transactional(SUPPORTS)
    public UserEntity addUser(@Valid UserEntity userEntity) {
        userEntity.persist();
        return userEntity;
    }

    @Transactional(SUPPORTS)
    public void deleteUser(long id) {
        UserEntity.deleteById(id);
    }

    @Transactional(SUPPORTS)
    public UserEntity updateUser(UUID id, @Valid UserEntity userEntity) {
        UserEntity existingUser = UserEntity.findById(id);

        existingUser.name = userEntity.name;
        existingUser.address = userEntity.address;
        existingUser.email = userEntity.email;
        existingUser.phone = userEntity.phone;

        existingUser.persist();
        return existingUser;
    }

}
