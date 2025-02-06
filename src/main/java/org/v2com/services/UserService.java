package org.v2com.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.v2com.entities.LoanEntity;
import org.v2com.entities.UserEntity;
import org.v2com.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class UserService {

    @Inject
    UserRepository userRepository;


    public List<UserEntity> listAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserEntity findUserById(long id) {
        return userRepository.findById(id);
    }

    public UserEntity finUserByName(String name){
        return userRepository.findByName(name);
    }

    @Transactional(SUPPORTS)
    public UserEntity addUser(@Valid UserEntity userEntity) {
        userRepository.persist(userEntity);
        return userEntity;
    }

    @Transactional(SUPPORTS)
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional(SUPPORTS)
    public UserEntity updateUser(UUID id, @Valid UserEntity userEntity) {
        UserEntity existingUser = userRepository.findbyId(id);

        existingUser.setName(userEntity.getName());
        existingUser.setAddress(userEntity.getAddress());
        existingUser.setEmail(userEntity.getEmail());
        existingUser.setPhone(userEntity.getPhone());

        userRepository.persist(existingUser);
        return existingUser;
    }

    public List<LoanEntity> listUserLoans(@Valid UserEntity userEntity) {
        return userEntity.getLoans();
    }


}
