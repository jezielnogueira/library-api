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

    @Transactional
    public List<UserEntity> listAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserEntity findUserById(UUID id) {
        return userRepository.findById(id);
    }

    public UserEntity finUserByName(String name){
        return userRepository.findByName(name);
    }

    public void addUser(@Valid UserEntity userEntity) {
        userRepository.persist(userEntity);
    }

    @Transactional
    public void deleteUser(UUID id) {
        userRepository.deleteUserById(id);
    }

    @Transactional
    public void updateUser(UUID id, @Valid UserEntity userEntity) {
        UserEntity existingUser = userRepository.findById(id);

        existingUser.setName(userEntity.getName());
        existingUser.setAddress(userEntity.getAddress());
        existingUser.setEmail(userEntity.getEmail());
        existingUser.setPhone(userEntity.getPhone());

        userRepository.persist(existingUser);
    }

    public List<LoanEntity> listUserLoans(@Valid UserEntity userEntity) {
        return userEntity.getLoans();
    }


}
