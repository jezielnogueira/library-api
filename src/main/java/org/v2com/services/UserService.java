package org.v2com.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.v2com.entities.MyUser;

import java.util.List;
import java.util.UUID;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class UserService {


    public List<MyUser> findAllUsers() {
        return MyUser.listAll();
    }

    public MyUser findUserById(long id) {
        return MyUser.findById(id);
    }

    public MyUser findUserByEmail(String email) {
        return MyUser.find("email", email).firstResult();
    }

    public MyUser finUserByName(String name){
        return MyUser.find("name", name).firstResult();
    }

    @Transactional(SUPPORTS)
    public MyUser addUser(@Valid MyUser myUser) {
        myUser.persist();
        return myUser;
    }

    @Transactional(SUPPORTS)
    public void deleteUser(long id) {
        MyUser.deleteById(id);
    }

    @Transactional(SUPPORTS)
    public MyUser updateUser(UUID id, @Valid MyUser myUser) {
        MyUser existingUser = MyUser.findById(id);

        existingUser.name = myUser.name;
        existingUser.address = myUser.address;
        existingUser.email = myUser.email;
        existingUser.phone = myUser.phone;

        existingUser.persist();
        return existingUser;
    }

}
