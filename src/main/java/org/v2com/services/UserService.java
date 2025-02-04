package org.v2com.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.v2com.entities.MyUser;

import java.util.List;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class UserService {

    @Transactional(SUPPORTS)
    public List<MyUser> findAllUsers() {
        return MyUser.listAll();
    }

    @Transactional(SUPPORTS)
    public MyUser findUserById(long id) {
        return MyUser.findById(id);
    }

    public MyUser addUser(@Valid MyUser myUser) {
        myUser.persist();
        return myUser;
    }

    public void deleteUser(long id) {
        MyUser.deleteById(id);
    }

}
